package com.liu.learn_05_haffmancode._02huffmancode_practice;

import java.util.*;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/6 16:32
 */
public class _02HuffManCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println(getNodes(bytes));
        Node huffManTree = createHuffManTree(  getNodes(bytes));
//        huffManTree.preOrder();

        Map<Byte, String> codes = getCodes(huffManTree);
        System.out.println(codes);
    }

    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            if (map.containsKey(aByte)) {
                map.put(aByte, map.get(aByte) + 1);
            } else {
                map.put(aByte, 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
    private static Node createHuffManTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.remove(0);
            Node rightNode = nodes.remove(0);
            Node node = new Node(leftNode.getWeight() + rightNode.getWeight(), leftNode, rightNode);

            nodes.add(node);
        }

        return nodes.get(0);
    }
    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "0", stringBuilder);
        getCodes(root, "1", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);

        if (node != null) {
            if (node.getData() == null) {
                getCodes(node.getLeftNode(), "0", stringBuilder1);
                getCodes(node.getRightNode(),"1", stringBuilder1);
            } else {
                huffmanCodes.put(node.getData(), stringBuilder1.toString());
            }
        }

    }

//    //为了调用方便，我们重载 getCodes
//    private static Map<Byte, String> getCodes(Node root) {
//        if(root == null) {
//            return null;
//        }
//        //处理root的左子树
//        getCodes(root.getLeftNode(), "0", stringBuilder);
//        //处理root的右子树
//        getCodes(root.getRightNode(), "1", stringBuilder);
//        return huffmanCodes;
//    }
//
//    /**
//     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
//     * @param node  传入结点
//     * @param code  路径： 左子结点是 0, 右子结点 1
//     * @param stringBuilder 用于拼接路径
//     */
//    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
//        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
//        //将code 加入到 stringBuilder2
//        stringBuilder2.append(code);
//        if(node != null) { //如果node == null不处理
//            //判断当前node 是叶子结点还是非叶子结点
//            if(node.getData() == null) { //非叶子结点
//                //递归处理
//                //向左递归
//                getCodes(node.getLeftNode(), "0", stringBuilder2);
//                //向右递归
//                getCodes(node.getRightNode(), "1", stringBuilder2);
//            } else { //说明是一个叶子结点
//                //就表示找到某个叶子结点的最后
//                huffmanCodes.put(node.getData(), stringBuilder2.toString());
//            }
//        }
//    }
}
class Node implements Comparable<Node>{
    private Byte data;
    private int weight;
    private Node leftNode;
    private Node rightNode;

    public Node(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight, Node leftNode, Node rightNode) {
        this.weight = weight;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(byte data, int weight, Node leftNode, Node rightNode) {
        this.data = data;
        this.weight = weight;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Byte getData() {
        return data;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder () {
        System.out.println(this);
        if (this.getLeftNode() != null) {
            this.getLeftNode().preOrder();
        }
        if (this.getRightNode() != null) {
            this.getRightNode().preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}