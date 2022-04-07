package jianzhi;

import java.util.*;

public class day3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList();
        queue.add(root);
        TreeNode temp;

        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList();
            for (int i = queue.size(); i > 0; i--) {
                temp = queue.pollFirst();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
