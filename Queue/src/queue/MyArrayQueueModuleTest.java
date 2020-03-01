package queue;

public class MyArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i + 1);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.print("Queue: ");
            for (int i = 0; i < ArrayQueueModule.size(); i++) {
                System.out.print(ArrayQueueModule.get(i) + " ");
            }

            System.out.println();

            if (Math.random() >= 0.5) {
                int ind = (int)(Math.random() * ArrayQueueModule.size());
                int x = (int)(Math.random() * 1e9);
                System.out.println("Set " + ind + " Val " + x + ": ");
                ArrayQueueModule.set(ind, x);
                System.out.print("Queue: ");
                for (int i = 0; i < ArrayQueueModule.size(); i++) {
                    System.out.print(ArrayQueueModule.get(i) + " ");
                }
                System.out.println();
            }

            System.out.println("size : " + ArrayQueueModule.size() + "\ntail element: " +
                    ArrayQueueModule.peek() + "\nhead element: " +
                    ArrayQueueModule.element() + "\n" +
                    (ArrayQueueModule.size() % 2 == 0 ? "Delete tail: " + ArrayQueueModule.dequeue() : "Delete head: " + ArrayQueueModule.remove()) + " ");
            System.out.println();

        }
    }

    public static void main(String[] args) {
        fill();
        dump();
    }

}
