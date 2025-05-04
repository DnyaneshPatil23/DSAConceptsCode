public class AVLTreeOperations {
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node z) {
        Node y = z.left;
        Node T3 = y.right;

        // Perform rotation
        y.right = z;
        z.left = T3;

        // Update heights
        z.height = max(height(z.left), height(z.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }


    Node leftRotate(Node z) {
        Node y = z.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = z;
        z.right = T2;

        // Update heights
        z.height = max(height(z.left), height(z.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }


    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // Duplicate keys not allowed
        // Update height
        node.height = 1 + max(height(node.left), height(node.right));

        // Get balance factor
        int balance = getBalance(node);

        // Balance the tree
        if (balance > 1 && key < node.left.key)
            return rightRotate(node); // LL
        if (balance < -1 && key > node.right.key)
            return leftRotate(node); // RR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node); // LR
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node); // RL
        }
        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node balanceNode(Node root) {
        // Update height
        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node deleteNode(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            // Node with one or no child
            if (root.left == null || root.right == null) {
                root = (root.left != null) ? root.left : root.right;
            } else {
                // Node with two children
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node
        if (root == null)
            return root;

        // Update height and balance
        return balanceNode(root);
    }

    void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.key + " ");
        inOrder(root.right);
    }
}
