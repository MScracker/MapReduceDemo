package com.datamining.datastructure.basic;

/**
 * Created by wanghuali1 on 2017/3/31.
 */
public class LinkNodeTest {
    public static class LinkNode {
        public int value;
        public LinkNode next;

        public LinkNode(int value) {
            this.value = value;
        }
    }
    public static void printLink(LinkNode head) {

        while (head != null) {
            System.out.print(head.value);
            if (head.next != null){
                System.out.print("->");
            }
            head = head.next;
        }
    }


    public static LinkNode reverseLinkTable(LinkNode head) {
        if (head == null) {
            return head;
        }

        LinkNode previous = head;//保留头指针,方便断环，将头指针做前一指针
        LinkNode current = head.next;//下一指针做当前指针
        LinkNode tmp=null;

        while (current != null) {
            tmp = current.next; //保存下一节点
            current.next = previous;
            previous = current;
            current = tmp;
        }
        head.next=null; //第一次 0->1 反转的时候，并未断开0->1链接，所以必须断开头指针的指针域，否则成环0->1->0->1->0

        return previous;
    }

    //递归反转链表
    public static LinkNode recursiveRevLinkTable(LinkNode head){
        if (head == null || head.next == null){
            return head;
        }
        LinkNode previous = head; //当前列表头指针做前一指针
        LinkNode current = head.next;//下一指针看做当前指针

        LinkNode tail = recursiveRevLinkTable(current);
        current.next = previous;
        previous.next = null; //前一节点置null，很重要！！！ 否则会成环 1->0->1->1->0
        return tail;
    }

    public static void main(String[] args) {
        LinkNode head = new LinkNode(0);
        LinkNode pointer = head;
        for (int i = 1; i < 5; i++) {
            pointer.next = new LinkNode(i);
            pointer = pointer.next;
        }
        System.out.print("original link:");
        printLink(head);
        LinkNode start=reverseLinkTable(head);
        System.out.print("\nreverse link:");
        printLink(start);

    }

}
