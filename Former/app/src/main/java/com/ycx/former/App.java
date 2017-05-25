package com.ycx.former;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by 李小明 on 17/3/20.
 * 邮箱:287907160@qq.com
 */

public class App extends Application {


    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    /**
     * 得到全局上下文对象
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * 单例模式
     **/
    public static App getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {

        private static final App INSTANCE = new App();
    }

}
