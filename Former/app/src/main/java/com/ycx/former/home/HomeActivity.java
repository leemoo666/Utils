package com.ycx.former.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.ycx.former.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private HomeAdapter adapter;

    private TabLayout tabHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.vpHome);

        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
//            tabHome.addTab(tabHome.newTab().setText("第"+i+"个"));
            titles.add("第"+i+"个");
        }
        adapter = new HomeAdapter(this,titles);

        viewPager.setAdapter(adapter);

        tabHome = (TabLayout) findViewById(R.id.tabHome);


        tabHome.setTabMode(TabLayout.MODE_FIXED);


//        将tablelayout和ViewPager关联起来
        tabHome.setupWithViewPager(viewPager);
        tabHome.setTabsFromPagerAdapter(adapter);

    }
}
