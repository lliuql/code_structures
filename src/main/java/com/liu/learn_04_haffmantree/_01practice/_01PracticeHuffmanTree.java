package com.liu.learn_04_haffmantree._01practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/6 15:06
 */
public class _01PracticeHuffmanTree {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node huffmanTree = createHuffmanTree(arr);

        preOrder(huffmanTree);
    }

    public static Node createHuffmanTree(int [] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
//            System.out.println(nodes);
            Node nodeLeft = nodes.remove(0);
            Node nodeRight = nodes.remove(0);
            Node node = new Node(nodeLeft.getValue() + nodeRight.getValue());
            node.setLeft(nodeLeft);
            node.setRight(nodeRight);
            nodes.add(node);
//            System.out.println(nodes);

        }

        return nodes.get(0);
    }

    public static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        }
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);

        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }


    }
}
