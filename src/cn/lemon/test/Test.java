package cn.lemon.test;

import cn.lemon.HuffmanCode;

/**
 * Created by linlongxin on 2016/5/23.
 */
public class Test {

    public static void main(String[] args) {
        String str = "Chongqing University of Posts and Telecommunications";
        HuffmanCode huffmanCode = new HuffmanCode();
        String encodeStr = huffmanCode.encode(str);
        System.out.println("哈夫曼编码：" + encodeStr);
    }

}
