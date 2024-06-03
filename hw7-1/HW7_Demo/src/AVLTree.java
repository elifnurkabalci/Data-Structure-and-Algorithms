public class AVLTree {
    public class Node {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }

        public void setHeight(int h) {
            height = h;
        }

        public int getHeight() {
            return height;
        }
    }

    public Node root; /* initial node */
    private int nodeCounter; /* for graphics part x axis */

    AVLTree() {
        /* Constructor , initial declarations */
        root = null;
        nodeCounter = 0;
    }

    /**
     * @param stock
     * @return int
     */
    public int get_nodeCounter() { /* getter */
        return nodeCounter;
    }

    // Insertion
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private int getHeight(Node node) { /* if node is not null get height */
        return node == null ? 0 : node.getHeight();
    }

    private Node insert(Node node, Stock stock) {
        if (node == null) { /* if node is empty write there the stock */
            nodeCounter++; /* increase number of nodes */
            return new Node(stock);
        }
        if (node.stock.getSymbol().compareTo(stock.getSymbol()) > 0) { /* node > stock */
            node.left = insert(node.left, stock);
        } else if (node.stock.getSymbol().compareTo(stock.getSymbol()) < 0) { /* node < stock */
            node.right = insert(node.right, stock);
        } else { /* node == stock */
            return node;
        }
        node.setHeight(1 + Math.max(getHeight(node.left), getHeight(node.right)));
        int balance = getBalance(node); /* decide which balance will make */

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0) { /* ll - right rotation */
            return rightRotation(node);
        }
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0) { /* rr - left rotation */
            return leftRotation(node);
        }
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) { /*
                                                                                            * lr - left and right
                                                                                            * rotations
                                                                                            */
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) { /*
                                                                                              * rl - right and left
                                                                                              * rotation
                                                                                              */
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    private int getBalance(Node node) {
        if (node == null) { /* if empty */
            return 0;
        }
        /* decide left and right's height's different more than 1 or not */
        return getHeight(node.left) - getHeight(node.right);
    }

    // Deletion
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    private Node findMin(Node node) {
        /* this method calling with right element in delete method */
        /* try to find min node */
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node delete(Node node, String symbol) {
        if (node == null) { /* node is empty, maybe tree is empty */
            return node;
        }
        if (node.stock.getSymbol().compareTo(symbol) > 0) { /* node > symbol */
            node.left = delete(node.left, symbol);
        } else if (node.stock.getSymbol().compareTo(symbol) < 0) { /* node < symbol */
            node.right = delete(node.right, symbol);
        } else { /* node == symbol */
            if (node.left == null) { /* left node is empty */
                return node.right;
            } else if (node.right == null) { /* right node is empty */
                return node.left;
            }
            /* left and right nodes are not empty */
            Node temp = findMin(node.right); /* dedicate min value's node */
            node.stock = temp.stock; /* write the symbol value, not swap node's */
            node.right = delete(node.right, temp.stock.getSymbol()); /* call for balancing */
            nodeCounter--; /* decrease number of nodes */
        }
        node.setHeight(1 + Math.max(getHeight(node.left), getHeight(node.right)));
        int balance = getBalance(node); /* decide which rotation will make */

        if (balance > 1 && getBalance(node.left) >= 0) { /* ll - right rotation */
            return rightRotation(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) { /* lr - left and right rotations */
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) { /* rr - left rotation */
            return leftRotation(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) { /* rl - right and left rotation */
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    // Search
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    private Node search(Node node, String symbol) {
        if (node == null) { /* send node(root) or recursive calling, if node is empty */
            return null;
        }
        if (node.stock.getSymbol().equals(symbol)) { /* found */
            return node;
        }
        if (node.stock.getSymbol().compareTo(symbol) > 0) { /* search in min nodes */
            return search(node.left, symbol);
        }
        return search(node.right, symbol); /* search in max nodes */
    }

    // Balancing methods (left rotation, right rotation, etc.)
    private Node leftRotation(Node disBalancedNode) {
        Node newNode = disBalancedNode.right;
        disBalancedNode.right = newNode.left;
        newNode.left = disBalancedNode;
        heightUpdate(disBalancedNode, newNode);
        return newNode;
    }

    /* rotate a node with O(1) time comlexity */
    private Node rightRotation(Node disBalancedNode) {
        Node newNode = disBalancedNode.left;
        disBalancedNode.left = newNode.right;
        newNode.right = disBalancedNode;
        heightUpdate(disBalancedNode, newNode);
        return newNode;
    }

    /* After making rotation, check and refresh the affected nodes heights */
    private void heightUpdate(Node disBalancedNode, Node newNode) {
        disBalancedNode.setHeight(1 + Math.max(getHeight(disBalancedNode.left), getHeight(disBalancedNode.right)));
        newNode.setHeight(1 + Math.max(getHeight(newNode.left), getHeight(newNode.right)));
    }

    /* In-order traversals */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.stock.getSymbol());
        inOrderTraversal(node.right);
    }

    /* Pre-order Traversal */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.stock.getSymbol());
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /* Post-order Traversal */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.stock.getSymbol());
    }
}
