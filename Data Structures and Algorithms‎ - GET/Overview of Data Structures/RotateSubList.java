
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; this.next = null; }
}

public class RotateSubList {
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static ListNode rotateSublist(ListNode head, int L, int R, int N) {
        if (head == null || L >= R) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preL = dummy;
        for (int i = 1; i < L; i++) preL = preL.next;
        
        ListNode subStart = preL.next, subEnd = subStart;
        for (int i = L; i < R; i++) subEnd = subEnd.next;
        
        ListNode postR = subEnd.next;
        subEnd.next = null;
        
        ListNode newStart = rotateRight(subStart, N);
        preL.next = newStart;
        while (newStart.next != null) newStart = newStart.next;
        newStart.next = postR;
        
        return dummy.next;
    }
    
    private static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        
        k = k % len;
    
        if (k == 0){
             return head;
        }
        
        ListNode prev = head;
        for (int i = 0; i < len - k - 1; i++) {
            prev = prev.next;
        }
            
        
        ListNode newHead = prev.next;
        prev.next = null;
        tail.next = head;
        
        return newHead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(7);
        printList(head);
        
        head = rotateSublist(head, 2, 5, 8);
        printList(head);
    }
}
