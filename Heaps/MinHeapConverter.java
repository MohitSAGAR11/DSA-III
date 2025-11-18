package Heaps;
import java.util.Arrays;

public class MinHeapConverter {
    
    // ===========================================
    // METHOD 1: O(N log N) - Repeated Insertion
    // ===========================================
    
    /**
     * Heapify Up - Bubble up element to maintain heap property
     * Used in insertion method
     */
    private static void heapifyUp(int[] heap, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            
            // If current element is smaller than parent, swap
            if (heap[index] < heap[parent]) {
                System.out.println("  Swap " + heap[index] + " with parent " + heap[parent]);
                
                // Swap
                int temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;
                
                // Move up to parent
                index = parent;
            } else {
                break;
            }
        }
    }
    
    /**
     * Build Min-Heap by repeated insertion - O(N log N)
     */
    public static int[] buildHeapByInsertion(int[] arr) {
        System.out.println("=".repeat(60));
        System.out.println("METHOD 1: Build Heap using Repeated Insertion");
        System.out.println("Time Complexity: O(N log N)");
        System.out.println("=".repeat(60));
        
        int n = arr.length;
        int[] heap = new int[n];
        
        // Insert elements one by one
        for (int i = 0; i < n; i++) {
            System.out.println("\nStep " + (i + 1) + ": Inserting " + arr[i]);
            
            // Add element at the end
            heap[i] = arr[i];
            System.out.println("  Array: " + Arrays.toString(Arrays.copyOfRange(heap, 0, i + 1)));
            
            // Heapify up from newly inserted element
            if (i > 0) {
                heapifyUp(heap, i);
                System.out.println("  After heapify-up: " + Arrays.toString(Arrays.copyOfRange(heap, 0, i + 1)));
            }
            
            printTree(heap, i + 1);
        }
        
        System.out.println("\n✓ Final Min-Heap: " + Arrays.toString(heap));
        return heap;
    }
    
    // ===========================================
    // METHOD 2: O(N) - Build-Heap (Heapify)
    // ===========================================
    
    /**
     * Heapify Down - Push down element to maintain heap property
     * Used in build-heap method
     */
    private static void heapifyDown(int[] heap, int n, int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        // Find smallest among root, left child, right child
        if (left < n && heap[left] < heap[smallest]) {
            smallest = left;
        }
        
        if (right < n && heap[right] < heap[smallest]) {
            smallest = right;
        }
        
        // If smallest is not root, swap and continue heapifying
        if (smallest != index) {
            System.out.println("  Swap " + heap[index] + " (index " + index + 
                             ") with " + heap[smallest] + " (index " + smallest + ")");
            
            // Swap
            int temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;
            
            System.out.println("  Array: " + Arrays.toString(heap));
            
            // Recursively heapify the affected subtree
            heapifyDown(heap, n, smallest);
        }
    }
    
    /**
     * Build Min-Heap using heapify - O(N)
     * This is the OPTIMIZED method
     */
    public static int[] buildHeapByHeapify(int[] arr) {
        System.out.println("=".repeat(60));
        System.out.println("METHOD 2: Build Heap using Heapify (Bottom-Up)");
        System.out.println("Time Complexity: O(N) - OPTIMIZED!");
        System.out.println("=".repeat(60));
        
        int n = arr.length;
        int[] heap = arr.clone(); // Make a copy
        
        System.out.println("\nInitial Array: " + Arrays.toString(heap));
        printTree(heap, n);
        
        // Start from last non-leaf node and heapify each node
        int lastNonLeaf = (n / 2) - 1;
        System.out.println("\nLast non-leaf node index: " + lastNonLeaf);
        System.out.println("Starting heapify from index " + lastNonLeaf + " down to 0\n");
        
        for (int i = lastNonLeaf; i >= 0; i--) {
            System.out.println("Step: Heapifying subtree rooted at index " + i + 
                             " (value: " + heap[i] + ")");
            heapifyDown(heap, n, i);
            printTree(heap, n);
            System.out.println();
        }
        
        System.out.println("✓ Final Min-Heap: " + Arrays.toString(heap));
        return heap;
    }
    
    // ===========================================
    // UTILITY FUNCTIONS
    // ===========================================
    
    /**
     * Print heap as a tree structure
     */
    private static void printTree(int[] heap, int size) {
        if (size == 0) return;
        
        System.out.println("  Tree Structure:");
        int height = (int) (Math.log(size) / Math.log(2)) + 1;
        int index = 0;
        
        for (int level = 0; level < height && index < size; level++) {
            int nodesInLevel = (int) Math.pow(2, level);
            int spaces = (int) Math.pow(2, height - level) - 1;
            
            System.out.print("  ");
            for (int i = 0; i < nodesInLevel && index < size; i++) {
                // Print leading spaces
                for (int s = 0; s < spaces; s++) {
                    System.out.print("  ");
                }
                
                System.out.print(heap[index]);
                
                // Print trailing spaces
                for (int s = 0; s < spaces; s++) {
                    System.out.print("  ");
                }
                
                System.out.print("  ");
                index++;
            }
            System.out.println();
        }
    }
    
    /**
     * Verify if array is a valid min-heap
     */
    public static boolean isMinHeap(int[] heap) {
        int n = heap.length;
        
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            // Check left child
            if (left < n && heap[i] > heap[left]) {
                return false;
            }
            
            // Check right child
            if (right < n && heap[i] > heap[right]) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Extract minimum element from heap - O(log N)
     */
    public static int extractMin(int[] heap, int size) {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        
        int min = heap[0];
        
        // Move last element to root
        heap[0] = heap[size - 1];
        
        // Heapify down from root
        heapifyDown(heap, size - 1, 0);
        
        return min;
    }
    

    public static void main(String[] args) {
        // Test array
        int[] arr = {9, 5, 6, 2, 3, 7, 1, 4, 8};
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONVERTING ARRAY TO MIN-HEAP");
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("=".repeat(60) + "\n");
        
        // Method 1: Repeated Insertion - O(N log N)
        int[] heap1 = buildHeapByInsertion(arr.clone());
        System.out.println("\nIs valid min-heap? " + isMinHeap(heap1));
        
        System.out.println("\n\n");
        
        // Method 2: Build-Heap (Heapify) - O(N)
        int[] heap2 = buildHeapByHeapify(arr.clone());
        System.out.println("\nIs valid min-heap? " + isMinHeap(heap2));
        
        // Demonstration of extract min
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRATION: Extract Min Operation");
        System.out.println("=".repeat(60));
        
        int[] testHeap = heap2.clone();
        int size = testHeap.length;
        
        System.out.println("\nExtracting elements in sorted order:");
        while (size > 0) {
            int min = extractMin(testHeap, size);
            size--;
            System.out.println("Extracted: " + min + " | Remaining heap: " + 
                             Arrays.toString(Arrays.copyOfRange(testHeap, 0, size)));
        }
        
        // Comparison
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY COMPARISON");
        System.out.println("=".repeat(60));
        System.out.println("Method 1 - Repeated Insertion:");
        System.out.println("  Time:  O(N log N) - each of N insertions takes O(log N)");
        System.out.println("  Space: O(1) - iterative heapify-up");
        System.out.println("\nMethod 2 - Build-Heap (Heapify):");
        System.out.println("  Time:  O(N) - mathematical proof using geometric series");
        System.out.println("  Space: O(log N) - recursive heapify-down");
        System.out.println("\n✓ Method 2 is asymptotically faster!");
        System.out.println("=".repeat(60));
    }
}