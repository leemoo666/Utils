package com.ycx.former.suanfa;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by 李小明 on 17/5/16.
 * 邮箱:287907160@qq.com
 * 排序
 */

public class Sort {


    private static int[] array = {1, 16, 30, 11, 44, 20, 27, 30};

    /**冒泡排序升序
     *
     * 数组中有N项数据,则第一趟排序中有N-1次比较,第二趟中有N-2次比较.
     * 如此类推,这种排序的求和公式为N*(n-1)/ 2.
     * 这种算法做了约N*N / 2次比较,交换次数为N*N / 4,即大概有一半的数据需要交换
     * */

    public static void sortAsc() {
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[i] < array[j]) {
                    swap(i, j);
                }
            }
        }
        Log.i("lxm", "array = " + Arrays.toString(array));
    }

    private static void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**选择排序
     *
     * 改进了冒泡排序,将(交换)次数为N*N / 2减少到了N次,不幸的是(比较)次数仍为N*N/ 2
     * 大量记录需要在内存中移动,这就使交换时间和比较时间相比,交换时间更重要
     *
     */
    public static void selectSort() {
        int min;
        for (int out = 0; out < array.length - 1; out++) {
            min = out;
            for (int in = out + 1; in < array.length; in++) {
                if (array[in] < array[min]) {
                    min = in;
                }
            }
            swap(out, min);
        }
        Log.i("lxm", "array = " + Arrays.toString(array));
    }

    /**
     * 插入排序
     *
     *这个算法在第一次排序中,他最多比较一次,第二次比第一次多比较两次,最后一趟为N-1次.
     * 因此,总共比较次数为N*(N-1) / 2
     * 然而,因为每趟排序发现插入点之前,平均只有一半真的进行了比较,因此复制次数为N*(N-1) / 4
     * 复制的次数大致等于比较次数,然而一次复制与一次交换的时间消费不同,所以这个算法比冒泡排序快一倍,比选择排序略快
     * */
    public static void insertSort(){
        int in;
        for (int out = 0; out < array.length; out++) {
            int tmp = array[out];
            in = out;
            while (in > 0 && array[in-1] >=tmp){
                array[in] = array[in-1];
                --in;
            }
            array[in] = tmp;
        }
        Log.i("lxm", "insert array = " + Arrays.toString(array));

    }

}
