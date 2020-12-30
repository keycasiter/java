package com.github.java.learning.dataStructure;

/**
 * 哈夫曼树
 * created by guanjian on 2020/12/30 15:43
 */
public class HuffmanTree {

    /**
     * 权值数组
     */
    private int[] weightArray;
    /**
     * 哈夫曼树结点数组
     */
    private HuffmanTreeNode[] huffmanTreeNodeArray;

    public HuffmanTree(int[] weightArray, HuffmanTreeNode[] huffmanTreeNodeArray) {
        this.weightArray = weightArray;
        this.huffmanTreeNodeArray = huffmanTreeNodeArray;
    }

    public static final class Builder {
        private int[] weightArray;
        private HuffmanTreeNode[] huffmanTreeNodeArray;

        private Builder() {
        }

        public static Builder aHuffmanTree() {
            return new Builder();
        }

        public Builder weightArray(int[] weightArray) {
            this.weightArray = weightArray;
            return this;
        }

        public Builder huffmanTreeNodeArray(HuffmanTreeNode[] huffmanTreeNodeArray) {
            this.huffmanTreeNodeArray = huffmanTreeNodeArray;
            return this;
        }

        public HuffmanTree build() {
            return new HuffmanTree(weightArray, huffmanTreeNodeArray);
        }
    }


    /**
     * 哈夫曼树子结点定义
     */
    class HuffmanTreeNode {

        /**
         * 父结点
         */
        private int parent;

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

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
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

        public class Builder {

            private int parent;
            private HuffmanTreeNode leftChild;
            private HuffmanTreeNode rightChild;
            private int weight;

            private Builder() {
            }

            public Builder aHuffmanTreeNode() {
                return new Builder();
            }

            public Builder weight(int weight) {
                this.weight = weight;
                return this;
            }

            public Builder parent(int parent) {
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
    }

    public static void main(String[] args) {
        //权值结点n

        //哈夫曼树结点总数 2n-1


        HuffmanTreeNode[] huffmanTreeNodeArray = new HuffmanTreeNode[];

        HuffmanTree huffmanTree = HuffmanTree.Builder.aHuffmanTree()
                .huffmanTreeNodeArray()
                .weightArray(new int[]{})
                .build();
    }
}
