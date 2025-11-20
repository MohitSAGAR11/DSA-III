package Heaps;

import java.util.PriorityQueue;

public class Sort_Ksorted_array {
    public static void main(String[] args) {

        int[] arr = { 3, 2, 1, 5, 4, 7, 6, 5 };
        int k = 3;
        int[] sortedArr = sortKSortedArray(arr, k);
        System.out.println("Sorted array: ");
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
    }

    // we been given a k sorted array we have to sort it completely
    // k - sorted : an array is said to be k sorted if every element is at most k
    // distance away from its target position in the sorted array
    // tc = O(n log k) sc = O(k)

    public static int[] sortKSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = arr.length;
        int[] sortedArr = new int[n];
        int index = 0;

        for (int i = 0; i <= k && i < n; i++) {
            minHeap.offer(arr[i]);
        }
        for (int i = k + 1; i < n; i++) {
            sortedArr[index++] = minHeap.poll();
            minHeap.offer(arr[i]);
        }
        while (!minHeap.isEmpty()) {
            sortedArr[index++] = minHeap.poll();
        }

        return sortedArr;
    }

    // now change in original array
    public static void sortKSortedArrayInPlace(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = arr.length;
        int index = 0;

        for (int i = 0; i <= k && i < n; i++) {
            minHeap.offer(arr[i]);
        }
        for (int i = k + 1; i < n; i++) {
            arr[index++] = minHeap.poll();
            minHeap.offer(arr[i]);
        }
        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }
}
