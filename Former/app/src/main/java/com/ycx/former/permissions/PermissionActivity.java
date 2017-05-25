package com.ycx.former.permissions;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.ycx.former.R;

import rx.functions.Action1;

/**
 * Created by 李小明 on 17/5/22.
 * 邮箱:287907160@qq.com
 */

public class PermissionActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        findViewById(R.id.btCamera).setOnClickListener(this);
        findViewById(R.id.btSDCard).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCamera:
                new RxPermissions(this).request(Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean flag) {
                                if (flag) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                    Log.i("lxm", "拍照授权成功");
                                } else {
                                    Log.i("lxm", "拍照授权失败");
                                }
                            }
                        });
                break;
            case R.id.btSDCard:
                new RxPermissions(this).request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean flag) {
                                if (flag) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                    Log.i("lxm", "SD卡授权成功");
                                } else {
                                    Log.i("lxm", "SD卡授权失败");
                                }
                            }
                        });

//                RxView.clicks(findViewById(R.id.btSDCard))
//                        .compose(new RxPermissions(this).ensure(Manifest.permission.CAMERA))
//                        .subscribe(new Action1<Boolean>() {
//                            @Override
//                            public void call(Boolean aBoolean) {
//                                if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
//                                    Log.i("lxm", "SD卡授权成功");
//                                } else {
//                                    Log.i("lxm", "SD卡授权失败");
//                                }
//                            }
//                        });
                break;
        }
    }
}
