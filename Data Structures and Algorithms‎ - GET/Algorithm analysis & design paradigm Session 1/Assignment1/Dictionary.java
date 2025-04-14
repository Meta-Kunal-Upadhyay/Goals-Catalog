import java.util.List;
import java.util.Map;

public interface Dictionary<K extends Comparable<K>, V> {
    void add(K key, V value);
    void delete(K key);
    V get(K key);
    List<Map.Entry<K, V>> getSortedList();
    List<Map.Entry<K, V>> getRange(K k1, K k2);
}
