public class HashTable<K, V> {
    static final int initial_capacity = 4;
    float load_factor = 0.75f;
    int count =  0;

    Entry<K, V>[] table;

    public K put(K key, V value) {
        Entry<K, V> newEntry = new Entry(key, value);
        int hash = hash(key);
        if (table == null) {
            table = new Entry[initial_capacity];
            count++;
        }
        Entry<K, V> head = table[hash];
        if (count > initial_capacity * load_factor) {
            resize();
        }
        if (head == null) {
            table[hash] = newEntry;
            count++;
            return key;
        } else {
            Entry tail = new Entry<K, V>();
            if (head.next == null) {
                head.next = newEntry;
            } else {
                do {
                    tail = head;
                } while ((head = head.next) != null);
                tail.next = newEntry;
            }
            count++;
            return key;
        }
    }

    public V get(K key) {
        Entry<K, V> entry;
        return (entry = getEntry(hash(key), key)) == null ? null : entry.value;
    }

    public Entry<K,V> getEntry(int hash, K key) {
        Entry<K, V> entry = table[hash];
        if (entry == null) {
            return null;
        } else if (entry != null && entry.next == null) {
            return entry;
        } else if (entry.next != null) {
            do {
                if (hash == hash(entry.key) &&
                        (key == entry.key || (key != null && key.equals(entry.key)))) {
                    return entry;
                }
            } while ((entry = entry.next) != null);
            return entry;
        }
        return null;
    }

    public int resize() {
        int newCapacity = initial_capacity << 2;
        Entry[] newtable = new Entry[newCapacity];
        System.arraycopy(table, 0, newtable, 0, table.length);
        this.table = newtable;
        return newCapacity;
    }

    private final int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7FFFFFFF % initial_capacity);
    }
}