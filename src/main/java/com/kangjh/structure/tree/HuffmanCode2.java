package com.kangjh.structure.tree;

import java.util.*;

/**
 * HuffmanCode2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-12
 * @since 1.0.0
 */
public class HuffmanCode2 {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<HuffmanNode2> nodes = getNode(bytes);
        HuffmanNode2 root = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(root);
        return zip(bytes, huffmanCodes);
    }

    // 完成数据的解压
    // 思路
    // 1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    // 2. 赫夫曼编码对应的二进制的字符串 "1010100010111..." => 对照 赫夫曼编码  => "i like like like java do you like a java"

    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param b    传入的byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b对应的二进制的字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            // 按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return null;
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    private static void getCodes(HuffmanNode2 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 思路:
     * 2.1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
     * 生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 2.2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子节点的路径
     */
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(HuffmanNode2 root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static List<HuffmanNode2> getNode(byte[] bytes) {
        List<HuffmanNode2> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.merge(b, 1, (a, b1) -> a + b1);
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            HuffmanNode2 huffmanNode = new HuffmanNode2(entry.getKey(), entry.getValue());
            nodes.add(huffmanNode);
        }
        return nodes;
    }

    private static HuffmanNode2 createHuffmanTree(List<HuffmanNode2> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode2 left = nodes.get(0);
            HuffmanNode2 right = nodes.get(1);
            HuffmanNode2 parent = new HuffmanNode2(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(HuffmanNode2 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

}

class HuffmanNode2 implements Comparable<HuffmanNode2> {

    Byte data;

    int weight;

    HuffmanNode2 left;

    HuffmanNode2 right;

    public HuffmanNode2(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(HuffmanNode2 o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

}
