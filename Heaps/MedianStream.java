package Heaps;

import java.util.*;

/**
 * MedianFinder - Finds median from a stream of numbers using Two Heaps approach
 * 
 * Time Complexity:
 * - addNum(): O(log n) - heap insertions
 * - findMedian(): O(1) - just peek at heap tops
 * 
 * Space Complexity: O(n) - storing all numbers
 */
class MedianFinder {
    // Max heap for smaller half (left side)
    // In Java, PriorityQueue is min heap by default, so we reverse it
    PriorityQueue<Integer> maxHeap;

    // Min heap for larger half (right side)
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        // Max heap: reverse order to get largest element at top
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Min heap: natural order, smallest element at top
        minHeap = new PriorityQueue<>();
    }

    /**
     * Adds a number to the data structure
     * 
     * Strategy:
     * 1. Add to max heap (smaller half)
     * 2. Move max heap's top to min heap (ensures order)
     * 3. Balance: if min heap becomes larger, move back to max heap
     * 
     * This maintains:
     * - maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1
     * - All elements in maxHeap <= all elements in minHeap
     */
    public void addNum(int num) {
        // Step 1: Add to max heap (left/smaller half)
        maxHeap.offer(num);

        // Step 2: Move largest from max heap to min heap
        // This ensures all numbers in maxHeap <= all in minHeap
        minHeap.offer(maxHeap.poll());

        // Step 3: Balance the heaps
        // Max heap should have equal or 1 more element than min heap
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Returns the median of all elements so far
     * 
     * Cases:
     * - Odd count (maxHeap larger): return maxHeap's top
     * - Even count (equal sizes): return average of both tops
     */
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            // Odd number of elements
            return maxHeap.peek();
        } else {
            // Even number of elements
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}

/**
 * Driver class to demonstrate the solution
 */
public class MedianStream {
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        System.out.println("=== Finding Median from Stream ===\n");

        int[] stream = { 5, 15, 1, 3, 8, 7, 9, 10, 20 };

        for (int num : stream) {
            mf.addNum(num);
            System.out.printf("After adding %2d: Median = %.1f\n", num, mf.findMedian());
        }

        System.out.println("\n=== Visual Explanation ===");
        demonstrateVisually();
    }

    /**
     * Visual demonstration of how the heaps work
     */
    private static void demonstrateVisually() {
        MedianFinder mf = new MedianFinder();

        int[] nums = { 5, 15, 1, 3 };

        for (int num : nums) {
            System.out.println("\n--- Adding " + num + " ---");
            mf.addNum(num);

            System.out.println("Max Heap (smaller half): " +
                    getHeapString(mf.maxHeap));
            System.out.println("Min Heap (larger half):  " +
                    getHeapString(mf.minHeap));
            System.out.println("Median: " + mf.findMedian());
        }
    }

    private static String getHeapString(PriorityQueue<Integer> heap) {
        if (heap.isEmpty())
            return "[]";

        // Create a copy to avoid modifying original
        PriorityQueue<Integer> copy = new PriorityQueue<>(heap);
        StringBuilder sb = new StringBuilder("[");

        while (!copy.isEmpty()) {
            sb.append(copy.poll());
            if (!copy.isEmpty())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

