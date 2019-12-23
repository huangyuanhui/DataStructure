package com.hyh.datastructure.tree;

//前缀树
public class TrieTreeDemo {
    //前缀节点
    public static class TrieNode {
        //多少个字符串经过这个节点
        public int path;
        //多少个字符串以这个节点结尾
        public int end;
        //这个节点往下有多少条路（规定a-z）
        public TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    //前缀树数据结构
    public static class TrieTree {
        public TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        //建立前缀树
        public void insert(String str) {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }

        //判断一个字符串出现过几次
        public int search(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        //删除
        public void delete(String str) {
            if (search(str) != 0) {
                char[] chars = str.toCharArray();
                int index = 0;
                TrieNode node = root;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nexts[index].path == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        //以某字符串为前缀的字符串的数量
        public int prefixCount(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }
}
