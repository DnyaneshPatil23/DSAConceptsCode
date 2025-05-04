public class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        // new node is initially
        // added at leaf
        height = 1;
    }
}
