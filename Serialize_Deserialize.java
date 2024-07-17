import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
    }
}
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        
        // Remove the trailing comma
        sb.setLength(sb.length() - 1);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();
            
            if (!nodes[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                current.left = left;
                queue.add(left);
            }
            i++;
            
            if (i < nodes.length && !nodes[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                current.right = right;
                queue.add(right);
            }
            i++;
        }
        
        return root;
    }
}
public class Serialize_Deserialize {
    public static void main(String[] args) {
        Codec codec = new Codec();
        
        // Create a sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);
        
        // Deserialize the string back to tree
        TreeNode deserializedRoot = codec.deserialize(serialized);
        
        // Print the deserialized tree in level-order to verify
        System.out.println("Deserialized tree in level-order: ");
        printLevelOrder(deserializedRoot);
    }
    
    // Helper function to print the tree in level-order
    private static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }
}
