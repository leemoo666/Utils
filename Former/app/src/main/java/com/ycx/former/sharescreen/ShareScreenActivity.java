package com.ycx.former.sharescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/4/13.
 * 邮箱:287907160@qq.com
 */

public class ShareScreenActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_screen);

        linearLayout = (LinearLayout) findViewById(R.id.ll);
        textView = (TextView) findViewById(R.id.tv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pair存在V4包中

                Intent intent = new Intent(ShareScreenActivity.this, DetailActivity.class);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(ShareScreenActivity.this,
                        Pair.create((View) linearLayout, "shareAnim"),
                        Pair.create((View) textView, "shareAnimTextView")).toBundle();
                bundle.putString("lxm","haha");
                startActivity(intent, bundle);

            }
        });
    }

}
