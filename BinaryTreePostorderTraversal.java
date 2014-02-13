import java.util.ArrayList;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private void postorderTraversal(TreeNode root, ArrayList<Integer> traversal) {
        if(root == null) return;
        postorderTraversal(root.left, traversal);
        postorderTraversal(root.right, traversal);
        traversal.add(root.val);
    }
    
    // recursive
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        postorderTraversal(root, traversal);
        return traversal;
    }

    // iteratively
    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        TreeNode pointer = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || pointer != null) {
            if(pointer != null) {
                while(pointer != null) {
                    stack.push(pointer);
                    pointer = pointer.left;
                }
                pointer = stack.peek().right;
            }
            else {
                TreeNode node = stack.pop();
                traversal.add(node.val);
                if(!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if(parent.left == node) {
                        pointer = parent.right;
                    }
                }
            }
        }
        return traversal;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(solution.postorderTraversal2(root));
    }
}