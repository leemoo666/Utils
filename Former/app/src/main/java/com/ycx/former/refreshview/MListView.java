package com.ycx.former.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by 李小明 on 17/5/22.
 * 邮箱:287907160@qq.com
 */

public class MListView extends ListView {
    public MListView(Context context) {
        super(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int mDownY;
    int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int currentY = (int) ev.getY();                   //当前手指相对于当前view的Y坐标
        float deltaY;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = currentY;
                mLastY = currentY;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                deltaY = mLastY - currentY; //连续两次进入move的偏移量
                mLastY = currentY;
                if ((getFirstVisiblePosition() == 0 && deltaY < 0)
                        || (getLastVisiblePosition() == (getCount() - 1) && deltaY > 0)) { //拦截
                    Log.i("lxm","拦截");
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    Log.i("lxm","不拦截");
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(ev);

    }
}
