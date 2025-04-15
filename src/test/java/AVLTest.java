import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTest {

    @Test
    void testIsEmptyInitially() {
        AVL tree = new AVL();
        assertTrue(tree.isEmpty());
    }

    @Test
    void testAddFirstElement() {
        AVL tree = new AVL();
        tree.add(10);
        assertFalse(tree.isEmpty());
        assertEquals(10, tree.root.value);
        assertEquals(0, tree.size);
    }

    @Test
    void testAddMultipleElements() {
        AVL tree = new AVL();
        tree.add(10);
        tree.add(5);
        tree.add(15);

        assertEquals(2, tree.size);assertEquals(2, tree.size); // agora começa do -1, e após 3 inserções: -1+3 = 2
        assertEquals(10, tree.root.value);
        assertEquals(5, tree.root.left.value);
        assertEquals(15, tree.root.right.value);
    }

    @Test
    void testBalanceAfterInsertionsCausingRightRotation() {
        AVL tree = new AVL();
        tree.add(30);
        tree.add(20);
        tree.add(10); // Deve causar rotação à direita

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    void testBalanceAfterInsertionsCausingLeftRotation() {
        AVL tree = new AVL();
        tree.add(10);
        tree.add(20);
        tree.add(30); // Deve causar rotação à esquerda

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    void testBalanceAfterInsertionsCausingLeftRightRotation() {
        AVL tree = new AVL();
        tree.add(30);
        tree.add(10);
        tree.add(20); // Deve causar rotação dupla esquerda-direita

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    void testBalanceAfterInsertionsCausingRightLeftRotation() {
        AVL tree = new AVL();
        tree.add(10);
        tree.add(30);
        tree.add(20); // deve causar rotação dupla direita-esquerda

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    void testNodeBalanceLogic() {
        Node node = new Node(10);
        node.left = new Node(5);
        node.left.parent = node;

        assertTrue(node.isBalanced()); // com um filho à esquerda, altura 1
    }

    @Test
    void testNodeHeightAndBalance() {
        Node root = new Node(10);
        Node left = new Node(5);
        Node right = new Node(15);
        root.left = left;
        root.right = right;
        left.parent = root;
        right.parent = root;

        assertEquals(1, root.height());
        assertEquals(0, root.balance());
    }

    @Test
    public void testTreeImbalanceDetection() {
        AVL tree = new AVL();
        tree.add(30);
        tree.add(20);

        Node node10 = new Node(10);
        Node node20 = tree.root.left;
        node20.left = node10;
        node10.parent = node20;

        Node unbalanced = tree.checkBalance(node10);
        assertNotNull(unbalanced);
        assertEquals(30, unbalanced.value);
    }

    @Test
    public void testAddSingleNode() {
        AVL tree = new AVL();
        tree.add(10);
        assertEquals(10, tree.root.value);
        assertTrue(tree.root.isLeaf());
    }

    @Test
    public void testLeftLeftRotation() {
        AVL tree = new AVL();
        tree.add(30);
        tree.add(20);
        tree.add(10); // causa rotação simples à direita (LL)

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    public void testRightRightRotation() {
        AVL tree = new AVL();
        tree.add(10);
        tree.add(20);
        tree.add(30); // causa rotação simples à esquerda (RR)

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    public void testLeftRightRotation() {
        AVL tree = new AVL();
        tree.add(30);
        tree.add(10);
        tree.add(20); // causa rotação dupla (LR)

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    public void testRightLeftRotation() {
        AVL tree = new AVL();
        tree.add(10);
        tree.add(30);
        tree.add(20); // causa rotação dupla (RL)

        assertEquals(20, tree.root.value);
        assertEquals(10, tree.root.left.value);
        assertEquals(30, tree.root.right.value);
    }

    @Test
    public void testIsNotEmptyAfterAdd() {
        AVL tree = new AVL();
        tree.add(5);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void testAddMultipleNodesWithoutError() {
        AVL tree = new AVL();
        int[] elements = {50, 20, 70, 10, 30, 60, 80};
        for (int e : elements) {
            tree.add(e);
        }

        assertEquals(50, tree.root.value); // árvore deve estar balanceada
    }
}
