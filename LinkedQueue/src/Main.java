import queue.LinkedQueue;

public class Main {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        int[] b = new int[10];

        int l = 2;
        int r = 5;
        int n = a.length;

        System.arraycopy(a, l, b, 0, Math.min(n - l, (r - l + n) % n));
        System.arraycopy(a, 0, b, Math.min(n - l, (r - l + n) % n), (r - l + n) % n - Math.min(n - l, (r - l + n) % n));

        for (int el : b)
            System.out.print(el + " ");
    }
}
