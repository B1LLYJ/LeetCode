import java.util.Objects;

public class Entry<K, V> {
    int hash;
    K key;
    V value;
    Entry<K, V> next;
    public Entry() {
    }
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public Entry(int hash, K key, V value, Entry<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Entry) {
            Entry<K, V> e = (Entry<K, V>)(o);
            if (Objects.equals(e.key, key) && Objects.equals(e.value, value)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(key) ^ Objects.hash(value);
    }
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
