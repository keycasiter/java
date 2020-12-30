package com.github.java.learning.dataStructure;

import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * 哈夫曼树
 * created by guanjian on 2020/12/30 15:43
 */
public class HuffmanTree {

    //权值结点
    private Integer[] weightArray;
    //哈夫曼树结点总数 2*weights - 1
    private int huffmanTreeNodeLength = 7;//2 * this.weightArray.length - 1;
    //哈夫曼树结点数组
    private HuffmanTreeNode[] huffmanTreeNodeArray = new HuffmanTreeNode[7];
    //每次获取最小权值数量
    private final static int minNodeLength = 2;

    public HuffmanTree(Integer[] weightArray) {
        Assert.notEmpty(weightArray, "weightArray can not be empty");
        this.weightArray = weightArray;
        initHuffmanTreeNodeArray();
        buildHuffmanTree();
    }

    /**
     * 初始化哈夫曼树结点数组
     */
    private void initHuffmanTreeNodeArray() {
        for (int i : weightArray) {
            //这里将权值灌入到node节点中
            huffmanTreeNodeArray[i].setWeight(i);
        }
    }

    /**
     * 查找最小权值方法
     */
    private HuffmanTreeNode[] findMinNodes() {
        //按升序进行排序
        Arrays.sort(huffmanTreeNodeArray);
        //取最小权值的两个node
        return Arrays.copyOfRange(huffmanTreeNodeArray,
                0,
                huffmanTreeNodeArray.length >= minNodeLength ? minNodeLength : huffmanTreeNodeArray.length);
    }

    /**
     * 构建哈夫曼树
     */
    private void buildHuffmanTree() {
        for (; ; ) {
            /**
             * 1、选取与合并
             */
            HuffmanTreeNode[] minNodes = findMinNodes();
            if (minNodes.length != minNodeLength) break;

            //左子节点
            HuffmanTreeNode leftChild = minNodes[0];
            //右子节点
            HuffmanTreeNode rightChild = minNodes[1];
            //父结点
            HuffmanTreeNode parent = HuffmanTreeNode.Builder.aHuffmanTreeNode()
                    .weight(minNodes[0].getWeight() + minNodes[1].getWeight())
                    .leftChild(leftChild)
                    .rightChild(rightChild)
                    .build();

            leftChild.setParent(parent);
            rightChild.setParent(parent);

            /**
             * 2、删除与加入
             */
            huffmanTreeNodeArray[huffmanTreeNodeArray.length] = parent;
        }
    }

    public static void main(String[] args) {
        //权值结点
        Integer[] weightArray = new Integer[]{2, 3, 5, 7};

        HuffmanTree huffmanTree = new HuffmanTree(weightArray);

//        HuffmanTreeNode[] huffmanTreeNodeArray = new HuffmanTreeNode[]{
//                HuffmanTreeNode.Builder.aHuffmanTreeNode()
//                        .weight(3)
//                        .build(),
//                HuffmanTreeNode.Builder.aHuffmanTreeNode()
//                        .weight(7)
//                        .build(),
//                HuffmanTreeNode.Builder.aHuffmanTreeNode()
//                        .weight(2)
//                        .build(),
//                HuffmanTreeNode.Builder.aHuffmanTreeNode()
//                        .weight(4)
//                        .build()
//        };
//        Arrays.sort(huffmanTreeNodeArray);
//        System.out.println(Arrays.toString(huffmanTreeNodeArray));
//        System.out.println(Arrays.toString(Arrays.copyOfRange(huffmanTreeNodeArray, 0, 2)));
    }
}
