package cn.lemon;

import java.util.*;

public class HuffmanCode {
    /**
     * 对字符串进行编码
     *
     * @param originalStr
     * @return
     */
    public String encode(String originalStr) {
        if (originalStr == null || originalStr.isEmpty()) {
            return "";
        }
        List<Node> leafNodes = new ArrayList<>();
        buildTree(originalStr, leafNodes);

        Map<Character, String> codeMap = buildEncodingInfo(leafNodes);

        StringBuilder stringBuilder = new StringBuilder();

        for (Character c : originalStr.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c < 'a') {
                c = (char) (c + 'a' - 'A');
            }
            stringBuilder.append(codeMap.get(c));
        }

        return stringBuilder.toString();
    }

    /**
     * 对每个字符进行编码，返回一个map集合
     *
     * @param leafNodes
     * @return
     */
    private Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {

        Map<Character, String> codeWords = new HashMap<>();

        for (Node leaf : leafNodes) {
            Character character = leaf.getChars().charAt(0);
            String code = "";
            Node currentNode = leaf;

            do {
                if (currentNode.isLeftChild()) {
                    code += "0";
                } else {
                    code += "1";
                }

                currentNode = currentNode.getParent();

            } while (currentNode.getParent() != null);

            codeWords.put(character, code);
            System.out.println("char : " + character + "  code : " + code);
        }
        System.out.println();
        return codeWords;
    }

    /**
     * 创建树的过程
     *
     * @param originStr
     * @param leafs
     * @return
     */
    private Tree buildTree(String originStr, List<Node> leafs) {

        Map<Character, Integer> statistics = statistics(originStr.toCharArray());

        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getWeight() - o2.getWeight();
            }
        };

        //通过有序队列，对头是最小的元素
        PriorityQueue<Node> queue = new PriorityQueue<>(comparator);

        for (Character c : statistics.keySet()) {
            System.out.println("char : " + c + "  weight : " + statistics.get(c));
        }
        System.out.println();

        for (Character c : statistics.keySet()) {

            Node node = new Node();
            node.setChars(c.toString());
            node.setWeight(statistics.get(c));

            leafs.add(node);   //每个字符都是叶子结点
            queue.add(node);

        }

        int size = queue.size();
        for (int i = 1; i < size; i++) {  //n - 1次
            Node node1 = queue.poll();
            Node node2 = queue.poll();

            Node sumNode = new Node();
            sumNode.setChars(node1.getChars() + node2.getChars());
            sumNode.setWeight(node1.getWeight() + node2.getWeight());

            sumNode.setLeftChild(node1);
            sumNode.setRightChild(node2);

            node1.setParent(sumNode);
            node2.setParent(sumNode);

            queue.add(sumNode);
        }

        Tree tree = new Tree();
        tree.setRoot(queue.poll());   //最后一次只剩下一个结点
        return tree;
    }

    /**
     * 通过频率设置权重
     *
     * @param charArray
     * @return
     */
    private Map<Character, Integer> statistics(char[] charArray) {

        Map<Character, Integer> map = new HashMap<>();

        for (Character c : charArray) {
            if (c == ' ') {   //排除空格
                System.out.println("-----排除空格-----");
                continue;
            }
            if (c < 'a') {   //排除大写字母，把大写转换成小写
                System.out.println("转换的大写字符： " + c);
                c = (char) (c + 'a' - 'A');
            }
            if (map.containsKey(c)) {   //
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
}  