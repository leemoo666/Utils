package com.ycx.former.shimmer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ycx.former.R;
import com.ycx.former.widget.ShimmerView;

/**
 * Created by 李小明 on 17/3/16.
 * 邮箱:287907160@qq.com
 *参考 https://github.com/RomainPiel/Shimmer-android.git
 *
 */

public class ShimmerActivity extends FragmentActivity {

    private ShimmerView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);

        view = (ShimmerView) findViewById(R.id.svShimmer);
        findViewById(R.id.btShimmer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnim();

            }
        });

    }
}
