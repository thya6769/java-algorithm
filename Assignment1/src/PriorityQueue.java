 public interface PriorityQueue<K extends Comparable<K>, V> {

        public void insert(K key, V value);

        public V removeMin();

        public V min();

        public int size();

        public boolean isEmpty();
        
        public void replaceKey(K oldKey, V oldValue, K newKey);
    }
 