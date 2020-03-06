package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    public void enqueueImpl(Object element) {
        tail = new Node(element, tail);
        if (size() == 0)
            head = tail;
    }

    public Object elementImpl() {
        return head.value;
    }

    public void dequeueImpl() {
        head = head.next;
    }

    public void clearImpl() {
        head = tail = null;
    }

    @Override
    protected Queue clone() {
        LinkedQueue result = new LinkedQueue();
        Node temp = head;
        while (temp != null) {
            result.enqueue(temp.value);
            temp = temp.next;
        }
        return result;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node prev) {
            if (size() > 0)
                prev.next = this;
            this.value = value;
        }
    }
}
