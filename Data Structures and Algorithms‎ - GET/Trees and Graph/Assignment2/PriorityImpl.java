public class PriorityImpl<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] heap;
    private int size;
    private static final int INITIAL_CAPACITY = 20;

    // Constructor
    public PriorityImpl() {
        heap = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    // Insert an element into the priority queue
    @Override
    public void insert(T element) {
        if (size == heap.length) {
            resize();
        }
        heap[size] = element;
        size++;
        heapifyUp();
    }

    // Remove and return the element with the highest priority
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        T root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return root;
    }

    // Peek at the element with the highest priority
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap[0];
    }

    // Check if the priority queue is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the size of the priority queue
    @Override
    public int size() {
        return size;
    }

    // Helper method to resize the array
    private void resize() {
        T[] newHeap = (T[]) new Comparable[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    // Helper method to maintain heap property after insertion
    private void heapifyUp() {
        int index = size - 1;
        while (index > 0 && heap[index].compareTo(heap[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // Helper method to maintain heap property after removal
    private void heapifyDown() {
        int index = 0;
        while (leftChild(index) < size) {
            int smallerChild = leftChild(index);
            if (rightChild(index) < size && heap[rightChild(index)].compareTo(heap[leftChild(index)]) < 0) {
                smallerChild = rightChild(index);
            }

            if (heap[index].compareTo(heap[smallerChild]) <= 0) {
                break;
            }

            swap(index, smallerChild);
            index = smallerChild;
        }
    }

    // Helper methods to calculate parent, left child, and right child indices
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
