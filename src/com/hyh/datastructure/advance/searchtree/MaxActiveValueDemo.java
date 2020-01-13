package com.hyh.datastructure.advance.searchtree;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个公司的上下节关系是一棵多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理：
 * 一个员工的直接上级如果到场，这个员工肯定不会来。每个员工都有一个活跃度的值，决定谁来你
 * 会给这个员工发邀请函，怎么让舞会的气氛最活跃？返回最大的活跃值。
 *
 *  可能性
 * 1、X来，活跃度就是x活跃度+x1不来+x2不来+x3不来的总和。
 * 2、X不来，活跃度就是x1/x2/x3来和不来中选最大的总和。
 *
 * 收集信息：
 * 1、一棵树在头结点来的活跃度
 * 2、一棵树在头结点不来的活跃度
 */
public class MaxActiveValueDemo {
    public static class Node {
        public int happy;  //开心值
        public List<Node> nexts;  //多叉树

        public Node(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }

    public static class ReturnType {
        public int comeHappy;  //来快活
        public int notComeHappy;  //不来快活
        public ReturnType(int comeHappy, int notComeHappy) {
            this.comeHappy = comeHappy;
            this.notComeHappy = notComeHappy;
        }
    }

    public static ReturnType process(Node head) {
        int happy = head.happy; //来快活
        int notHappy = 0;  //不来快活
        for (int i = 0; i < head.nexts.size(); i++) {
            ReturnType returnType = process(head.nexts.get(i));  //视作黑盒即可
            happy += returnType.notComeHappy;
            notHappy += Math.max(returnType.comeHappy, returnType.notComeHappy);
        }
        return new ReturnType(happy, notHappy);
    }
}
