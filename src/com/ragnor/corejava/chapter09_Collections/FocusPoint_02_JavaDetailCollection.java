package com.ragnor.corejava.chapter09_Collections;

import javax.lang.model.type.MirroredTypeException;
import java.time.LocalDate;
import java.util.*;

public class FocusPoint_02_JavaDetailCollection {
    public static void main(String[] args) {
        // Topic-01: java库中的具体集合
        /*   集合类型                   描述
         *   ArrayList               一种可以动态增长和缩减的索引序列
         *   LinkedList              一种可以在任何位置进行高效插入和删除操作的有序序列
         *   ArrayDeque              一种用循环数组实现的双端队列
         *   HashSet                 一种没有重复元素的无需集合
         *   TreeSet                 一种有序集
         *   EnumSet                 一种包含枚举类型值的集
         *   LinkedHashSet           一种可以记住元素插入次序的集
         *   PriorityQueue           一种允许高效删除最小元素的集合
         *   HashMap                 一种存储键/值关联的数据结构
         *   TreeMap                 一种键值有序排列的映射表
         *   EnumMap                 一种键值属于枚举类型的映射表
         *   LinkedHashMap           一种可以记住键/值添加次序的映射表
         *   WeakHashMap             一种其值无用武之地后可以被垃圾回收器回收的映射表
         *   IdentityHashMap         一种一面不是equals比较键值的映射表
         * */

        JavaLinkedList();
        JavaArrayList();
        HashSet();
        JavaTreeSet();
        JavaQueuesAndTwo_EndedQueues();
        JavaPriorityQueue();

    }

    // FocusSubPoint-01: Linked list
    public static void JavaLinkedList(){
        // Topic-01: 数组以及数据列表有一个重大的缺陷 ==》从数组的中间位置删除/插入一个元素要付出很大的代价==》其原因是数组中处于被删除元素之后的所有元素都要向数组的前端移动
        // Topic-02: 而链表解决了这个问题 ==》链表将每个对象存放在独立的结点中 ==》每个结点还存放着序列中下一个结点的引用
        // Topic-03: 在java程序设计语言总，所有的链表实际上都是双向链表：即每个结点还存放着指向前驱结点的引用previous 与 后驱结点的引用next
        // Topic-04: 从链表中间删除一个元素是一个很轻松的操作：更新被删除元素附近的链接
        List<String> staff = new LinkedList<>();
        staff.add("ragnor");
        staff.add("suling");
        staff.add("zhan");

        System.out.println(staff.listIterator().next()); // 指针到ragnor 列表迭代器
        System.out.println(staff.listIterator().next()); // ragnor
//        System.out.println(staff.listIterator().previous());  // nosuchelement


        staff.remove("suling");
        System.out.println(Arrays.toString(staff.toArray()));  // [ragnor, zhan]

        //

    }

    // FocusSubPoint-02: Array list
    public static void JavaArrayList(){
        // java提供了ArrayList类可以用来： 一种可以动态增长和缩减的索引序列
        ArrayList<String> als = new ArrayList<>(3);
        als.add("ragnor");
        als.add("bug");
        als.add("data");

        System.out.println(als.get(1));
        als.set(0, "qingning");
        System.out.println(als.get(0));
    }

    // FocusSubPoint-03: Hash set
    public static void HashSet(){
        // Topic-01: 散列集是一种可以快速查找所需要对象的数据结构,但无序
        Set<String> words = new HashSet<>();
        words.add("bob");
        words.add("ale");
        words.add("car");

        for(String item : words){
            System.out.println(item);
        }



    }

    // FocusSubPoint-04: Tree set
    public static void JavaTreeSet(){
        // Topic-01: 树集是一个有序集合
        SortedSet<String> sorter = new TreeSet<>();

        sorter.add("bob");
        sorter.add("ale");
        sorter.add("car");

        for (String item: sorter){
            System.out.println(item);
        }
    }

    // FocusSubPoint-05: Queues vs. two-ended queues
    public static void JavaQueuesAndTwo_EndedQueues(){
        // Topic-01: 什么是队列：队列就是可以在尾部添加一个元素，在头部删除一个元素
        // Topic-02: 双端队列就是：有两个端头的队列，可以在头部或者尾部同事添加或删除元素，但是不支持在队列中间添加元素
        System.out.println("queue");
    }

    // FocusSubPoint-06: Priority queue
    public static void JavaPriorityQueue(){
        // Topic-01: 优先级队列中的元素可以按照任意的顺序插入==》却总是按照排序的顺序进行检索
        // Topic-02: 优先级队列使用了一种优雅且高效的数据结构==》堆（heap）
        // Topic-03: 堆是一种可以自我调整的二叉树，对树执行添加add和删除remove操作，可以让最小的元素移动到根，而不必花费时间对元素进行排序

        PriorityQueue<LocalDate> pq = new PriorityQueue<LocalDate>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements...");
        for (LocalDate date: pq){
            System.out.println(date);
        }

        System.out.println("Removing elements...");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }


}
