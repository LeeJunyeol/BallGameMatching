package com.project.ballgamematching;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements Serializable,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Intent intent;
    private GoogleSignInAccount acct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        acct = (GoogleSignInAccount) intent.getExtras().get("acct");





        // activity_main에 있는 툴바를 액션바로 이용
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_common);
        toolbar.setTitle("testett");
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_main);

        tabLayout.addTab(tabLayout.newTab().setText("체육시설"));
        tabLayout.addTab(tabLayout.newTab().setText("상대찾기"));
        tabLayout.addTab(tabLayout.newTab().setText("즐겨찾기"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            /**
             * Called when a tab enters the selected state.
             *
             * @param tab The tab that was selected
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            /**
             * Called when a tab exits the selected state.
             *
             * @param tab The tab that was unselected
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            /**
             * Called when a tab that is already selected is chosen again by the user. Some applications
             * may use this action to return to the top level of a category.
             *
             * @param tab The tab that was reselected.
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // drawer 부분

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

        // drawer 버튼 누르면 실행
        ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            intent = new Intent(this, SettingLayoutActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_signOut){
            intent = new Intent(this, SignOutActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



    @Override
    public void onClick(View v) {

    }


}
