package com.hyh.datastructure.advance.cache;

import java.util.HashMap;

/**
 * 最近最常使用(根据一段时间内使用频度决定)
 */
public class LFUDemo {
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;  //这个node出现的次数
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {
        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList lastNodeList;
            public NodeList nextNodeList;

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            public void addNodeFromHead(Node newNode) {
                newNode.down = head;
                head.up = newNode;
                head = newNode;
            }

            public boolean isEmpty() {
                return head == null;
            }

            public void deleteNode(Node node) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    if (node == head) {
                        head = node.down;
                        head.up = null;
                    } else if (node == tail) {
                        tail = node.up;
                        tail.down = null;
                    } else {
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                node.up = null;
                node.down = null;
            }
        }


        private int capacity;   //缓存容量
        private int size;  //缓存数据数目
        private HashMap<Integer, Node> records; //一个node对应的key(key是Integer)
        private HashMap<Node, NodeList> heads;  //一个node对应哪个nodeList
        private NodeList headList;  //头nodeList 超过容量时 方便删除 删除的就是headList的尾部

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
        }

        //向缓存添加数据
        public void set(int key, int value) {
            if (records.containsKey(key)) { //包含key
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList nodeList = heads.get(node);
                //重新调整node所在的nodeList
                move(node, nodeList);
            } else {    //不包含key
                if (size == capacity) {   //到达容量 要先删除缓存 即删除headList的尾节点
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    //更新headList
                    modifyHeadList(headList);
                    //移除被删除节点的相关关系（哪个key对应这个被删除的node）
                    records.remove(node.key);
                    //移除被删除节点的相关关系（被删除node对应哪个nodeList）
                    heads.remove(node);
                    size--;
                }
                //将数据存入缓存
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList newHeadList = new NodeList(node);
                        newHeadList.nextNodeList = headList;
                        headList.lastNodeList = newHeadList;
                        headList = newHeadList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        //像缓存中获取缓存数据
        public Integer get(int key) {
            if (!records.containsKey(key)) {
                return null; //不存在
            }
            Node node = records.get(key);
            node.times++;
            NodeList nodeList = heads.get(node);
            move(node, nodeList);
            return node.value;
        }

        //当删除缓存数据时 更新nodeList指针 返回是或者否
        private boolean modifyHeadList(NodeList nodeList) {
            if (nodeList.isEmpty()) {
                if (headList == nodeList) {
                    headList = nodeList.nextNodeList;
                    if (headList != null) {
                        headList.lastNodeList = null;
                    }
                } else {
                    nodeList.lastNodeList.nextNodeList = nodeList.nextNodeList;
                    if (nodeList.nextNodeList != null) {
                        nodeList.nextNodeList.lastNodeList = nodeList.lastNodeList;
                    }
                }
                return true;
            }
            return false;
        }

        //当node数据发生变化时 重新调整node所在的nodeList
        //一定注意nextNodeList lastNodeList 与 headList之间是相互解耦的
        private void move(Node node, NodeList oleNodeList) {
            //从oleNodeList删除词频变化了的node
            oleNodeList.deleteNode(node);
            //老链表调整后前一个nodeLList
            NodeList preNodeList = modifyHeadList(oleNodeList) ? oleNodeList.lastNodeList : oleNodeList;
            //老链表调整后后一个nodeLList
            NodeList nextNodeList = oleNodeList.nextNodeList;
            if (nextNodeList == null) {
                NodeList newNodeList = new NodeList(node);
                if (preNodeList != null) {
                    preNodeList.nextNodeList = newNodeList;
                }
                newNodeList.lastNodeList = preNodeList;
                if (headList == null) {
                    headList = newNodeList;
                }
                heads.put(node, newNodeList);
            } else {
                if (nextNodeList.head.times == node.times) {
                    nextNodeList.addNodeFromHead(node);
                    heads.put(node, nextNodeList);
                } else {
                    NodeList newNodeList = new NodeList(node);
                    if (preNodeList != null) {
                        preNodeList.nextNodeList = newNodeList;
                    }
                    newNodeList.lastNodeList = preNodeList;
                    newNodeList.nextNodeList = nextNodeList;
                    nextNodeList.lastNodeList = newNodeList;
                    if (headList == nextNodeList) {
                        headList = newNodeList;
                    }
                    heads.put(node, newNodeList);
                }
            }
        }
    }

    public static void main(String[] args) {
        LFUCache testCache = new LFUCache(3);
        testCache.set(1, 1);
        testCache.set(2, 2);
        testCache.set(3, 3);
        System.out.println(testCache.get(1));
        System.out.println(testCache.get(2));
        testCache.set(4, 5);
        System.out.println(testCache.get(4));
        System.out.println(testCache.get(3));
    }
}
