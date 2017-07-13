package com.anastasia.app.treeviewer.viewer;

import com.anastasia.app.treeviewer.tree.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeDrawStrategy {

    private List<List<Node>> levels;
    private Map<Node, Node> parents;

    TreeDrawStrategy() {
        this.levels = new ArrayList<>();
        this.parents = new HashMap<>();
    }

    public void register(Node node, Node parent, int depth) {
        parents.put(node, parent);

        if (depth == levels.size()) levels.add(new ArrayList<>());
        List<Node> level = levels.get(depth);
        level.add(node);
    }

    public List<List<Node>> getLevels() {
        return levels;
    }

    public Node getParent(Node node) {
        return parents.get(node);
    }
}
