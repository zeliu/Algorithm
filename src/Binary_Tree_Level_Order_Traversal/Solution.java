package Binary_Tree_Level_Order_Traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.org.apache.bcel.internal.generic.RETURN;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
 

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null)
        	return result;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<Integer> values = new LinkedList<Integer>();
        values.add(root.val);
        result.add(values);
        while(!queue.isEmpty()) {
        	List<Integer> line = new LinkedList<Integer>();
        	Queue<TreeNode> temp = new LinkedList<TreeNode>(queue);
        	queue.clear();
        	while(!temp.isEmpty()) {
        		TreeNode node = temp.poll();
        		if(node == null)
        			break;
        		if(node.left != null) {
        			queue.add(node.left);
        			line.add(node.left.val);
        		}
        		if(node.right != null) {
        			queue.add(node.right);
        			line.add(node.right.val);
        		}
        	}
        	if(!line.isEmpty()) {
        		result.add(line);
        	}
        }
        return result;
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	
    	node1.left = node2;
    	node1.right = node3;
    	node2.left = node2.right = null;
    	
    	node3.left = node4;
    	node3.right = null;
    	
    	node4.left = null;
    	node4.right = node5;
    	
    	node5.left = node5.right = null;
    	
    	Solution solution = new Solution();
    	System.out.println(solution.levelOrder(node1));
    }
}
