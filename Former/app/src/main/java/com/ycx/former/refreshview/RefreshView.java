package com.ycx.former.refreshview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by 李小明 on 17/5/22.
 * 邮箱:287907160@qq.com
 */

public class RefreshView extends LinearLayout {

    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = 2;

    private Scroller mScroller;
    private int mTouchSlop;         //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
    private int mMinimumVelocity;   //允许执行一个fling手势动作的最小速度值
    private int mMaximumVelocity;   //允许执行一个fling手势动作的最大速度值

    private View mHeadView;         //需要被滑出的头部
    private int mHeadHeight;        //滑出头部的高度
    private View mFootView;         //需要被滑出的底部
    private int mFootHeight;        //滑出底部的高度

    private MListView recyclerView; //

    private VelocityTracker mVelocityTracker;   //速度计算

    private float mDownY;  //第一次按下的y坐标
    private float mLastY;  //最后一次移动的Y坐标

    public RefreshView(Context context) {
        super(context);
        init(context);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();   //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity(); //允许执行一个fling手势动作的最小速度值
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity(); //允许执行一个fling手势动作的最大速度值
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            height += getChildAt(i).getMeasuredHeight();
        }
        Log.i("lxm", "height = " + height);
        setMeasuredDimension(getMeasuredWidth(), height);
        mHeadView = getChildAt(0);
        mHeadHeight = mHeadView.getHeight();

        recyclerView = (MListView) getChildAt(1);

        mFootView = getChildAt(getChildCount() - 1);
        mFootHeight = mFootView.getHeight();
        scrollTo(0, mHeadHeight);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("lxm", "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i("lxm", "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
//                Log.i("lxm", "dispatchTouchEvent ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("lxm", "onInterceptHoverEvent ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.i("lxm", "onInterceptHoverEvent ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.i("lxm", "onInterceptHoverEvent ACTION_UP");
                return true;
        }
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float currentY = ev.getY();                   //当前手指相对于当前view的Y坐标
        float shiftY = Math.abs(currentY - mDownY);   //当前触摸位置与第一次按下位置的Y偏移量
        float deltaY;                                 //滑动的偏移量，即连续两次进入Move的偏移量
        obtainVelocityTracker(ev);                    //初始化速度追踪器
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = currentY;
                mLastY = currentY;
                mScroller.abortAnimation();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i("lxm","...........move");
                deltaY = mLastY - currentY; //连续两次进入move的偏移量
                mLastY = currentY;

                int scrollTmp = getScrollY() + (int) deltaY;
                if (shiftY > mTouchSlop) {
                    //初始化时头部要隐藏在上面
                    if (scrollTmp <= 0) {  //滑到顶部
                        scrollTo(0, 0);
                    } else if (scrollTmp >= (mHeadHeight + mFootHeight)) { //滑到底部
                        scrollTo(0, (mHeadHeight + mFootHeight));
                    } else {
                        if (scrollTmp >= mHeadHeight) {// 滑到底部了

                            requestDisallowInterceptTouchEvent(false);

                        } else {
                            scrollBy(0, (int) deltaY);
                            requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


    private void obtainVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
