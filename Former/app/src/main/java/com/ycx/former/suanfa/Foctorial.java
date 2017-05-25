package com.ycx.former.suanfa;

/**
 * Created by 李小明 on 17/5/16.
 * 邮箱:287907160@qq.com
 */

public class Foctorial {

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else
            return n * factorial(n - 1);
    }

}
