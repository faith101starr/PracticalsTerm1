// ChainedHash.java
public class ChainedHash {

    private static class Node {
        String key, value;
        Node next;
        Node(String k, String v) { key = k; value = v; next = null; }
    }

    private Node[] table;
    private int m;

    public ChainedHash(int m) {
        this.m = m;
        table = new Node[m + 1]; // indices 1..m
    }

    private int hash(String key) {
        int x = Integer.parseInt(key);
        return (x % m) + 1;
    }

    public void insert(String key, String value) {
        int i = hash(key);
        Node head = table[i];
        Node prev = null;

        while (head != null) {
            if (head.key.equals(key)) { head.value = value; return; }
            prev = head;
            head = head.next;
        }

        Node newNode = new Node(key, value);
        if (prev == null) table[i] = newNode;
        else prev.next = newNode;
    }

    public String lookup(String key) {
        int i = hash(key);
        Node head = table[i];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public String remove(String key) {
        int i = hash(key);
        Node head = table[i], prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) table[i] = head.next;
                else prev.next = head.next;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean isInTable(String key) { return lookup(key) != null; }
}