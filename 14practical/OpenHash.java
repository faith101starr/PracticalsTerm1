// OpenHash.java
public class OpenHash {

    private static class Entry {
        String key;
        String value;
        Entry(String k, String v) { key = k; value = v; }
    }

    private Entry[] table;
    private int size, m;

    public OpenHash(int m) {
        this.m = m;
        table = new Entry[m + 1]; // indices 1..m
        size = 0;
    }

    private int hash(String key) {
        int x = Integer.parseInt(key);
        return (x % m) + 1;
    }

    public void insert(String key, String value) {
        int i = hash(key);
        int r = 1;
        while (table[i] != null && !table[i].key.equals(key)) {
            i = ((i - 1 + r) % m) + 1;
            r++;
        }
        table[i] = new Entry(key, value);
        size++;
    }

    public String lookup(String key) {
        int i = hash(key), r = 1;
        while (table[i] != null) {
            if (table[i].key.equals(key)) return table[i].value;
            i = ((i - 1 + r) % m) + 1;
            r++;
        }
        return null;
    }

    public String remove(String key) {
        int i = hash(key), r = 1;
        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                String val = table[i].value;
                table[i] = null;
                size--;
                return val;
            }
            i = ((i - 1 + r) % m) + 1;
            r++;
        }
        return null;
    }

    public boolean isInTable(String key) { return lookup(key) != null; }
    public boolean isFull() { return size == m; }
    public boolean isEmpty() { return size == 0; }
}