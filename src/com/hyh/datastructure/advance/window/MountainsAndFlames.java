package com.hyh.datastructure.advance.window;

import java.util.Scanner;
import java.util.Stack;

/**
 * 烽火问题 O(1)的解
 * 给你一个数组，数组中的每个数代表一座山的高度，这个数组代表将数组中的数从头到尾连接而成的环形山脉
 * 圆圈就代表一座山，圈中的数字代表这座山的高度。现在在每座山的山顶都点燃烽火，假设你处在其中的一个山峰上，
 * 要想看到另一座山峰的烽火需满足以下两个条件中的一个：
 * 1：你想看的山峰在环形路径上与你所在的山峰相邻。
 * 2：如果你想看的山峰和你所在的山峰不相邻，那么你可以沿环形路径顺时针看这座山 也可以沿环形路径逆时针看这座山，
 * 只要你放眼望去沿途经过的山峰高度小于你所在的山峰和目标山峰，那么也能看到。
 * 问：所有山峰中，能互相看到烽火的两两山峰的对数
 * <p>
 * 情况一：数组中无重复的数
 * 这种情况下，答案可以直接通过公式2*N-3可以求得（其中N为数组长度），证明如下：
 * 假设A是在山峰中最高，B在所有山峰中第二高。那么环形路径上介于A和B之间的任意一座山峰（比如K），
 * 逆时针方向在到达A之前或到达A时一定会遇到第一座比它高的山峰，记这座山峰和K是一对；
 * 顺时针方向，在到达B之前或到达B时，一定会遇到第一个比K高的山峰，记这座山峰和K是一对。
 * 也就是说对于除A,B之外的所有山峰，都能找到两对符合标准的，这算下来就是(N-2)*2了，最后AB也算一对，
 * 总数是(N-2)*2+1=2N-3。
 * <p>
 * 情况二：数组中存在重复的数（利用单调栈）
 * <p>
 * 首先找出数组中最大数第一次出现的位置，记为M。从这个数开始遍历数组并依次压栈（栈底到栈底从大到小的单调栈），
 * 从M开始压栈，同时附带一个计数器
 * 例如当栈顶为4时，当压入5时，违反单调栈约定因此结算4（4左边第一个比它高的是M，右边第一个比它高的是5，因此能和4配对的有两对）；
 * 数组中有重复的数时，如何使用单调栈解决此题的关键：如果压入的元素与栈顶元素相同，将栈顶元素的计数器加1
 * 那么如何结算计数器大于1的数据呢？
 * 首先，若有3座高度相同的山峰，两两配对能够组成C(n,2)=3对，此外其中的每座山峰左边离它最近的比它高的假如是5、右边离它近的比它大的假如是9，
 * 因此这3座山峰每座都能和5、9配对，即3*2=6，因此结算结果为3+6=9
 * <p>
 * 如果数据压完了，那就从栈顶弹出数据进行结算，直到结算栈底上一个元素之前（栈底元素是最大值），
 * 弹出数据的结算逻辑都是C(K,2)+K*2（其中K是该数据的计数器数值）
 * <p>
 * 倒数第二条数据的结算逻辑有点复杂：
 * 如果K的数值大于1，那么这k座高度相同的山峰结算逻辑还是上述公式。但如果K为1，那么结算公式就是C(K,2)+K*1了。
 * <p>
 * 最后对于最大值M的结算，假设其计数器的值为K，如果K=1，那么结算结果为0；如果K>1，那么结算结果为C(K,2)。其实都是C(K,2)
 */
public class MountainsAndFlames {

    public static void main(String[] args) {
        int[] arr = {9, 7, 3, 5 ,2, 4, 1, 6, 8, 5, 9, 5};
        System.out.println(communicationsCount(arr));
    }

    public static int communicationsCount(int[] mountains) {
        if (mountains == null || mountains.length < 2) {
            return 0;
        }

        //获取数组最大值第一次出现的位置
        int maxValueIndex = 0;
        for (int i = 0; i < mountains.length; i++) {
            maxValueIndex = mountains[i] > mountains[maxValueIndex] ? i : maxValueIndex;
        }
        //最大值
        int value = mountains[maxValueIndex];
        //山峰数量
        int mountainsCount = mountains.length;
        int index = nextIndexOfCurr(mountainsCount, maxValueIndex);
        //可以相互看见的山峰对
        int count = 0;
        Stack<Pair> stack = new Stack<>();
        //最大值已然在栈底打底 即在加山峰进栈时 栈不可能为空
        stack.push(new Pair(value, 1));
        while (index != maxValueIndex) { //不用判空 就是这么大胆 就是这么自信
            value = mountains[index];
            while (stack.peek().value < value) {
                int times = stack.pop().times;
                count += 2 * times + getInternalSum(times);
            }
            if (stack.peek().value == value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(value, 1));
            }
            index = nextIndexOfCurr(mountainsCount, index);
        }

        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            count += getInternalSum(times);
            //此时 就是两种情况
            if (!stack.isEmpty()) {
                count += times;
                if (stack.size() > 1) {
                    //此时结算的是栈底倒数第三或大于第三个数
                    count += times;
                } else {
                    //此时结算的是栈底中倒数第二个数
                    count += stack.peek().times == 1 ? 0 : times;
                }
            }
        }

        return count;
    }

    //相邻相同山峰之间的对数，若只有一个，则没有成对，若有两个以上计算内部成对数
    public static int getInternalSum(int n) {
        return n == 1 ? 0 : n * (n - 1) / 2;
    }

    private static int nextIndexOfCurr(int arrSize, int currIndex) {
        return currIndex + 1 == arrSize ? 0 : currIndex + 1;
    }


    public static class Pair {
        public int value; //山峰的高度
        public int times; //值为value的山峰出现的次数

        public Pair(int value, int times) {
            this.value = value;
            this.times = times;
        }
    }
}