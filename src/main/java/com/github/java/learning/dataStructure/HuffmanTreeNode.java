package com.github.java.learning.dataStructure;

/**
 * 哈夫曼树子结点定义
 * created by guanjian on 2020/12/30 21:18
 */
public class HuffmanTreeNode implements Comparable {

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
     * 通过weight权重字段进行排序
     */
    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.getWeight(), ((HuffmanTreeNode) o).getWeight());
    }

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

    public static class Builder {

        private HuffmanTreeNode parent;
        private HuffmanTreeNode leftChild;
        private HuffmanTreeNode rightChild;
        private int weight;

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

        public HuffmanTreeNode build() {
            HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode();
            huffmanTreeNode.setLeftChild(this.leftChild);
            huffmanTreeNode.setRightChild(this.rightChild);
            huffmanTreeNode.setParent(this.parent);
            huffmanTreeNode.setWeight(this.weight);
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
                '}';
    }
}