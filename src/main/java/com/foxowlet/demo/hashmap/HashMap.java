package com.foxowlet.demo.hashmap;

public class HashMap<K, V> implements Map<K, V> {
    private Node[] values;

    public HashMap() {
        this.values = new Node[16];
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        node.next = values[computeIndex(key)];
        values[computeIndex(key)] = node;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = values[computeIndex(key)];
        while (node != null && !node.key.equals(key)) {
            node = node.next;
        }
        return node == null ? null : node.value;
    }

    private int computeIndex(K key) {
        return Math.abs(key.hashCode()) % values.length;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }
}
