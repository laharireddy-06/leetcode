/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // List to store (col, row, val)
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        
        // Sort by col, then row, then val
        Collections.sort(nodes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];   // col
            if (a[1] != b[1]) return a[1] - b[1];   // row
            return a[2] - b[2];                     // val
        });
        
        // Group by column
        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        for (int[] node : nodes) {
            if (node[0] != prevCol) {
                result.add(new ArrayList<>());
                prevCol = node[0];
            }
            result.get(result.size() - 1).add(node[2]);
        }
        
        return result;
    }
    
    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) return;
        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }
}
