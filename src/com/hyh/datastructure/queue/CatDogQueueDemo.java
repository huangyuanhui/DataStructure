package com.hyh.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

//猫狗队列问题
public class CatDogQueueDemo {
    public static void main(String[] args) {
        CatDogQueue catDogQueue = new CatDogQueue();
        catDogQueue.add(new Dog());
        catDogQueue.add(new Dog());

        System.out.println("catDogQueue.isEmpty() = " + catDogQueue.isEmpty());
        System.out.println("catDogQueue.isDogEmpty() = " + catDogQueue.isDogEmpty());
        System.out.println("catDogQueue.isCatEmpty() = " + catDogQueue.isCatEmpty());
        System.out.println("catDogQueue.pollDog() = " + catDogQueue.pollDog());
        System.out.println("catDogQueue.pollCat() = " + catDogQueue.pollCat());
    }
}

//猫狗队列
class CatDogQueue{
    private Queue<PetEnterQueue> catQueue;
    private Queue<PetEnterQueue> dogQueue;
    //计数器
    private int count;

    public CatDogQueue() {
        this.catQueue = new LinkedList<>();
        this.dogQueue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return isCatEmpty() && isDogEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public void add(Pet pet) {
        if ("dog".equals(pet.getType())) {
            dogQueue.add(new PetEnterQueue(pet, ++count));
        }else if ("cat".equals(pet.getType())) {
            catQueue.add(new PetEnterQueue(pet, ++count));
        }
    }

    public Pet pollAll() {
        if (isEmpty()) {
            throw new RuntimeException("猫狗队列为空。。。。");
        } else if (isDogEmpty()) {
            return catQueue.poll().getPet();
        } else if (isCatEmpty()){
            return dogQueue.poll().getPet();
        } else {
            return catQueue.peek().getCount() < dogQueue.peek().getCount() ? catQueue.poll().getPet() : dogQueue.poll().getPet();
        }
    }

    public Cat pollCat() {
        if (isCatEmpty()) {
            throw new RuntimeException("猫队列为空。。。。");
        }
        return (Cat) catQueue.poll().getPet();
    }

    public Dog pollDog() {
        if (isDogEmpty()) {
            throw new RuntimeException("狗队列为空。。。。");
        }
        return (Dog) dogQueue.poll().getPet();
    }
}

//宠物 猫 狗类是给定的类 我们不应该也不能改他们
//描述宠物进入队列的类
class PetEnterQueue {
    private Pet pet;   //此时进队列的宠物
    private int count;  //时间戳

    public PetEnterQueue(Pet pet, int count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public int getCount() {
        return count;
    }

    //返回宠物类型
    public String getPetType() {
        return this.pet.getType();
    }
}

//宠物
class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                '}';
    }
}

//猫
class Cat extends Pet {

    public Cat() {
        super("cat");
    }
}

//狗
class Dog extends Pet {

    public Dog() {
        super("dog");
    }
}

