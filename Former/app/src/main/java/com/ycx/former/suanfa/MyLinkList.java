package com.ycx.former.suanfa;

/**
 * Created by 李小明 on 17/5/18.
 * 邮箱:287907160@qq.com
 */

public class MyLinkList {

    public MyLink first; // 定义一个头结点  
    private int pos = 0;// 节点的位置  

    public MyLinkList() {
        this.first = null;
    }

    // 插入一个头节点  
    public void addFirstMyLink(String data) {
        MyLink link = new MyLink(data);
        link.next = first;
        first = link;
    }

    // 删除一个头结点,并返回头结点  
    public MyLink deleteFirstMyLink() {
        MyLink tempMyLink = first;
        first = tempMyLink.next;
        return tempMyLink;
    }

    // 在任意位置插入节点 在index的后面插入  
    public void add(int index, String data) {
        MyLink MyLink = new MyLink(data);
        MyLink current = first;
        MyLink previous = first;
        while (pos != index) {
            previous = current;
            current = current.next;
            pos++;
        }
        MyLink.next = current;
        previous.next = MyLink;
        pos = 0;
    }

    // 删除任意位置的节点  
    public MyLink deleteByPos(int index) {
        MyLink current = first;
        MyLink previous = first;
        while (pos != index) {
            pos++;
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            pos = 0;
            previous.next = current.next;
        }
        return current;
    }

    // 根据节点的data删除节点(仅仅删除第一个)  
    public MyLink deleteByData(String data) {
        MyLink current = first;
        MyLink previous = first; //记住上一个节点  
        while (current.name != data) {
            if (current.next == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }
}
