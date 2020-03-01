package queue;

public class LinkedQueue extends AbstractQueue implements Queue {
    Node head, tail;
    int size;

    public void enqueue(Object element) {
        assert element != null;

        size++;
        tail = new Node(element, tail);
        if (head == null)
            head = tail;
    }

    public Object element() {
        return head.value;
    }

    public Object dequeue() {
        size--;
        Object x = head.value;
        head = head.next;
        return x;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node prev) {
            assert value != null;

            if (prev != null)
                prev.next = this;
            this.value = value;
        }
    }
}
