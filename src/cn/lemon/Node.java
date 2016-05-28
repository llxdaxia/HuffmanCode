package cn.lemon;

/**
 * Created by linlongxin on 2016/5/23.
 */
public class Node implements Comparable<Node> {

    private String chars = "";
    private int weight = 0;

    private Node parent;
    private Node leftChild;
    private Node rightChild;

    @Override
    public int compareTo(Node o) {
        return weight - o.getWeight();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.leftChild;
    }

    public boolean isRightChile() {
        return parent != null && this == parent.rightChild;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

}
