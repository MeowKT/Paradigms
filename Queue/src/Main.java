import queue.ArrayQueueModule;

public class Main {

    public static void main(String[] args) {
        ArrayQueueModule.enqueue(1);
        ArrayQueueModule.enqueue(1);
        ArrayQueueModule.enqueue(1);
        ArrayQueueModule.enqueue(1);

        System.out.println(ArrayQueueModule.element());
        ArrayQueueModule.clear();
        ArrayQueueModule.enqueue(22);
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.dequeue());
        ArrayQueueModule.enqueue(3);
        System.out.println(ArrayQueueModule.element());
    }
}
