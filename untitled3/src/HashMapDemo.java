import java.util.Map;

public class HashMapDemo<K, V> {

    private class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    //默认容量为16
    private static final int DEFAULT_CAPACITY = 1 << 4;

    private Entry<K, V>[] table;

    private int capacity;

    private int size;

    public HashMapDemo() {
        this(DEFAULT_CAPACITY);
    }

    public HashMapDemo(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        } else {
            table = new Entry[capacity];
            size = 0;
            this.capacity = capacity;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private int hash(K key) {
        double tmp = key.hashCode() * (Math.pow(5, 0.5) - 1) / 2;
        //System.out.println(key.hashCode());
        double digit = tmp - Math.floor(tmp);
        return (int) Math.floor(digit * capacity);
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = hash(key);
        System.out.println(hash);
        System.out.println(key.hashCode());
        Entry<K, V> nEntry = new Entry<K, V>(hash, key, value, null);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        nEntry.next = table[hash];
        table[hash] = nEntry;
        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = hash(key);
        //System.out.println(hash);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;

            }
            entry = entry.next;
        }
        return null;
    }

    public static void main(String[] args) {
        // 初始化数据对象;
        HashMapDemo<String, String> map = new HashMapDemo<String, String>();
        System.out.println(map);
        // 数据转载;
        map.put("1", "11");
        map.put("2", "44");
        map.put("3", "33");
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        System.out.println(map.get("3"));
    }

}