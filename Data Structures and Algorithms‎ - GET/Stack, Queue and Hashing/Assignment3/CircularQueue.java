public class CircularQueue<T> implements Queue<T> {
    private T[] queue;
    private int front, rear, size, capacity;
    
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = this.rear = this.size = 0;
    }
    
    @Override
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("The Queue is Full");   
        }
        queue[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
    }
    
    @Override
    public T dequeue() {
        if (isEmpty()){ 
            throw new IllegalStateException("The Queue is Empty");
        }
        T item = queue[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }
    
    @Override
    public boolean isEmpty() { 
        return size == 0; 
    }
    
    @Override
    public boolean isFull() { 
        return size == capacity; 
    }

    @Override
    public int size() {
        return size;
    }


    public T peek() {
        if (isEmpty()){
            throw new IllegalStateException("The Queue is Empty");
        }

        return queue[front];
    }
}