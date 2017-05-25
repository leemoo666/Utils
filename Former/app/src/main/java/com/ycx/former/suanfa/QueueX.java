package com.ycx.former.suanfa;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 李小明 on 17/5/16.
 * 邮箱:287907160@qq.com
 *
 * 105页:后缀表达式
 *
 */

public class QueueX {


    /**
     * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     * remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     * element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     * <p>
     * offer       添加一个元素并返回true       如果队列已满，则返回false
     * poll         移除并返问队列头部的元素    如果队列为空，则返回null
     * peek       返回队列头部的元素             如果队列为空，则返回null
     * <p>
     * put         添加一个元素                      如果队列满，则阻塞
     * take        移除并返回队列头部的元素     如果队列为空，则阻塞
     */
    public static void queue() {
        Queue<String> queue = new LinkedList<>();

        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("efg");
        System.out.println("element=" + queue.element()); //返回第一个元素

        System.out.println("poll=" + queue.poll()); //返回第一个元素，并在队列中删除

        System.out.println("peek=" + queue.peek()); //返回第一个元素


    }
}
