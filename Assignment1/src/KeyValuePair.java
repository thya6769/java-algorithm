
public class KeyValuePair<K extends Comparable<K>, V> implements Comparable<KeyValuePair<K, V>> {
	public K key;
	public V value;
	public KeyValuePair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public int compareTo(KeyValuePair<K, V> other){
		return key.compareTo(other.key);
	}
}
