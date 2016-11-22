import java.util.ArrayList;
import java.util.List;


public class ArrayPriorityQueue <K extends Comparable<K>, V> implements PriorityQueue<K, V>{
	private ArrayList<KeyValuePair<K, V>> queue;
	public ArrayPriorityQueue(){
		queue = new ArrayList<KeyValuePair<K, V>>();
	}
	public void insert(K key, V value){
		queue.add(new KeyValuePair<K, V>(key, value));
	}
	public V removeMin() {
		if (queue.isEmpty()){
			return null;
		}
		KeyValuePair<K, V> min = queue.get(0);
		for (int i = 1; i < queue.size(); i++){
			if (min.compareTo(queue.get(i)) >= 0){
				min = queue.get(i);
			} 
		}
		V value = min.value;
		queue.remove(min);
		return value;
	}
	public List<V> removeBefore(K current){
		List<V> beforeCurrent = new ArrayList<V>();
		for (int i = 0; i < queue.size(); i++){
			if (current.compareTo(queue.get(i).key) > 0 || current.compareTo(queue.get(i).key) == 0){
				beforeCurrent.add(queue.get(i).value);
				queue.remove(queue.get(i));
				i--;
			}
		}
		return beforeCurrent;
	}
	public V min() {
		if (queue.isEmpty()){
			return null;
		}
		KeyValuePair<K, V> min = queue.get(0);
		for (int i = 1; i < queue.size(); i++){
			if (min.compareTo(queue.get(i)) > 0){
				min = queue.get(i);
			}
		}
		return min.value;
	}
	public K getKey() {
		if (queue.isEmpty()){
			return null;
		}
		KeyValuePair<K, V> min = queue.get(0);
		for (int i = 1; i < queue.size(); i++){
			if (min.compareTo(queue.get(i)) > 0){
				min = queue.get(i);
			}
		}
		return min.key;
	}
	
	public int size() {
		return queue.size();
	}
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}
	/*
	 * Takes two keys and find the node with the old key and change it to new key
	 */
	@Override
	public void replaceKey(K oldKey, V oldValue, K newKey) {
		KeyValuePair<K, V> entryToModify = null;
        for (KeyValuePair<K, V> entry : queue) {
            if (entry.key.equals(oldKey) && entry.value.equals(oldValue)) {
                entryToModify = entry;
                break;
            }
        }  
        if (entryToModify == null) {
            return;
        }   
        queue.remove(entryToModify);
        entryToModify.key = newKey;
        queue.add(entryToModify);
	}
	
}
