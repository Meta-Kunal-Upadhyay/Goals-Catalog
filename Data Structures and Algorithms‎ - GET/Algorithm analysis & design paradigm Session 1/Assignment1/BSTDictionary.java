import java.util.*;

public class BSTDictionary<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    // Constructor: initialize with key-value pairs (from JSON as List of Map.Entry)
    public BSTDictionary(List<Map.Entry<K, V>> entries) {
        for (Map.Entry<K, V> entry : entries) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void add(K key, V value) {
        root = addRecursive(root, key, value);
    }

    private Node addRecursive(Node node, K key, V value) {
        if (node == null) return new Node(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = addRecursive(node.left, key, value);
        else if (cmp > 0)
            node.right = addRecursive(node.right, key, value);
        else
            node.value = value; // Update value if key already exists

        return node;
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = deleteRecursive(node.left, key);
        else if (cmp > 0)
            node.right = deleteRecursive(node.right, key);
        else {
            // Node with one or no child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node with two children: get in-order successor
            Node minNode = getMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = deleteRecursive(node.right, minNode.key);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) return node.value;
            else if (cmp < 0) node = node.left;
            else node = node.right;
        }
        return null;
    }

    @Override
    public List<Map.Entry<K, V>> getSortedList() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<Map.Entry<K, V>> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
        inOrder(node.right, result);
    }

    @Override
    public List<Map.Entry<K, V>> getRange(K k1, K k2) {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        rangeSearch(root, k1, k2, result);
        return result;
    }

    private void rangeSearch(Node node, K k1, K k2, List<Map.Entry<K, V>> result) {
        if (node == null) return;

        int cmpLow = k1.compareTo(node.key);
        int cmpHigh = k2.compareTo(node.key);

        if (cmpLow < 0)
            rangeSearch(node.left, k1, k2, result);

        if (cmpLow <= 0 && cmpHigh >= 0)
            result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));

        if (cmpHigh > 0)
            rangeSearch(node.right, k1, k2, result);
    }
}
