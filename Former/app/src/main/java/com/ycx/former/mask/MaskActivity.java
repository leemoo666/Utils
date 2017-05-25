package com.ycx.former.mask;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.ycx.former.R;

/**
 * Created by 李小明 on 17/3/16.
 * 邮箱:287907160@qq.com
 *
 * 参考https://github.com/binIoter/GuideView.git
 */

public class MaskActivity extends FragmentActivity {

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask);

        tvOne = (TextView) findViewById(R.id.tvOneMask);
        tvTwo = (TextView) findViewById(R.id.tvTwoMask);
        tvThree = (TextView) findViewById(R.id.tvThreeMask);
        tvFour = (TextView) findViewById(R.id.tvFourMask);

        getWindow()
                .getDecorView()
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            getWindow()
                                    .getDecorView()
                                    .getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } else {
                            getWindow()
                                    .getDecorView()
                                    .getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
//                        mask2();
                        mask();
                    }
                });
    }

    private void mask() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(tvThree)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);

        builder.addComponent(new MutiComponent());
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(this);
    }

    private void mask2() {
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);

        View view = getLayoutInflater().inflate(R.layout.view_mask, null);

        content.addView(view);

    }
}
