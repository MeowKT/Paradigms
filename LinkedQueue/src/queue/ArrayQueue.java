package queue;

// n - count elements
// a - sequence


public class ArrayQueue extends AbstractQueue implements Queue {
    private Object[] elements;
    private int head;


    public ArrayQueue() {
        head = size = 0;
        elements = new Object[1];
    }

    public void enqueueImpl(Object x) {
        ensureCapacity(size() + 1);
        elements[(head + size) % elements.length] = x;
    }

    public Object elementImpl() {
        return elements[head];
    }

    public void dequeueImpl() {
        elements[head] = null;
        head = next(head);
    }

    private void ensureCapacity(int size) {
        if (size == elements.length) {
            Object[] arr = new Object[2 * elements.length];
            int sz = 0;
            for (int i = 0; i < size; i++) {
                arr[sz++] = elements[head];
                head = next(head);
            }
            elements = arr;
            head = 0;
        }
    }

    public void clearImpl() {
        head = 0;
        elements = new Object[1];
    }

    private int next(int x) {
        return (x + 1) % elements.length;
    }
}
