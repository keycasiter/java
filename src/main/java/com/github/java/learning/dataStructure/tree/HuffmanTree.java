package com.github.java.learning.dataStructure.tree;

import com.google.common.collect.Lists;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 哈夫曼树
 * created by guanjian on 2020/12/30 15:43
 */
public class HuffmanTree {

    //权值结点
    private Integer[] weightArray;
    //哈夫曼树结点总数 2*weights - 1
    private int huffmanTreeNodeLength;
    //哈夫曼树结点数组
    private List<HuffmanTreeNode> huffmanTreeNodeList;
    //每次获取最小权值数量
    private final static int minNodeLength = 2;

    public HuffmanTree(Integer[] weightArray) {
        Assert.notEmpty(weightArray, "weightArray can not be empty");
        this.weightArray = weightArray;
    }

    /**
     * 初始化哈夫曼树结点数组
     */
    public void initHuffmanTreeNodeArray() {
        //哈夫曼树结点总数 2*weights - 1
        this.huffmanTreeNodeLength = 2 * this.weightArray.length - 1;
        this.huffmanTreeNodeList = Lists.newArrayListWithCapacity(huffmanTreeNodeLength);

        //初始化填充哈夫曼树结点
        IntStream.range(0, weightArray.length).forEach(i -> {
            //这里将权值灌入到node节点中
            huffmanTreeNodeList.add(
                    HuffmanTreeNode.Builder.aHuffmanTreeNode()
                            .weight(weightArray[i])
                            .build()
            );
        });
        System.out.format("初始化完成 huffmanTreeNodeList=%s \n", Arrays.toString(huffmanTreeNodeList.toArray()));
    }

    /**
     * 查找最小权值方法
     */
    public List<HuffmanTreeNode> findMinNodes() {
        //按权值从小到大升序进行排序
        List<HuffmanTreeNode> sortedList = huffmanTreeNodeList.stream()
                .filter(x -> !x.isSorted())
                .sorted(Comparator.comparing(HuffmanTreeNode::getWeight))
                .collect(Collectors.toList());
        sortedList.forEach(x -> System.out.format("排序 %s 处理 %s \n", x.getWeight(), x.isSorted()));
        //取最小权值的两个node
        List<HuffmanTreeNode> list = sortedList.subList(0,
                sortedList.size() >= minNodeLength ? minNodeLength : sortedList.size());

        if (CollectionUtils.isEmpty(list)) return list;

        list.forEach(x -> {
            x.setSorted(true);
        });
        return list;
    }

    /**
     * 构建哈夫曼树
     */
    public void buildHuffmanTree() {
        for (; ; ) {
            /**
             * 选取权值最小的两个结点，构造父节点
             */
            List<HuffmanTreeNode> minNodes = findMinNodes();
            if (minNodes.size() != minNodeLength) break;

            //左子节点
            HuffmanTreeNode leftChild = minNodes.get(0);
            //右子节点
            HuffmanTreeNode rightChild = minNodes.get(1);
            //父结点
            HuffmanTreeNode parent = HuffmanTreeNode.Builder.aHuffmanTreeNode()
                    .weight(leftChild.getWeight() + rightChild.getWeight())
                    .leftChild(leftChild)
                    .rightChild(rightChild)
                    .build();

            leftChild.setParent(parent);
            rightChild.setParent(parent);

            System.out.println("====================================");
            System.out.format("获取左子结点权值=%s\n", leftChild.getWeight());
            System.out.format("获取右子结点权值=%s\n", rightChild.getWeight());
            System.out.format("获取父结点权值=%s\n", parent.getWeight());

            huffmanTreeNodeList.add(parent);
        }
    }

    /**
     * 计算当前二叉树的WPL
     * weight（结点权值） * path（树高度） = WPL（权值路径）
     */
    public void calculateWeightPathLength() {
        System.out.println("====================================");

        int wpl = 0;

        for (HuffmanTreeNode node : huffmanTreeNodeList) {
            //跳过构建结点,只计算权值结点
            if (null != node.leftChild || null != node.rightChild) continue;

            int path = 0;
            HuffmanTreeNode currentNode = node;
            while (null != currentNode.parent) {
                path++;
                currentNode = currentNode.parent;
            }
            System.out.format("权值：%s * 路径长度：%s \n", node.getWeight(), path);
            wpl += node.getWeight() * path;
        }
        System.out.format("WPL=%s \n", wpl);
    }

    public Integer[] getWeightArray() {
        return weightArray;
    }

    public void setWeightArray(Integer[] weightArray) {
        this.weightArray = weightArray;
    }

    public int getHuffmanTreeNodeLength() {
        return huffmanTreeNodeLength;
    }

    public void setHuffmanTreeNodeLength(int huffmanTreeNodeLength) {
        this.huffmanTreeNodeLength = huffmanTreeNodeLength;
    }

    public List<HuffmanTreeNode> getHuffmanTreeNodeList() {
        return huffmanTreeNodeList;
    }

    public void setHuffmanTreeNodeList(List<HuffmanTreeNode> huffmanTreeNodeList) {
        this.huffmanTreeNodeList = huffmanTreeNodeList;
    }

    public static int getMinNodeLength() {
        return minNodeLength;
    }

    static class HuffmanTreeNode {

        /**
         * 父结点
         */
        private HuffmanTreeNode parent;

        /**
         * 左子结点
         */
        private HuffmanTreeNode leftChild;

        /**
         * 右子结点
         */
        private HuffmanTreeNode rightChild;

        /**
         * 权重值
         */
        private int weight;

        /**
         * 是否完成排序
         */
        private boolean sorted;

        public HuffmanTreeNode getParent() {
            return parent;
        }

        public void setParent(HuffmanTreeNode parent) {
            this.parent = parent;
        }

        public HuffmanTreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(HuffmanTreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public HuffmanTreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(HuffmanTreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public boolean isSorted() {
            return sorted;
        }

        public void setSorted(boolean sorted) {
            this.sorted = sorted;
        }

        public static class Builder {

            private HuffmanTreeNode parent;
            private HuffmanTreeNode leftChild;
            private HuffmanTreeNode rightChild;
            private int weight;
            private boolean sorted;

            private Builder() {
            }

            public static Builder aHuffmanTreeNode() {
                return new Builder();
            }

            public Builder weight(int weight) {
                this.weight = weight;
                return this;
            }

            public Builder parent(HuffmanTreeNode parent) {
                this.parent = parent;
                return this;
            }

            public Builder rightChild(HuffmanTreeNode rightChild) {
                this.rightChild = rightChild;
                return this;
            }

            public Builder leftChild(HuffmanTreeNode leftChild) {
                this.leftChild = leftChild;
                return this;
            }

            public Builder sorted(boolean sorted) {
                this.sorted = sorted;
                return this;
            }

            public HuffmanTreeNode build() {
                HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode();
                huffmanTreeNode.setLeftChild(this.leftChild);
                huffmanTreeNode.setRightChild(this.rightChild);
                huffmanTreeNode.setParent(this.parent);
                huffmanTreeNode.setWeight(this.weight);
                huffmanTreeNode.setSorted(this.sorted);
                return huffmanTreeNode;
            }
        }

        @Override
        public String toString() {
            return "HuffmanTreeNode{" +
                    "parent=" + parent +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", weight=" + weight +
                    ", sorted=" + sorted +
                    '}';
        }
    }

    public static void main(String[] args) {
        //权值结点
        Integer[] weightArray = new Integer[]{2,3,5,7,5};

        //声明哈夫曼树
        HuffmanTree huffmanTree = new HuffmanTree(weightArray);
        //初始化哈夫曼树
        huffmanTree.initHuffmanTreeNodeArray();
        //构建哈夫曼树
        huffmanTree.buildHuffmanTree();
        //WPL
        huffmanTree.calculateWeightPathLength();
    }
}
