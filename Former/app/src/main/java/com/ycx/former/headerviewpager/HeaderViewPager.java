package com.ycx.former.headerviewpager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 李小明 on 17/5/17.
 * 邮箱:287907160@qq.com
 */

public class HeaderViewPager extends LinearLayout {

    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = 2;

    private Scroller mScroller;
    private int mTouchSlop;         //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
    private int mMinimumVelocity;   //允许执行一个fling手势动作的最小速度值
    private int mMaximumVelocity;   //允许执行一个fling手势动作的最大速度值
    private int sysVersion;         //当前sdk版本，用于判断api版本
    private View mHeadView;         //需要被滑出的头部
    private int mHeadHeight;        //滑出头部的高度
    private int minY = 0;           //最小的距离， 头部在最顶部
    private int mCurY;              //当前已经滚动的距离
    private VelocityTracker mVelocityTracker;
    private int mDirection;
    private int mLastScrollerY;

    private float mDownX;  //第一次按下的x坐标
    private float mDownY;  //第一次按下的y坐标
    private float mLastY;  //最后一次移动的Y坐标
    private boolean verticalScrollFlag = false;   //是否允许垂直滚动

    public HeaderViewPager(Context context) {
        super(context);
        init(context);
    }

    public HeaderViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();   //表示滑动的时候，手的移动要大于这个距离才开始移动控件。
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity(); //允许执行一个fling手势动作的最小速度值
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity(); //允许执行一个fling手势动作的最大速度值
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeadView = getChildAt(0);
        measureChildWithMargins(mHeadView, widthMeasureSpec, 0, MeasureSpec.UNSPECIFIED, 0);
        mHeadHeight = mHeadView.getMeasuredHeight();
        //让测量高度加上头部的高度
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) + mHeadHeight, MeasureSpec.EXACTLY));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float currentX = event.getX();                   //当前手指相对于当前view的X坐标
        float currentY = event.getY();                   //当前手指相对于当前view的Y坐标
        float shiftX = Math.abs(currentX - mDownX);   //当前触摸位置与第一次按下位置的X偏移量
        float shiftY = Math.abs(currentY - mDownY);   //当前触摸位置与第一次按下位置的Y偏移量
        float deltaY;                                 //滑动的偏移量，即连续两次进入Move的偏移量
        mVelocityTracker.addMovement(event);                 //初始化速度追踪器
        switch (event.getAction()) {
            //Down事件主要初始化变量
            case MotionEvent.ACTION_DOWN:
                verticalScrollFlag = false;
                mDownX = currentX;
                mDownY = currentY;
                mLastY = currentY;
                mScroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                deltaY = mLastY - currentY; //连续两次进入move的偏移量
                mLastY = currentY;
                if (shiftX > mTouchSlop && shiftX > shiftY) {
                    //水平滑动
                    verticalScrollFlag = false;
                } else if (shiftY > mTouchSlop && shiftY > shiftX) {
                    //垂直滑动
                    verticalScrollFlag = true;
                }
                /**
                 * 这里要注意，对于垂直滑动来说，给出以下三个条件
                 * 头部没有固定，允许滑动的View处于第一条可见，当前按下的点在头部区域
                 * 三个条件满足一个即表示需要滚动当前布局，否者不处理，将事件交给子View去处理
                 */
                if (verticalScrollFlag) {
                    //如果是向下滑，则deltaY小于0，对于scrollBy来说
                    //正值为向上和向左滑，负值为向下和向右滑，这里要注意
                    scrollBy(0, (int) (deltaY + 0.5));
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (verticalScrollFlag) {
                    mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity); //1000表示单位，每1000毫秒允许滑过的最大距离是mMaximumVelocity
                    float yVelocity = mVelocityTracker.getYVelocity();  //获取当前的滑动速度
                    mDirection = yVelocity > 0 ? DIRECTION_DOWN : DIRECTION_UP;  //下滑速度大于0，上滑速度小于0
                    //根据当前的速度和初始化参数，将滑动的惯性初始化到当前View，至于是否滑动当前View，取决于computeScroll中计算的值
                    //这里不判断最小速度，确保computeScroll一定至少执行一次
                    mScroller.fling(0, getScrollY(), 0, -(int) yVelocity, 0, 0, -Integer.MAX_VALUE, Integer.MAX_VALUE);
                    mLastScrollerY = getScrollY();
                    invalidate();  //更新界面，该行代码会导致computeScroll中的代码执行
                    //阻止快读滑动的时候点击事件的发生，滑动的时候，将Up事件改为Cancel就不会发生点击了

                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.recycle();
                break;
            default:
                break;
        }
        //手动将事件传递给子View，让子View自己去处理事件
        super.dispatchTouchEvent(event);
        //消费事件，返回True表示当前View需要消费事件，就是事件的TargetView
        return true;
    }


    /**
     * 对滑动范围做限制
     */
    @Override
    public void scrollBy(int x, int y) {
        int scrollY = getScrollY();
        int toY = scrollY + y;
        if (toY >= mHeadHeight) {
            toY = mHeadHeight;
        } else if (toY <= minY) {
            toY = minY;
        }
        y = toY - scrollY;
        super.scrollBy(x, y);
    }

}

