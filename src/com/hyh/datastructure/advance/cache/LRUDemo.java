package com.hyh.datastructure.advance.cache;


import java.util.HashMap;

public class LRUDemo {
    public static class Node<V> {
        public V value;
        public Node<V> last;  //前指针
        public Node<V> next;  //后指针

        public Node(V value) {
            this.value = value;
            this.last = null;
            this.next = null;
        }
    }

    public static class NodeDoubleLinkedList<V> {
        private Node<V> head;  //头指针
        private Node<V> tail;  //尾指针

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        //向双端链表添加节点
        public void addNode(Node<V> newNode) {
            if (newNode == null) {
                return;
            }
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        //将链表中的某个节点移动到链表尾处
        public void moveNodeToTail(Node<V> node) {
            if (this.tail == node) {
                return;
            }
            if (this.head == node) {
                this.head = this.head.next;
                this.head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.next = null;
            node.last = this.tail;
            this.tail.next = node;
            this.tail = node;
        }

        //移除双端链表头部节点
        public Node<V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<V> res = this.head;
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                this.head.last = null;
                res.next = null;
            }
            return res;
        }
    }

    //自定义实现LRU缓存算法的数据结构
    public static class MyCache<K, V> {
        private HashMap<K, Node<V>> keyNodeMap;  //方便通过key找value(value封装在Node中)
        private HashMap<Node<V>, K> nodeKeyMap;  //方便通过value找key
        private int capacity;
        private NodeDoubleLinkedList<V> nodeDoubleLinkedList;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("缓存大小应该大于0哦");
            }
            this.keyNodeMap = new HashMap<>();
            this.nodeKeyMap = new HashMap<>();
            this.nodeDoubleLinkedList = new NodeDoubleLinkedList<>();
            this.capacity = capacity;
        }

        //从缓存中取数据
        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<V> res = this.keyNodeMap.get(key);
                this.nodeDoubleLinkedList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        //添加数据进缓存
        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<V> node = this.keyNodeMap.get(key);
                node.value = value;
                this.nodeDoubleLinkedList.moveNodeToTail(node);
            } else {
                Node<V> node = new Node<>(value);
                this.keyNodeMap.put(key, node);
                this.nodeKeyMap.put(node, key);
                this.nodeDoubleLinkedList.addNode(node);
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUsedCache();
                }
            }
        }

        //删除缓存中过期数据
        public void removeMostUsedCache() {
            Node<V> node = this.nodeDoubleLinkedList.removeHead();
            K key = this.nodeKeyMap.get(node);
            this.nodeKeyMap.remove(node);
            this.keyNodeMap.remove(key);
        }
    }
}
