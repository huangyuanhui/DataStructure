package com.hyh.datastructure.hashtable;

import java.util.HashMap;

//随机池 对键的insert getRandom delete操作都是O(1)
public class RandomPoolDemo {

	public static class Pool<K> {
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);
				int lastIndex = --this.size;
				K lastKey = this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(lastKey, deleteIndex);
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}

class RandomPool<K> {
	private HashMap<K, Integer> keyIndexMap = new HashMap<>();
	private HashMap<Integer, K> indexKeyMap = new HashMap<>();
	private int size;

	public void insert(K k) {
		if (!keyIndexMap.containsKey(k)) {
			keyIndexMap.put(k, size);
			indexKeyMap.put(size, k);
			size++;
		}
	}

	public K getRandom() {
		if (size == 0) {
			return null;
		}
		int rand = (int) (Math.random() * size);
		return indexKeyMap.get(rand);
	}

	public void delete(K k) {
		if (keyIndexMap.containsKey(k)) {
			int lastIndex = --size;
			int deleteIndex = keyIndexMap.get(k);
			K lastKey = indexKeyMap.get(lastIndex);
			//其实就是用最后index的值覆盖掉deleteIndex中的值
			keyIndexMap.put(lastKey, deleteIndex);
			indexKeyMap.put(deleteIndex, lastKey);
			keyIndexMap.remove(lastKey);
			indexKeyMap.remove(lastIndex);
		}
	}
}