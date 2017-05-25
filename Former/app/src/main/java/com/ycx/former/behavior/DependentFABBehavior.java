package com.ycx.former.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 李小明 on 17/4/6.
 * 邮箱:287907160@qq.com
 */

public class DependentFABBehavior extends CoordinatorLayout.Behavior {

    public DependentFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return true;
    }

    /**
     * 当依赖对象发生变化时,产生回调,自定义改变child view
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child, View directTargetChild, View target, int nestedScrollAxes) {
        return false;
    }

    //[java] view plain copy 在CODE上查看代码片派生到我的代码片
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        // Do nothing
    }
}
