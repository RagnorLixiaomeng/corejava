package com.ragnor.corejava.chapter09_Collections;

import java.util.*;
import java.util.function.Consumer;

public class FocusPoint_01_JavaCollectionFramework {
    public static void main(String[] args) {
        // Topic-01: 什么是数据结构？
        // ==》我们在实现方法的时候，选择不同的数据结构会导致其实现风格以及性能存在很大差异
        // ==》需要快速搜索成千上万个有序的数据项吗？
        // ==》需要建立键与值之间的关联吗？
        // ==》这个章节的目的就是为了让我们掌握：利用java类库帮助我们在程序设计中实现传统的数据结构
        SeparateInterfaceAndImplementationOfCollection();
        InterfaceCollection();
        RealizeIterator();
        IsThereSomeGenericPracticalMethods();
        CollectionFrameworkInterfaces();
    }


    // FocusSubPoint-01：How do I separate the interface of a collection from the implementation?
    public static void SeparateInterfaceAndImplementationOfCollection(){
        // Topic-01: 现代的数据结构类库一样，Java的集合类库也是将接口（interface）与实现（implementation）分离
        //           比如队列这种数据结构：
        //                             设计队列接口指出可以在队列的尾部添加元素，在队列的头部删除元素，并且可以查找队列中元素的个数
        System.out.println("什么意思呢？就是说集合接口与实现在概念上是不同的，如果需要一个循环数组队列就使用ArrayDeque类，如果需要一个链表队列就直接使用LinkedList类，这些类实现了Queue接口");
    }

    /**接口并没有说明队列是如何实现的，而是说明需要具备什么样的能力*/
    public interface R$Queue<E>{
        void add(E element);
        E remove();
        int size();
    }

    // FocusSubPoint-02：How to define collection interface？
    public static void InterfaceCollection(){
        // Topic-01: 在java类库中，集合类的基本接口是Collection接口，接口中有两个基本方法：boolean add（E element）:向集合中添加元素 跟 Iterator<E> iterator()
        System.out.println("就是为了说明java中集合类的基本接口是Collection接口");
    }

    // FocusSubPoint-03：how to realize Iterator？
    public static void RealizeIterator(){
        // Iterator接口包含4个方法： next() hasNext() remove() forEachRemaining()

        List<String> list=new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");
        Iterator<String> ite = list.iterator();

        while (ite.hasNext()){
            System.out.println(ite.next());
        }



    }

    public interface R$IteratorInterface<E>{
        E next();
        boolean hasNext();
        void remove();

        default void forEachRemaining(Consumer<? super E> action){};
    }

    class R$Iterator implements R$IteratorInterface<String>{


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public void remove() {

        }

        @Override
        public String next() {
            return null;
        }
    }


    // FocusSubPoint-04: What are some practical(实用) generic methods?
    public static void IsThereSomeGenericPracticalMethods(){
        // Topic-01: 由于Collection 与 Iterator都是泛型接口 ==> 可以编写操作任何集合类型的使用方法
        // 比如检测任意集合是否包含指定元素的泛型方法，当然这里的目的主要是有很多接口可以访问
        List<String> list=new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");

        System.out.println(R$contains(list, "Hello"));


    }

    /**检测任意集合是否包含指定元素的泛型方法*/
    public static <E> boolean R$contains(Collection<E> c, Object obj){
        for (E element: c){
            if (element.equals(obj)){
                return true;}
        }
        return false;
    }

    // FocusSubPoint-05: What are the interfaces in the collection framework?
    public static void CollectionFrameworkInterfaces(){
        // Topic-01: java集合框架中为不同类型的集合定义了大量的接口
        // Topic-02: 集合中有两个基本接口：Collection 和 Map
        System.out.println("看corejava中的图即可");
    }


}
