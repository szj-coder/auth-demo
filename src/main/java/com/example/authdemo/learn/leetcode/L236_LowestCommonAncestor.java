package com.example.authdemo.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class L236_LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        search(root, p, q, treeNodes);
        return treeNodes.get(0);
    }

    public int search(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> result) {
        if (root == null) {
            return 0;
        }
        if (!result.isEmpty()) {
            return 0;
        }
        int leftResult = search(root.left, p, q, result);
        int rightResult = search(root.right, p, q, result);

        int num = 0;
        if (root.val == p.val || root.val == q.val) {
            num = 1;
        }
        if (leftResult + rightResult + num >= 2 && leftResult < 2 && rightResult < 2) {
            result.add(root);
        }
        return leftResult + rightResult + num;
    }
}
