package queue;

public class ArrayQueue {
    private Object elements[] = new Object[1];
    private int tail = 0, head = 0;

    public void enqueue(Object x) {
        checkSize(next(tail));
        elements[tail] = x;
        tail = next(tail);
    }

    private void checkSize(int next) {
        if (next == head) {
            Object[] arr = new Object[2 * elements.length];
            int sz = 0;
            while (tail != head) {
                arr[sz++] = elements[head];
                head = next(head);
            }
            arr[sz++] = elements[head];
            elements = arr;
            head = 0;
            tail = sz - 1;
        }
    }

    private int next(int x) {
        return (x + 1) % elements.length;
    }

    public Object element() {
        return elements[head];
    }

    public Object dequeue() {
        Object x = elements[head];
        head = next(head);
        return x;
    }

    public int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else {
            return tail - head;
        }
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public void clear() {
        tail = head = 0;
        elements = new Object[1];
    }


}
