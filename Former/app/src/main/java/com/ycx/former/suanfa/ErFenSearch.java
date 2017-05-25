package com.ycx.former.suanfa;

/**
 * Created by 李小明 on 17/5/16.
 * 邮箱:287907160@qq.com
 * 折半查找(前提是数组已经排序)
 */

public class ErFenSearch {


    private static int[] array = {1, 6, 10, 11, 14, 20, 27, 30};

    public static int testFind(int searchKey) {
        int lowerBounder = 0;
        int uperBounder = array.length - 1;
        while (true) {
            int curIndex = (lowerBounder + uperBounder) / 2;
            if (array[curIndex] == searchKey) {
                return curIndex;
            } else if (lowerBounder > uperBounder) {
                return array.length;
            } else {
                if (array[curIndex] < searchKey) {
                    lowerBounder = curIndex + 1;
                } else {
                    uperBounder = curIndex - 1;
                }
            }
        }
    }
}
