import java.util.List;

public class LinkedList {
    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
    public ListNode head;
    public ListNode cur;
    public void add(int value) {
        if (head == null) {
            head = new ListNode(value);
            cur = head;
        } else {
            cur.next = new ListNode(value);
            cur = cur.next;
        }
    }
    public void print(ListNode node) {
        if (node == null) {
            return ;
        }
        cur = node;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }
    public ListNode init() {
        for (int i = 1; i < 8; i++) {
            this.add(i);
        }
        return head;
    }

    public ListNode reverse_iterative(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public ListNode reverse_recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse_recursion(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // Write your solution here
        if (head == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            ListNode next = cur.next;
            if (count == k) {
                prev = reverse(prev, next);
                count = 0;
            }
            cur = next;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode prev, ListNode next) {
        ListNode last = prev.next;
        ListNode cur = last.next;
        while (cur != next) {
            last.next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = last.next;
        }
        return last;
    }

    public static void main(String[] args) {
        LinkedList test = new LinkedList();
        ListNode head = test.init();
        ListNode res = test.reverseKGroup(head, 3);
        test.print(res);
    }
}
