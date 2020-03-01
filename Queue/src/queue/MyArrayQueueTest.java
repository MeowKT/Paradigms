package queue;

public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 1);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.print("Queue: ");
            for (int i = 0; i < queue.size(); i++) {
                System.out.print(queue.get(i) + " ");
            }

            System.out.println();

            if (Math.random() >= 0.5) {
                int ind = (int)(Math.random() * queue.size());
                int x = (int)(Math.random() * 1e9);
                System.out.println("Set " + ind + " Val " + x + ": ");
                queue.set(ind, x);
                System.out.print("Queue: ");
                for (int i = 0; i < queue.size(); i++) {
                    System.out.print(queue.get(i) + " ");
                }
                System.out.println();
            }

            System.out.println("size : " + queue.size() + "\ntail element: " +
                               queue.peek() + "\nhead element: " +
                               queue.element() + "\n" +
                    (queue.size() % 2 == 0 ? "Delete tail: " + queue.dequeue() : "Delete head: " + queue.remove()) + " ");
            System.out.println();

        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
    }

}
