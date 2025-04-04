public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityImpl<>();
        
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);
        pq.insert(1);

        System.out.println("Peek: " + pq.peek()); 
        System.out.println("Removed: " + pq.remove()); 
        System.out.println("Peek: " + pq.peek()); 
        System.out.println("Removed: " + pq.remove()); 
        System.out.println("Peek: " + pq.peek()); 

    }
}
