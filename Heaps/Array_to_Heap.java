package Heaps;

import java.util.Arrays;

public class Array_to_Heap {

    public static void main(String[] args) {
       int[] arr = {9, 5, 6, 2, 3, 7, 1, 4, 8};
       System.out.println("Original Array: " + Arrays.toString(arr));
       int[] minHeap = arrayToMinHeap(arr);
       System.out.println("Min-Heap Array: " + Arrays.toString(minHeap));

    }

    public static void heapifyDown(int[] heap , int n , int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if (left < n && heap[left] < heap[smallest]) {
            smallest = left;
        }
        
        if (right < n && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if(smallest != index) {
            int temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(heap , n , smallest);
        }
    }

    public static int[] arrayToMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
        
        return arr;
    }
    
}
