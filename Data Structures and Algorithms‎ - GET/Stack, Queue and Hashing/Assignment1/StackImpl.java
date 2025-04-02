import java.util.LinkedList;

public class StackImpl<T> implements Stack<T> {
    private LinkedList<T> list = new LinkedList<>();
    
    @Override
    public void push(T item) {
        list.addFirst(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return list.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
