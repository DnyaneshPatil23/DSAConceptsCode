import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) {

        AVLTreeOperations tree = new AVLTreeOperations();
        Node root = null;

        while(true) {
            System.out.println("Select from below options");
            System.out.println("1. Build New Tree");
            System.out.println("2. Insert");
            System.out.println("3. Delete");
            System.out.println("4. Print");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);

            switch (scanner.nextInt()) {
                case 1:
                    root = null;
                    System.out.println("New tree created, You can start inserting values");
                    break;
                case 2:
                    System.out.print("Enter value to insert: ");
                    int valueToInsert = scanner.nextInt();
                    root = tree.insert(root, valueToInsert);
                    System.out.println(valueToInsert + " inserted");
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    int valueToDelete = scanner.nextInt();
                    root = tree.deleteNode(root, valueToDelete);
                    System.out.println(valueToDelete + " deleted");
                    break;
                case 4:
                    tree.inOrder(root);
                    System.out.println("Tree printed");
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}