package queue;

public class ArrayQueueADT {
    private Object elements[] = new Object[1];
    private int tail = 0, head = 0;

    public static void enqueue(ArrayQueueADT queue, Object x) {
        assert x != null;

        checkSize(queue, next(queue, queue.tail));
        queue.elements[queue.tail] = x;
        queue.tail = next(queue, queue.tail);
    }

    private static void checkSize(ArrayQueueADT queue, int next) {
        if (next == queue.head) {
            Object[] arr = new Object[2 * queue.elements.length];
            int sz = 0;
            while (queue.tail != queue.head) {
                arr[sz++] = queue.elements[queue.head];
                queue.head = next(queue, queue.head);
            }
            arr[sz++] = queue.elements[queue.head];
            queue.elements = arr;
            queue.head = 0;
            queue.tail = sz - 1;
        }
    }

    private static int next(ArrayQueueADT queue, int x) {
        return (x + 1) % queue.elements.length;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.head != queue.tail;

        return queue.elements[queue.head];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.head != queue.tail;

        Object x = queue.elements[queue.head];
        queue.head = next(queue, queue.head);
        return x;
    }

    public static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail) {
            return queue.elements.length - queue.head + queue.tail;
        } else {
            return queue.tail - queue.head;
        }
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.tail == queue.head;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.tail = queue.head = 0;
        queue.elements = new Object[1];
    }

}
