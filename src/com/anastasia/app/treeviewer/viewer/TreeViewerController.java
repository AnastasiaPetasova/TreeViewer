package com.anastasia.app.treeviewer.viewer;

import com.anastasia.app.treeviewer.tree.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class TreeViewerController implements Initializable {

    private static final double NODE_CIRCLE_SIZE = 30, EDGE_LINE_WIDTH = 5, NODE_VALUE_FONT_SIZE = 12;

    private Node root;

    @FXML
    private TextField input_textField;

    @FXML
    private Button task28_button;

    @FXML
    private Button task33_button;

    @FXML
    private Button task39_button;

    @FXML
    private Button task43_button;

    @FXML
    private Button task47_button;

    @FXML
    private Canvas tree_canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCanvas();
        initButtons();
    }

    private void initCanvas() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        tree_canvas.setWidth(screenSize.width - 200);
        tree_canvas.setHeight(screenSize.height - 200);

        clearCanvas();
    }

    private void clearCanvas() {
        GraphicsContext graphics = tree_canvas.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, tree_canvas.getWidth(), tree_canvas.getHeight());
    }

    private void drawTree(){
        TreeDrawStrategy strategy = new TreeDrawStrategy();
        strategyDfs(root, null, 0, strategy);
        Map<Node, Point2D.Double> positions = new HashMap<>();

        List<List<Node>> levels = strategy.getLevels();

        double levelHeight = (tree_canvas.getHeight() - NODE_CIRCLE_SIZE) / (levels.size() + 1);
        for (int levelIndex = 0; levelIndex < levels.size(); ++levelIndex) {
            double y = (levelIndex + 1) * levelHeight;

            List<Node> nodes = levels.get(levelIndex);
            double levelWidth = (tree_canvas.getWidth() - NODE_CIRCLE_SIZE) / (nodes.size() + 1);

            for (int nodeIndex = 0; nodeIndex < nodes.size(); ++nodeIndex) {
                Node node = nodes.get(nodeIndex);

                double x = levelWidth * (nodeIndex + 1);

                positions.put(node, new Point2D.Double(x + NODE_CIRCLE_SIZE / 2, y + NODE_CIRCLE_SIZE / 2));
            }
        }

        clearCanvas();

        GraphicsContext graphics = tree_canvas.getGraphicsContext2D();

        graphics.setStroke(Color.LIGHTGRAY);
        for (List<Node> level : levels) {
            for (Node node : level) {
                Node parent = strategy.getParent(node);
                if (parent != null) {
                    Point2D.Double nodePosition = positions.get(node);
                    Point2D.Double parentPosition = positions.get(parent);
                    graphics.setStroke(Color.GRAY);
                    graphics.strokeLine(parentPosition.x, parentPosition.y, nodePosition.x, nodePosition.y);
                }
            }
        }

        graphics.setStroke(Color.BLACK);
        graphics.setFill(Color.WHITE);
        graphics.setFont(javafx.scene.text.Font.font(NODE_VALUE_FONT_SIZE));

        for (List<Node> level : levels) {
            for (Node node : level) {
                Point2D.Double nodePosition = positions.get(node);

                graphics.strokeOval(
                        nodePosition.x - NODE_CIRCLE_SIZE  / 2, nodePosition.y - NODE_CIRCLE_SIZE / 2,
                        NODE_CIRCLE_SIZE, NODE_CIRCLE_SIZE
                );

                graphics.fillOval(
                        nodePosition.x - NODE_CIRCLE_SIZE / 2, nodePosition.y - NODE_CIRCLE_SIZE / 2,
                        NODE_CIRCLE_SIZE, NODE_CIRCLE_SIZE
                );

                graphics.strokeText(
                        String.valueOf(node.getValue()),
                        nodePosition.x, nodePosition.y
                );
            }
        }
    }

    private void strategyDfs(Node node, Node parent, int depth, TreeDrawStrategy strategy) {
        if (node == null) return;

        strategy.register(node, parent, depth);

        strategyDfs(node.getLeft(), node, depth + 1, strategy);
        strategyDfs(node.getRight(), node, depth + 1, strategy);
    }

    private void initButtons() {
        initCreateTreeButtons();
        initUpdateTreeButtons();
    }

    private void setOnCreateAction(Button button, Function<int[], Node> treeCreator) {
        button.setOnAction(event -> {
            try {
                String input = input_textField.getText();
                if (input.isEmpty()) return;

//            String[] parts = input.split(" ");
//            int[] array = new int[parts.length];
//            for (int i = 0; i < array.length; i++) {
//                array[i] = Integer.parseInt(parts[i]);
//            }
//
//
                int[] array = Arrays.stream(input.split(" "))
                        .filter(str -> !str.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();

                root = treeCreator.apply(array);
                drawTree();
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Некорректные значения, мать вашу!", ButtonType.OK).show();
            }
        });
    }

    private void initCreateTreeButtons() {
        setOnCreateAction(task28_button, Tree28::build);
        setOnCreateAction(task33_button, Tree33::build);
    }

    private void setOnUpdateAction(Button button, Function<Node, Node> treeUpdater) {
        button.setOnAction(event -> {
            if(root == null) return;
            root = treeUpdater.apply(root);
            drawTree();
        });
    }

    private void initUpdateTreeButtons() {
        setOnUpdateAction(task39_button, Tree39::swap);
        setOnUpdateAction(task43_button, Tree43::executionPlusPlus);
        setOnUpdateAction(task47_button, Tree47::extend);
    }
}
