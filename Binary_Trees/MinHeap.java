package Binary_Trees;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void push(int x) {
        heap.add(x);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) <= heap.get(index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int extract() {
        int min = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        heapifyDown(0);

        return min;
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int lc = 2 * index + 1;
            int rc = 2 * index + 2;
            int s = index;

            if (lc < size && heap.get(lc) < heap.get(s)) {
                s = lc;
            }
            if (rc < size && heap.get(rc) < heap.get(s)) {
                s = rc;
            }
            if (s == index)
                break;

            swap(s, index);
            index = s;
        }

    }

    public void printHeap() {
        for (int val : heap) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.push(6);
        minHeap.push(7);
        minHeap.push(8);
        minHeap.push(9);
        minHeap.push(10);
        minHeap.push(11);
        minHeap.push(12);
        minHeap.push(15);
        minHeap.push(20);

        System.out.println("Min Heap elements:");
        minHeap.printHeap();

        System.out.println("Extracted min: " + minHeap.extract());
        System.out.println("Min Heap after extraction:");
        minHeap.printHeap();
    }

}
