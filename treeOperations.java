import java.util.*;
import java.util.LinkedList;

public class treeOperations {
    public static void main(String []args){
        treeOperations test = new treeOperations();
    }
    public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
            this.key = key;
        }
    }
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return res;
        }
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if (cur != null) {
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                }
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                }
                res.add(cur.key);
            }
        }
        return res;
    }
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                res.add(cur.key);
                cur = cur.right;
            }
        }
        return res;
    }
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return res;
        }
        stack.offerFirst(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (prev == null || cur == prev.left || cur == prev.right) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    stack.pollFirst();
                }
            } else if (prev == cur.left) {
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    stack.pollFirst();
                }
            } else {
                res.add(cur.key);
                stack.pollFirst();
            }
            prev = cur;
        }
        return res;
    }
    //BST的删除
    public TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key == root.key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else if (root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {    //找到代替被删除节点的最小右节点
                        //将root.left和代替节点左相连，right同理
                TreeNode subSmallest = deleteSmallest(root.right);
                subSmallest.left = root.left;
                subSmallest.right = root.right;
                return subSmallest;
            }
        }
        //这里就是删除节点的过程
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        }

        return root;
    }
    //找到最左边的叶子节点，因为他是没有左叶子节点的，最多只有右叶子节点
    //把右叶子节点接到父节点的左节点，即删除最小的叶子节点
    public TreeNode deleteSmallest(TreeNode root) {
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode subSmallest = root.left;
        root.left = root.left.right;
        return subSmallest;
    }
}
