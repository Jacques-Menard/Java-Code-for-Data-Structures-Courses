/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;

/**
 *
 * @author Jacques Menard
 */
public class SLinkedList {

    public static void main(String[] args) {
        Node test = new Node(5);
        test.setElement(5);

        SLinkedList myList = new SLinkedList();
        System.out.println(myList.contains(5));
        myList.addFirst(6);
        myList.addFirst(4);
        myList.addFirst(8);
        myList.addLast(20);
        myList.removeOdds();
        System.out.println(myList.contains(5));
        myList.printList();
        System.out.println(myList.indexOf(5));
        System.out.println(myList.indexOf(23));
        System.out.println(myList.findKthElement(2));
        myList.editAtIndex(2, 502);
        
        SLinkedList otherList = new SLinkedList();
        SLinkedList otherList2 = new SLinkedList();
        
        SLinkedList newList = concatenateLists(myList, myList);
        newList.printList();
        
        
        int product1 = Homework5.recMultiply(-7, -5);
        System.out.println(product1);
        
        int product2 = Homework5.recMultiply(4, -5);
        System.out.println(product2);
        
        int product3 = Homework5.recMultiply(0,0);
        System.out.println(product3);
        
        int product4 = Homework5.recMultiply(7, 5);
        System.out.println(product4);
        
        int product5 = Homework5.recMultiply(0, -5);
        System.out.println(product5);
        
        
       int array1[] = {1, 2, 3, 1, 3, 6, 6};
       Homework5.printRepeats(array1);
    }

    private Node head, tail;
    private int size;

    public SLinkedList() {

    }

    public void addFirst(int element) {
        head = new Node(element, head);
        size++;
        if (size == 1) {
            tail = head;
        }
    }

    public void addLast(int element) {
        Node last = new Node(element);
        if (size == 0) {
            head = last;
            tail = last;
        } else {
            tail.setNext(last);
            tail = last;
        }
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        }
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    public void removeLast() {
        if (size <= 1) {
            head = null;
            tail = null;
            size = 0;
        } else {
            Node cur = head;
            while (cur.next != tail) {
                cur = cur.next;
            }
            tail = cur;
            tail.next = null;
            size--;
        }
    }
    
    public void removeOdds() {
        Node nodeBefore, currentNode;
        int intSize=size;
        currentNode=this.getHead();
         if (size == 0) {
            removeFirst();
        }
         if(size==1){
             if(currentNode.getElement()%2==1){
                 removeFirst();
             }
         }
         if(size>1){
             nodeBefore=currentNode;
             currentNode=currentNode.getNext();
             for(int count=0;count<intSize-1;count++){
                 if(currentNode.getElement() % 2==1){
                     nodeBefore.setNext(currentNode.getNext());
                     size--;
                 }else{
                     nodeBefore=nodeBefore.getNext();
                 }
             currentNode=currentNode.getNext();
             }
             
         }
    }
   
    public boolean contains(int key) {
        Node cur = head;
        while (cur != null && cur.getElement() != key) {
            cur = cur.next;
        }
        if (cur == null) {
            return false;
        }
        return true;
    }

    public int indexOf(int key) {
        Node cur = head;
        int index = 0;
        while (cur != null) {
            if (cur.getElement() == key) {
                return index;
            }
            cur = cur.next;
            index++;
        }
        return -1;
    }
    // return -1 if not found
    public int findKthElement(int k){
        Node cur = head;
        while (cur!=null && k>0){
            cur = cur.next;
            k--;
        }
        if (cur == null){
            return -1;
        }
        else{
            return cur.getElement();
        }
    }
    
    public void editAtIndex(int index, int element){
        if (index >= size || index < 0){
            return;
        }
        //Node toAdd = new Node(element);
        Node cur = head;
        while (index!=0){
            cur = cur.next;    
            index--;
        }
        cur.setElement(element);        
    }
    
    public void printList() {
        System.out.println("A list of size " + size);
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getElement() + " ");
            temp = temp.next;
        }
        System.out.println();

    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;

    }
    
    public static SLinkedList concatenateLists(SLinkedList A, SLinkedList B){
        if(A==null && B==null){
            return null;
        }
        if(A==null){
            return B;
        }
        if(B==null){
            return A;
        }
        SLinkedList C = new SLinkedList();
        Node currentNodeA = A.getHead();
        Node currentNodeB = B.getHead();
        for(int count=0;count<A.getSize();count++){
            C.addLast(currentNodeA.getElement());
            currentNodeA=currentNodeA.getNext();
        }
        for(int count=0;count<B.getSize();count++){
            C.addLast(currentNodeB.getElement());
            currentNodeB=currentNodeB.getNext();
        }
        return C;
    }

    private static class Node {

        private int element;
        private Node next;

        public Node(int element) {
            this.element = element;
        }

        public Node(int element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
            element = 0;
        }

        public int getElement() {
            return element;
        }

        public void setElement(int element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
