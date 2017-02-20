package com.fuzhucheng.rxjavartrofitrecyclerview.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.fuzhucheng.rxjavartrofitrecyclerview.R;

import butterknife.BindView;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public abstract class DrawerActivity extends ToolbarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        drawerToggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.toolbar_navigation);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.publish:
//                startActivity(new Intent(this, PublishActivity.class));
                drawerLayout.closeDrawers();
                return true;

            case R.id.published:
//                startActivity(new Intent(this, PublishedActivity.class));
                drawerLayout.closeDrawers();
                return true;

            case R.id.growth:
//                startActivity(new Intent(this, GrowthActivity.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.collect:
//                startActivity(new Intent(this, CollectionActivity.class));
                drawerLayout.closeDrawers();
                return true;

            // 设置
            case R.id.setting:
//                startActivity(new Intent(this, SettingActivity.class));
                drawerLayout.closeDrawers();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
