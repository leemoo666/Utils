package com.ycx.former.suanfa;

import android.util.Log;

import java.util.Stack;

/**
 * Created by 李小明 on 17/5/16.
 * 邮箱:287907160@qq.com
 *
 * 数据项入栈和出栈的时间复杂度都为O(1),也就是说
 * 栈操作所消耗的时间不依赖于栈中数据项的个数,因此操作时间很短,栈不需要比较和移动操作
 */

public class StackX {


    private static String array = "abcdefg";

    //单次逆序
    public static void reverseChar() {
        int length = array.length();
        Stack<Character> stack = new Stack();

        for (int i = 0; i < length; i++) {
            char ch = array.charAt(i);
            stack.push(ch);
        }
        StringBuffer output = new StringBuffer();

        while (!stack.isEmpty()) {
            char ch = stack.pop();
            output.append(ch);
        }

        Log.i("lxm", "outPut = " + output.toString());
    }


    private static final String separatorStr = "a{bc[def(g)h]i}j{}(";

    //separator 校验分隔符
    public static void separator() {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < separatorStr.length(); i++) {
            char ch = separatorStr.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char chPop = stack.pop();
                        if ((ch == '}' && chPop != '{') ||
                                (ch == ']' && chPop != '[') ||
                                (ch == ')' && chPop != '(')) {
                            Log.i("lxm", "error : " + ch + " at " + i);
                        }
                    } else {
                        Log.i("lxm", "error : " + ch + " at " + i);
                    }
                    break;
            }
        }
        if (!stack.isEmpty()) {
            Log.i("lxm", "error missing right delimiter");
        }
    }
}
