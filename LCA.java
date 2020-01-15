import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LCA {
    public class TreeNode {
        int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public class TreeNodep {
        int val;
        public TreeNodep left;
        public TreeNodep right;
        public TreeNodep parent;
        TreeNodep(int val, TreeNodep parent) {
            this.val = val;
            this.parent = parent;
        }
    }
    public class KnaryTreeNode {
        int key;
        List<KnaryTreeNode> children;
        public KnaryTreeNode(int key) {
            this.key = key;
            this.children = new ArrayList<>();
        }
    }


    // two nodes in a binary tree, find the lca.
    public TreeNode LCA1(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode left = LCA1(root.left, one, two);
        TreeNode right = LCA1(root.right, one, two);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // two nodes in a bst, find the lca.
    public TreeNode LCA2(TreeNode root, TreeNode one, int p, int q) {
        int min = Math.min(p, q);
        int max = Math.max(p, q);
        while (root != null) {
            if (root.val < min) {
                root = root.right;
            } else if (root.val > max) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    // two nodes in a binary tree which has parent node, find the lca
    public TreeNodep LCA3(TreeNodep one, TreeNodep two) {
        if (one == null || two == null) {
            return null;
        }
        int len1 = length(one);
        int len2 = length(two);
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                one = one.parent;
            }
            if (one == two) {
                return one;
            }
        }
        if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++) {
                two = two.parent;
            }
            if (one == two) {
                return one;
            }
        }
        while (one != null || two != null) {
            if (one == two) {
                return one;
            } else {
                one = one.parent;
                two = two.parent;
            }
        }
        return null;
    }
    private int length (TreeNodep node) {
        int res = 0;
        while (node != null) {
            node = node.parent;
            res++;
        }
        return res;
    }

    //Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).
    //Return null If any of the nodes is not in the tree.
    public TreeNode LCA7(TreeNode root, TreeNode one, TreeNode two) {
        // write your solution here
        if (root == null || one == null || two == null) {
            return null;
        }
        TreeNode candidate = helper(root, one, two);
        if (candidate == one) {
            return helper(one, two, two) == null ? null : one;
        } else if (candidate == two) {
            return helper(two, one, one) == null ? null : two;
        } else {
            return candidate;
        }
    }
    private TreeNode helper(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode left = helper(root.left, one, two);
        TreeNode right = helper(root.right, one, two);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // k nodes in a binary tree, find the lca
    public TreeNode LCA4(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>(nodes);
        return helper4(root, set);
    }
    private TreeNode helper4(TreeNode root, Set<TreeNode> set) {
        if (root == null) {
            return null;
        }
        if (set.contains(root)) {
            return root;
        }
        TreeNode left = helper4(root.left, set);
        TreeNode right = helper4(root.right, set);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // two nodes in a K-nary tree, find the lca
    public KnaryTreeNode LCA5(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode node : root.children) {
            KnaryTreeNode cur = LCA5(node, a, b);
            if (cur == null) {
                continue;
            } else if (found == null) {
                found = cur;
            } else {
                return root;
            }
        }
        return found;
    }

    // M nodes in a K-nary tree, find the lca
    public KnaryTreeNode LCA6(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return helper5(root, set);
    }
    private KnaryTreeNode helper5(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode node : root.children) {
            KnaryTreeNode cur = helper5(node, set);
            if (cur == null) {
                continue;
            } else if (found == null) {
                found = cur;
            } else {
                return root;
            }
        }
        return found;
    }
}
