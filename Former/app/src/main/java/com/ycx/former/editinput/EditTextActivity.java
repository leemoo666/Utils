package com.ycx.former.editinput;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/5/2.
 * 邮箱:287907160@qq.com
 */

public class EditTextActivity extends Activity {

    private EditText tvEditInput;

    private TextInputLayout tvInputLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_input);

        tvInputLayout = (TextInputLayout) findViewById(R.id.tvInputLayout);
        tvInputLayout.setHintEnabled(false);
        tvInputLayout.setError("请输入东方时尚注册的手机号");
        tvInputLayout.setErrorEnabled(false);


        tvEditInput = (EditText) findViewById(R.id.tvEditInput);
        tvEditInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("lxm","length = "+tvEditInput.getText().toString().trim().length());
                if (tvEditInput.getText().toString().trim().length() == 0) {
                    Log.i("lxm", " ===== 0");
                    tvInputLayout.setErrorEnabled(false);
                } else {
                    Log.i("lxm", " !!!!! 0");
                    tvInputLayout.setErrorEnabled(true);
                    tvInputLayout.setError("请输入东方时尚注册的手机号");
                }
            }
        });

    }
}
