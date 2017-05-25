package com.ycx.former.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ycx.former.R;
import com.ycx.former.widget.BaseDialog;

/**
 * Created by 李小明 on 17/3/30.
 * 邮箱:287907160@qq.com
 */

public class DialogActivity extends Activity {

    BaseDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btShowDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BaseDialog(DialogActivity.this);
                dialog.show();
            }
        });
    }
}
