package com.hyh.datastructure.greed;

import java.util.Arrays;
import java.util.Comparator;

//会议室规定时间内最多有多少项目能宣讲（不同项目的开始 结束时间是确定的）
//贪心策略：不能按照项目早开始先安排 也不能按照项目持续时间短先安排
//正确的贪心策略应该是按照哪个项目早结束先安排的策略 然后淘汰掉因为做这个项目不能做的项目
public class Code_06_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	//项目结束时间排在前面的比较器
	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	/**
	 *
	 * @param programs
	 * @param start 开始时间
	 * @return
	 */
	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator());
		//可宣讲的项目数
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start) {
				result++;
				//下个项目可以开始宣讲的时间
				start = programs[i].end;
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
