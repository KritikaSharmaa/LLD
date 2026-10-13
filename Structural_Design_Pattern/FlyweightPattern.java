package Structural_Design_Pattern;

import java.util.*;

// ============= TreeType Class ================
class TreeType {
    // Properties that are common among all trees of this type
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing " + name + " tree at (" + x + ", " + y + ")");
    }
}

// ================ Tree Class =================
class Tree {
    // Attributes that keep on changing
    private int x;
    private int y;

    // Attributes that remain constant
    private TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw() {
        treeType.draw(x, y);
    }
}

// ============ TreeFactory Class ==============
class TreeFactory {

    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + " - " + color + " - " + texture;

        if (!treeTypeMap.containsKey(key)) {
            System.out.println("Creating new tree... " + key);
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        return treeTypeMap.get(key);
    }

    static int totalTypes() {
        return treeTypeMap.size();
    }
}

// ================ Forest Class =================
class Forest {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, TreeFactory.getTreeType(name, color, texture));
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        Forest forest = new Forest();

        forest.plantTree(1, 2, "Oak", "Green", "rough");
        forest.plantTree(5, 8, "Oak", "Green","rough");
        forest.plantTree(9, 3, "Oak", "Green","rough");
        forest.plantTree(4, 7, "Pine", "DarkGreen","rough");
        forest.plantTree(6, 1, "Pine", "DarkGreen","rough");

        forest.draw();

        System.out.println("TreeType objects: " + TreeFactory.totalTypes());
    }
}
