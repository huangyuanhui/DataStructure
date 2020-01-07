package com.hyh.datastructure.advance.searchtree;

import java.util.*;

public class Building_Outline {
    public static class Node {//高度 位置 上/下
        public boolean isUp;
        public int posi;
        public int h;
        public Node(boolean bORe, int position, int height) {
            isUp = bORe;
            posi = position;
            h = height;
        }
    }
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) {
                return o1.posi - o2.posi;
            }
//            if (o1.isUp != o2.isUp) {
//                return o1.isUp ? -1 : 1;
//            }
            return 0;
        }
    }
    public static List<List<Integer>> buildingOutline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        Arrays.sort(nodes, new NodeComparator());//按照位置排序
        //存高度，次数
        TreeMap<Integer, Integer> htMap = new TreeMap<>();//高度信息
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();//位置信息
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp) {//向上，加一
                if (!htMap.containsKey(nodes[i].h)) {
                    htMap.put(nodes[i].h, 1);
                } else {
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
            } else {//向下，减一
                if (htMap.containsKey(nodes[i].h)) {
                    if (htMap.get(nodes[i].h) == 1) {
                        htMap.remove(nodes[i].h);
                    } else {
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                    }
                }
            }
            //收集每个位置的最大高度
            if (htMap.isEmpty()) {
                pmMap.put(nodes[i].posi, 0);
            } else {
                pmMap.put(nodes[i].posi, htMap.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            //当前高度和之前的不同，开始产生轮廓线
            if (height != curMaxHeight) {
                //不等于0证明之前已经记录了开始，要收尾了
                if (height != 0) {
                    List<Integer> newRecord = new ArrayList<Integer>();
                    newRecord.add(start);
                    newRecord.add(curPosition);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                //等于0的话，才刚开始生成轮廓线
                start = curPosition;
                height = curMaxHeight;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] test = new int[][]{
                {1, 3, 3}, {2, 4, 4}, {5, 6, 1}
        };
        List<List<Integer>> buildingOutLine = buildingOutline(test);
        for (List<Integer> list : buildingOutLine) {
            for (Integer i : list) {
                System.out.printf(i + " ");
            }
            System.out.println();
        }
    }
}
