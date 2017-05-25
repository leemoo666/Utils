package com.ycx.former.titanic;

import android.app.Activity;
import android.os.Bundle;

import com.ycx.former.R;


public class TitanicActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_titanic);
		
		TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

	    //设置字体
	    tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

	    //开启动画
	    Titanic titanic = new Titanic();
	    titanic.start(tv);
	    //关闭动画
	    //titanic.cancel();
	}
}
