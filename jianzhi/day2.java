package jianzhi;

import java.util.Stack;

public class day2 {
    /**
     * 剑指 Offer 06. 从尾到头打印链表
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    /**
     * 剑指 Offer 24. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode result = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = result;
            result = current;
            current = next;
        }
        return result;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node r = head;
        Node result = new Node(head.val);
        result.random = head.random;
        Node p = result;
        while (r.next != null) {
            Node temp = new Node(r.next.val);
            temp.random = r.next.random;
            r = r.next;
            p.next = temp;
            p = temp;
        }
        return result;
    }
}
