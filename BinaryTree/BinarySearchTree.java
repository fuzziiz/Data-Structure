import java.util.*;

class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}

class Tree {
    Node root;

    Tree() {
        root = null;
    }

    // INSERT METHOD
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    // INORDER TRAVERSAL
    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // PREORDER TRAVERSAL
    void preorder() {
        preorderRec(root);
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // POSTORDER TRAVERSAL
    void postorder() {
        postorderRec(root);
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // SEARCH METHOD
    Node search(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (key < root.key)
            return search(root.left, key);

        return search(root.right, key);
    }

    // DELETE METHOD
    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    // FINDS THE MINIMUM VALUE IN THE SUBTREE
    int minValue(Node root) {
        // STORES THE MIN VALUE
        int minValue = root.key;
        // TRAVERSE TO THE LEFTMOST NODE TO FIND THE MIN VALUE
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }
    
    // CHECKS IF THE TREE IS EMPTY
    boolean isEmpty() {
        return root == null;
    }

    // CALCULATES THE HEIGHT OF A SUBTREE
    int height(Node root) {
        // IF THE SUBTREE IS EMPTY IT WILL RETURN 0 AS ITS HEIGHT.
        if (root == null)
            return 0;
        else {
            int leftHeight = height(root.left); // CALCULATES THE HEIGHT OF THE LEFT SUBTREE
            int rightHeight = height(root.right); // CALCULATES THE HEIGHT OF THE RIGHT SUBTREE

            // RETURNS THE MAXIMUM HEIGHT OF THE LEFT OR RIGHT SUBTREE
            if (leftHeight > rightHeight)
                return (leftHeight + 1); // PLUS 1 FOR THE CURRENT ROOT
            else
                return (rightHeight + 1);
        }
    }

    // ALLOWS THE USER TO VIEW THE CURRENT TREE
    void levelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        System.out.print("Current Tree: ");
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.key + " ");

            if (tempNode.left != null)
                queue.add(tempNode.left);

            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
        System.out.println();
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();

        System.out.println("---- Binary Search Tree ----");
        System.out.print("Add numbers: ");
        String[] randomNumbers = scanner.nextLine().split(" ");
        for (String num : randomNumbers) {
            try {
                int value = Integer.parseInt(num);
                tree.insert(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + num + " - You can add a node using the Insert Node.");
            }
        }

        int choice = 0;
        do { // MAIN MENU
            System.out.println("\nBinary Search Tree Operations:");
            System.out.println("1. Insert Node");
            System.out.println("2. Traverse Tree");
            System.out.println("3. Search Node");
            System.out.println("4. Delete Node");
            System.out.println("5. View Current Structure");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            switch (choice) {
                case 1: // INSERTS A NODE INTO THE TREE
                    System.out.print("Enter value to insert: ");
                    int value = scanner.nextInt();
                    tree.insert(value);
                    break;
                case 2: // TRAVERSE THE TREE
                    if (tree.isEmpty()) {
                        System.out.println("Tree is empty! Please insert nodes first.");
                    } else {
                        int traverseChoice;
                        do { // SUBMENU FOR TRAVERSING THE TREE
                            System.out.println("\nTraverse Tree:");
                            System.out.println("1. Inorder");
                            System.out.println("2. Preorder");
                            System.out.println("3. Postorder");
                            System.out.print("Enter your choice: ");
                            traverseChoice = scanner.nextInt();
                            switch (traverseChoice) {
                                case 1:
                                    System.out.print("Inorder traversal: ");
                                    tree.inorder();
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.print("Preorder traversal: ");
                                    tree.preorder();
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.print("Postorder traversal: ");
                                    tree.postorder();
                                    System.out.println();
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                                    break;
                            }
                        } while (traverseChoice < 1 || traverseChoice > 3);
                    }
                    break;
                case 3: // SEARCH FOR A SPECIFIC NODE IN THE TREE
                    if (tree.isEmpty()) {
                        System.out.println("Tree is empty! Please insert nodes first.");
                    } else {
                        System.out.print("Enter value to search: ");
                        int searchValue = scanner.nextInt();
                        Node found = tree.search(tree.root, searchValue);
                        if (found != null) {
                            System.out.println("Node found: " + found.key);
                        } else {
                            System.out.println("Node not found!");
                        }
                    }
                    break;
                case 4: // DELETES A NODE FROM THE TREE
                    if (tree.isEmpty()) {
                        System.out.println("Tree is empty! Please insert nodes first.");
                    } else {
                        System.out.print("Enter value to delete: ");
                        int deleteValue = scanner.nextInt();
                        tree.delete(deleteValue);
                        System.out.println("Node deleted!");
                    }
                    break;
                case 5: // VIEW THE CURRENT STRUCTURE OF THE TREE
                    if (tree.isEmpty()) {
                        System.out.println("Tree is empty!");
                    } else {
                        tree.levelOrder();
                    }
                    break;
                case 6: // EXIT THE PROGRAM
                    System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }
}
