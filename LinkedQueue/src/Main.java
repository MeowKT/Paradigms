import queue.LinkedQueue;

public class Main {

    public static void main(String[] args) {
        LinkedQueue q = new LinkedQueue();
        q.enqueue("123");
        System.out.println(q.element());
    }
}
