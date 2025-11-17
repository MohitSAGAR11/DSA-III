package Binary_Trees;
import java.util.PriorityQueue;

public class mergeRopes {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 6};
        System.out.println(minCost(arr));
    }

    public static int minCost(int[] arr) {
        int cost = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int a : arr) {
            q.add(a);
        }

        while (q.size() > 1) {
            int a = q.poll();
            int b = q.poll();

            int merged = a + b;
            cost += merged;

            q.add(merged);

        }

        return cost;

    }
}
