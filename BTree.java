import java.util.*;
import java.util.LinkedList;

public class BTree<E> {
    static class Pair {
        Node node;
        int index;
        Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public static class Node<E> {
        Node<E> left = null;    //左子树
        Node<E> right = null;   //右子树
        E data = null;          //数据域

        //初始化节点
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node() {

        }
    }

    private Node<E> root = null;        //根节点
    private List<Node<E>> list = null;  //节点列表，用于将数组元素转化为节点

    public Node<E> getRoot() {
        return this.root;
    }

    //将将数组转化为一颗二叉树，转换规则：依次为节点列表中节点的左右孩子赋值。左孩子为i*2+1,右孩子为i*2+2
    @SuppressWarnings("unchecked")
    public void createTreeByArray(Object[] array) {
        this.list = new ArrayList<Node<E>>();

        //将数组添加到节点列表
        for (int i = 0; i < array.length; i++) {
            list.add(new Node<E>((E) array[i]));
        }

        System.out.println("头节点->" + list.get(0).data);
        this.root = new Node<E>(list.get(0).data); //根节点

        //为二叉树指针赋值
        for (int j = 0; j < (list.size() / 2); j++) {
            try {
                //为左子树赋值  j*2+1
                list.get(j).left = list.get(j * 2 + 1);
                System.out.println("节点" + list.get(j).data + "左子树 ->" + list.get(j * 2 + 1).data);
                //为右子树赋值 j*2+2
                list.get(j).right = list.get(j * 2 + 2);
                System.out.println("节点" + list.get(j).data + "右子树 ->" + list.get(j * 2 + 2).data);
            } catch (Exception e) {

            }
        }

    }

    //先序遍历二叉树
    public void Indorder(Node<E> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        Indorder(root.left);  //递归输出左子树
        Indorder(root.right); //递归遍历右子树
    }

    //中序遍历二叉树
    public void inOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);  //遍历左子树
        System.out.println(root.data);
        inOrderTraverse(root.right); //遍历右子树
    }

    //后序遍历
    public void postOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);  //遍历左子数节点
        postOrderTraverse(root.right); //遍历右子树节点
        System.out.println(root.data); //从下往上遍历
    }


    public static void main(String[] args) {
        BTree<Integer> createTreeByArray = new BTree<>();
        Object[] arrays = {1,2,null,3};
        createTreeByArray.createTreeByArray(arrays);
        System.out.println("===============================");
        Node head = createTreeByArray.toDoubleLinkedList(createTreeByArray.list.get(0));
        List<Integer> res = createTreeByArray.borderView(head);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public List<Integer> borderView(Node root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> leftView = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();
        List<Integer> botView = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node cur = queue.poll();
                if (i == 0) {
                    leftView.add((Integer) cur.data);
                }
                if (i == len - 1) {
                    rightView.add((Integer) cur.data);
                }
                if (cur.left == null && cur.right == null) {
                    botView.add((Integer) cur.data);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        Collections.reverse(rightView);
        res = getRes(leftView, rightView, botView);
        return res;
    }

    private List<Integer> getRes(List<Integer> leftView, List<Integer> rightView, List<Integer> botView) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int left = 0;
        int bot = 0;
        while (leftView.get(left) != botView.get(0)){
            if (set.add(leftView.get(left))) {
                res.add(leftView.get(left++));
            }
        }
        while (botView.get(bot) != rightView.get(0)) {
            if (set.add(botView.get(bot))) {
                res.add(botView.get(bot++));
            }
        }
        for (int i = 0; i < rightView.size(); i++) {
            if (set.add(rightView.get(i))) {
                res.add(rightView.get(i));
            }
        }
        return res;
    }

    private Node prev = null;
    private Node head = null;
    public Node toDoubleLinkedList(Node root) {
        // Write your solution here.
        inOrder(root);
        return head;
    }
    private void inOrder(Node root) {
        if (root == null) {
            return ;
        }
        inOrder(root.left);
        if (head == null) {
            prev = root;
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        inOrder(root.right);
    }
}
