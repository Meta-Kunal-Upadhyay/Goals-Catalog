class Main{

    public static void main(String[] args) {
        Queue<Integer> queue = new CircularQueue<>(5); // Create a queue with capacity 5
        
        // Enqueue items
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
    
        System.out.println("Queue is full: " + queue.isFull()); // true
         
        // Dequeue an item
        System.out.println("Dequeued: " + queue.dequeue()); // 1
    
            
        // Enqueue another item after dequeue
        queue.enqueue(6);
        System.out.println("Dequeued: " + queue.dequeue()); // 2
    

        // Print the size of the queue            
        System.out.println("Queue size: " + queue.size()); // 4
    
        // Dequeue all items
        while (!queue.isEmpty()) {                
            System.out.println("Dequeued: " + queue.dequeue());
        }
    
        System.out.println("Queue is empty: " + queue.isEmpty()); // true
    }
}
    



