package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class K_smallest {
    public static void main(String[] args) {
        int[] arr = { 7, 10, 4, 3, 20, 15 };
        int k = 3;
        // int[] kthSmallest = findKthSmallest(arr, k);
        int[] kthSmallest = findkthSmallestOptimized(arr, k);
        // int[] kthSmallest = find(arr, k);
        System.out.println(k + "smallest element are: " + Arrays.toString(kthSmallest));
    }

    // tc : O(n log n)
    // sc : O(n)
    public static int[] findKthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] A = new int[k];

        for (int num : arr) {
            minHeap.offer(num);
        }

        for (int i = 0; i < k; i++) {
            A[i] = minHeap.poll();
        }

        return A;
    }

    // we take the first k elements as ans then we traverse the rest of the array
    // if any element is smaller than the largest element in ans we replace it
    // tc : O(n*k)
    // sc : O(k)
    public static int[] find(int[] arr, int k) {
        int[] A = Arrays.copyOfRange(arr, 0, k);

        for (int i = k; i < arr.length; i++) {
            int maxIndex = 0;
            for (int j = 1; j < k; j++) {
                if (A[j] > A[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (arr[i] < A[maxIndex]) {
                A[maxIndex] = arr[i];
            }
        }

        return A;
    }

    // more optimized approach
    // tc : O(n log k)
    // sc : O(k)
    public static int[] findkthSmallestOptimized(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int[] A = new int[k];

        for (int i = 0; i < arr.length; i++) {
            maxHeap.offer(arr[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            A[i] = maxHeap.poll();
        }

        return A;
    }

    // different using maxHeap
    public static int[] findkthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int[] A = new int[k];

        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            A[i] = maxHeap.poll();
        }

        return A;

    }

}
