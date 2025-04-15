import static java.lang.Math.max;

public class Node {
    int value;
    int height;
    Node left;
    Node right;
    Node parent;

    public Node(int v) {
        height = 0;
        value = v;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public int height() {
        if (left == null && right == null) return 0;
        else if (left == null) return 1 + right.height();
        else if (right == null) return 1 + left.height();
        else return 1 + max(left.height(), right.height());
    }

    public int balance() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right;
    }

    public boolean isLeftPending() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right >= 1;
    }

    public boolean isBalanced() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right >= -1 && left - right <= 1;
    }
}