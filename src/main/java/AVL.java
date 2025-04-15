public class AVL {
    Node root;
    int size;

    public AVL() {
        size = -1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(int element) {
        this.size += 1;
        if (isEmpty()) this.root = new Node(element);
        else {
            Node aux = this.root;
            while (aux != null) {
                if (element < aux.value) {
                    if (aux.left == null) {
                        Node newNode = new Node(element);
                        aux.left = newNode;
                        newNode.parent = aux;
                        Node unbalanced = checkBalance(newNode);
                        if (unbalanced != null) callBestRotation(unbalanced);
                        return;
                    }
                    aux = aux.left;
                } else {
                    if (aux.right == null) {
                        Node newNode = new Node(element);
                        aux.right = newNode;
                        newNode.parent = aux;
                        Node unbalanced = checkBalance(newNode);
                        if (unbalanced != null) callBestRotation(unbalanced);
                        return;
                    }
                    aux = aux.right;
                }
            }
        }
    }

    public Node checkBalance(Node root) {
        Node aux = root;
        while (aux != null) {
            if (!aux.isBalanced()) return aux;
            else if (aux.parent != null) aux = aux.parent;
            else break;
        }
        return null;
    }

    public void callBestRotation(Node unbalanced) {
        Node x = unbalanced;
        if (x.isLeftPending()) {
            Node y = x.left;
            if (y.left != null) rotateRight(x);
            else {
                rotateLeft(y);
                rotateRight(x);
            }
        } else {
            Node y = x.right;
            if (y.right != null) rotateLeft(x);
            else {
                rotateRight(y);
                rotateLeft(x);
            }
        }
    }

    public void rotateRight(Node node) {
        Node newRoot = node.left; // raiz da subárvore
        newRoot.parent = node.parent;

        node.left = newRoot.right;
        newRoot.right = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.left == node) newRoot.parent.left = newRoot;
            else newRoot.parent.right = newRoot;
        } else root = newRoot;
    }

    public void rotateLeft(Node node) {
        Node newRoot = node.right;
        newRoot.parent = node.parent;

        node.right = newRoot.left;
        newRoot.left = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.right == node) newRoot.parent.right = newRoot;
            else newRoot.parent.left = newRoot;
        } else root = newRoot;
    }
}