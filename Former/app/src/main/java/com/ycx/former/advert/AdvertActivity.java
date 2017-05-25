package com.ycx.former.advert;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ycx.former.R;
import com.ycx.former.banner.BannerActivity;
import com.ycx.former.utils.SPUtil;

/**
 * Created by 李小明 on 17/3/20.
 * 邮箱:287907160@qq.com
 */

public class AdvertActivity extends FragmentActivity {

    private ImageView ivDefault; //默認圖片
    private ImageView ivAdvert; //廣告圖片

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        ivDefault = (ImageView) findViewById(R.id.ivDefault);
        ivAdvert = (ImageView) findViewById(R.id.ivAdvert);


        SPUtil.getInstance().putIntForKey("IMAGE_URL",0);
        findViewById(R.id.btClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNetConnect();
            }
        });


    }

    private int[] images = {
            R.drawable.aaa,
            R.drawable.bbbb,
            0,
            R.drawable.cccc,
            R.drawable.cccc
    };

    private boolean isAd;//是否有廣告
    private int position = 0;

    private void getNetConnect() {
        //position >= 4
        Log.i("lxm","position = "+position);
        if (position >= images.length) {
            position = 0;
        }

        if (images[position] == 0) {  //从网络获取的数据为空,就是没有广告
            Log.i("lxm", "11111网络数据为空");
            if (SPUtil.getInstance().getIntForKey("IMAGE_URL") == 0) {//如果原來也为空
                Log.i("lxm", "2222本地数据为空");
                //停留三秒,跳到首页
                Intent intent = new Intent(AdvertActivity.this, BannerActivity.class);
                startActivity(intent);
                ivDefault.setVisibility(View.VISIBLE);
                ivAdvert.setVisibility(View.GONE);
            } else {  //本地已經存儲廣告了
                Log.i("lxm", "33333本地数据有廣告");
                ivDefault.setVisibility(View.GONE);
                ivAdvert.setVisibility(View.VISIBLE);
                ivAdvert.setBackgroundResource(SPUtil.getInstance().getIntForKey("IMAGE_URL"));
                //將保存的數據清空
                SPUtil.getInstance().putIntForKey("IMAGE_URL", images[position]);
            }


        } else { //从网络获取的数据不為空,就是有广告
            Log.i("lxm", "44444網絡数据不為空");
            if (SPUtil.getInstance().getIntForKey("IMAGE_URL") == 0) {//如果原來也为空
                Log.i("lxm", "55555本地數據為空");
                //停留三秒,跳到首页
                ivDefault.setVisibility(View.VISIBLE);
                ivAdvert.setVisibility(View.GONE);
                Intent intent = new Intent(AdvertActivity.this, BannerActivity.class);
                startActivity(intent);
            } else {  //本地已經存儲廣告了
                Log.i("lxm", "6666本地數據有廣告");
                ivDefault.setVisibility(View.GONE);
                ivAdvert.setVisibility(View.VISIBLE);
                ivAdvert.setBackgroundResource(SPUtil.getInstance().getIntForKey("IMAGE_URL"));
            }

            Log.i("lxm", "下載圖片");
            //后台下载图片,并保存圖片路徑
            SPUtil.getInstance().putIntForKey("IMAGE_URL", images[position]);

        }
        position++;


    }

}
