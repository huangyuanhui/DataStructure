package com.hyh.datastructure.advance.searchtree;

import java.util.*;

//大楼轮廓问题
//使用TreeMap
public class BuildingOutline {

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


    private static List<List<Integer>> buildingOutline(int[][] building) {
        Node[] nodes = new Node[building.length * 2];
        for (int i = 0; i < building.length; i++) {
            nodes[i * 2] = new Node(building[i][0], building[i][2], true);
            nodes[i * 2 + 1] = new Node(building[i][1], building[i][2], false);
        }
        //按照位置排序
        Arrays.sort(nodes, new NodeComparator());
        System.out.println("Arrays.toString(nodes) = " + Arrays.toString(nodes));
        //存高度 次数
        TreeMap<Integer, Integer> htMap = new TreeMap<>();  //高度信息
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();  //位置信息 记录每一个位置冲到的最大高度

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp) {  //向上 加一
                if (!htMap.containsKey(nodes[i].h)) {
                    htMap.put(nodes[i].h, 1);
                } else {
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
            } else {  //向下 减一
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
                pmMap.put(nodes[i].pos, 0);
            } else {
                pmMap.put(nodes[i].pos, htMap.lastKey());
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height  = 0;  //前一个位置的高度
        //因为是treeMap 所以遍历的时候key一定是升序的 即位置是从小到大开始遍历(理解下面
        // 代码时要时刻记住这点 )
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int currPosition = entry.getKey();
            int currMaxHeight = entry.getValue();
            //最大高度不同 即最大高度发生变化 开始产生轮廓线变化
            if (height != currMaxHeight) {
                if (height != 0) {
                    //说明此处位置是某一个轮廓的收尾 记录
                    List<Integer> rewRecord = new ArrayList<>();
                    rewRecord.add(start);
                    rewRecord.add(currPosition);
                    rewRecord.add(height);
                    res.add(rewRecord);
                }
                //重新记录height 与 start
                height = currMaxHeight;
                start = currPosition;
            }
        }
        return res;
    }


    //大楼节点
    public static class Node {
        public int pos;  //大楼开始或结束位置
        public int h;  //大楼高度
        public boolean isUp;  //大楼上升还是下降

        public Node(int pos, int h, boolean isUp) {
            this.pos = pos;
            this.h = h;
            this.isUp = isUp;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "pos=" + pos +
                    ", h=" + h +
                    ", isUp=" + isUp +
                    '}';
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            //位置小的排前面 位置大的排后面
            if (o1.pos != o2.pos) {
                return o1.pos - o2.pos;
            }
            //01, 02位置相同
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }

}
