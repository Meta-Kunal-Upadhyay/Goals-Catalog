public interface PriorityQueue<T> {
    void insert(T element); 
    T remove(); 
    T peek(); 
    boolean isEmpty();
    int size(); 
}
