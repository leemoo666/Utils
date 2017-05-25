package com.ycx.former.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/3/30.
 * 邮箱:287907160@qq.com
 */

public class BaseDialog extends Dialog {

    protected Context mContext;

    public BaseDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;

    }

    {
//        requestWindowFeature(Window.);
        setContentView(R.layout.wait_dialog);


        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }


}
