package queue;

// n - count elements
// a - sequence

public class ArrayQueueADT {
    /**
     * Inv :
     * n >= 0
     * and for all i : a[i] != null
     */
    private Object[] elements = new Object[1];
    private int tail = 0, head = 0;

    /**
     * Pre :
     * queue != null
     * and x != null
     *
     * Post :
     * n' = n + 1
     * and a[i]' = a[i] for i in 0..n - 1
     * and a[n]' = x
     */
    public static void enqueue(ArrayQueueADT queue, Object x) {
        assert x != null;

        resize(queue, size(queue) + 1);
        queue.elements[queue.tail] = x;
        queue.tail = next(queue, queue.tail);
    }

    /**
     * Pre:
     * queue != null
     * and x != null
     *
     * Post :
     * n' = n + 1
     * and a[i + 1]' = a[i] for i in 0..n - 1
     * and a[0]' = x
     */
    public static void push(ArrayQueueADT queue, Object x) {
        assert x != null;

        resize(queue, size(queue) + 1);
        queue.head = prev(queue, queue.head);
        queue.elements[queue.head] = x;
    }

    /**
     * Pre:
     * queue != null
     * and n > 0
     *
     * Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[0]'
     */
    public static Object element(ArrayQueueADT queue) {
        assert queue.head != queue.tail;

        return queue.elements[queue.head];
    }

    /**
     * Pre:
     * queue != null
     * and n > 0
     *
     * Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[n - 1]'
     */
    public static Object peek(ArrayQueueADT queue) {
        return queue.elements[prev(queue, queue.tail)];
    }

    /**
     * Pre:
     * queue != null
     * and n > 0
     *
     * Post:
     * n' == n - 1
     * and (a'[i - 1] == a[i] for i in 1..n - 1)
     * and (Result == a[0])
     */
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.head != queue.tail;

        Object x = element(queue);
        queue.head = next(queue, queue.head);
        return x;
    }

    /**
     * Pre:
     * queue != null
     * and size > 0
     *
     * Post:
     * n' == n - 1
     * and (a'[i] == a[i] for i = 0...n - 2)
     * and (Result == a[n - 1])
     */
    public static Object remove(ArrayQueueADT queue) {
        Object x = peek(queue);
        queue.tail = prev(queue, queue.tail);
        queue.elements[queue.tail] = null;
        return x;
    }

    /**
     * Pre:
     * queue != null
     * and ind in 0..n - 1
     *
     * Post:
     * Result = a[ind]
     * and a[i]' = a[i] for i in 0..n-1
     */

    public static Object get(ArrayQueueADT queue, int ind) {
        return queue.elements[(queue.head + ind) % queue.elements.length];
    }

    /**
     * Pre:
     * queue != null
     * and ind in 0..size
     * and el != null
     *
     * Post:
     * a[i]' = a[i] for i in 0..ind-1
     * and a[i]' = a[i] for i in ind+1..n-1
     * a[ind]' = el
     */
    public static void set(ArrayQueueADT queue, int ind, Object element) {
        queue.elements[(queue.head + ind) % queue.elements.length] = element;
    }

    /**
     * Pre:
     * queue != null
     *
     * Post:
     * Result = n
     * and n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */

    public static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail) {
            return queue.elements.length - queue.head + queue.tail;
        } else {
            return queue.tail - queue.head;
        }
    }

    /**
     * Pre:
     * queue != null
     * and sz >= 0
     *
     * Post:
     * (n' == n)
     * and (a[i]' == a[i] for i = 0...n - 1)
     */
    private static void resize(ArrayQueueADT queue, int size) {
        if (size == queue.elements.length) {
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

    /**
     * Pre:
     * queue != null
     *
     * Post:
     * Result = (size == 0)
     * n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.tail == queue.head;
    }

    /**
     * Pre:
     * queue != null
     *
     * Post:
     * n' = 0
     */
    public static void clear(ArrayQueueADT queue) {
        queue.tail = queue.head = 0;
        queue.elements = new Object[1];
    }

    /**
     * Pre:
     * queue != null
     * and (queue.elements.length != 0)
     * and (0 <= x < elements.length)
     *
     * Post:
     * Result = (x + 1) % elements.length
     */

    private static int next(ArrayQueueADT queue, int x) {
        return (x + 1) % queue.elements.length;
    }

    /**
     * Pre:
     * queue != null
     * and (queue,elements.length != 0)
     * and (0 <= x < elements.length)
     *
     * Post:
     * Result = (x - 1 + queue.elements.length) % elements.length
     */
    private static int prev(ArrayQueueADT queue, int x) {
        return (x - 1 + queue.elements.length) % queue.elements.length;
    }

}
