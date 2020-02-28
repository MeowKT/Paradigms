package queue;

public class ArrayQueueModule {
    private static Object elements[] = new Object[1];
    private static int tail = 0, head = 0;

    public static void enqueue(Object x) {
        assert x != null;

        checkSize(next(tail));
        elements[tail] = x;
        tail = next(tail);
    }

    private static void checkSize(int next) {
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

    private static int next(int x) {
        return (x + 1) % elements.length;
    }

    public static Object element() {
        assert tail != head;

        return elements[head];
    }

    public static Object dequeue() {
        Object x = elements[head];
        head = next(head);
        return x;
    }

    public static int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else {
            return tail - head;
        }
    }

    public static boolean isEmpty() {
        return tail == head;
    }

    public static void clear() {
        tail = head = 0;
        elements = new Object[1];
    }

}
