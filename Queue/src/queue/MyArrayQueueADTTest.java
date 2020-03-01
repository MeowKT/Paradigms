package queue;

public class MyArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i + 1);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.print("Queue: ");
            for (int i = 0; i < ArrayQueueADT.size(queue); i++) {
                System.out.print(ArrayQueueADT.get(queue, i) + " ");
            }

            System.out.println();

            if (Math.random() >= 0.5) {
                int ind = (int)(Math.random() * ArrayQueueADT.size(queue));
                int x = (int)(Math.random() * 1e9);
                System.out.println("Set " + ind + " Val " + x + ": ");
                ArrayQueueADT.set(queue, ind, x);
                System.out.print("Queue: ");
                for (int i = 0; i < ArrayQueueADT.size(queue); i++) {
                    System.out.print(ArrayQueueADT.get(queue, i) + " ");
                }
                System.out.println();
            }

            System.out.println("size : " + ArrayQueueADT.size(queue) + "\ntail element: " +
                    ArrayQueueADT.peek(queue) + "\nhead element: " +
                    ArrayQueueADT.element(queue) + "\n" +
                    (ArrayQueueADT.size(queue) % 2 == 0 ? "Delete tail: " + ArrayQueueADT.dequeue(queue) : "Delete head: " + ArrayQueueADT.remove(queue)));
            System.out.println();

        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        fill(queue);
        dump(queue);
    }

}
