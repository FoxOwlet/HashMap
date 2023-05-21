package com.foxowlet.demo.hashmap;

public class HashMap<K, V> implements Map<K, V> {
    private Node[] values;
    private int size;
    private double threshold;

    public HashMap() {
        this.values = new Node[16];
        this.size = 0;
        this.threshold = 0.75;
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = computeIndex(key);
        Node<K, V> node = values[index];
        Node<K, V> lastNode = null;
        while (node != null) {
            lastNode = node;
            if (hash == node.hash) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.right;
            } else if (hash < node.hash) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        Node<K, V> newNode = new Node<>();
        newNode.hash = hash;
        newNode.key = key;
        newNode.value = value;
        if (lastNode == null) {
            values[index] = newNode;
        } else if (hash < lastNode.hash) {
            lastNode.left = newNode;
        } else {
            lastNode.right = newNode;
        }
        if (++size > values.length * threshold) {
            resize();
        }
    }

    private void resize() {
        Node<K, V>[] oldNodes = values;
        values = new Node[values.length * 2];
        for (Node<K, V> oldNode : oldNodes) {
            if (oldNode != null) {
                addNode(oldNode);
            }
        }
    }

    private void addNode(Node<K, V> oldNode) {
        int index = computeIndex(oldNode.key);
        Node<K, V> node = values[index];
        Node<K, V> lastNode = null;
        while (node != null) {
            lastNode = node;
            if (oldNode.hash < node.hash) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        Node<K, V> newNode = new Node<>();
        newNode.hash = oldNode.hash;
        newNode.key = oldNode.key;
        newNode.value = oldNode.value;
        if (lastNode == null) {
            values[index] = newNode;
        } else if (oldNode.hash < lastNode.hash) {
            lastNode.left = newNode;
        } else {
            lastNode.right = newNode;
        }
        if (oldNode.left != null) {
            addNode(oldNode.left);
        }
        if (oldNode.right != null) {
            addNode(oldNode.right);
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        Node<K, V> node = values[computeIndex(key)];
        while (node != null) {
            if (hash == node.hash) {
                if (key.equals(node.key)) {
                    return node.value;
                }
                node = node.right;
            } else if (hash < node.hash) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private int computeIndex(K key) {
        return Math.abs(key.hashCode()) % values.length;
    }

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
    }
}
