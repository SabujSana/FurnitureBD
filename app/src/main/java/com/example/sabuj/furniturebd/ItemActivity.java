package com.example.sabuj.furniturebd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sabuj.furniturebd.fragments.Fragment_almirah;
import com.example.sabuj.furniturebd.fragments.Fragment_alna;
import com.example.sabuj.furniturebd.fragments.Fragment_bed;
import com.example.sabuj.furniturebd.fragments.Fragment_chair;
import com.example.sabuj.furniturebd.fragments.Fragment_table;
import com.example.sabuj.furniturebd.fragments.Fragment_wardrobe;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        if (navView != null) {
            setupDrawerContent(navView);
        }

        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ItemActivity.ViewPagerAdapter adapter = new ItemActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_chair(), "Chair");
        adapter.addFragment(new Fragment_table(), "Table");
        adapter.addFragment(new Fragment_bed(), "Bed");
        adapter.addFragment(new Fragment_alna(), "Alna");
        adapter.addFragment(new Fragment_almirah(), "Almirah");
        adapter.addFragment(new Fragment_wardrobe(), "Wardrobe");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId()) {
                    case R.id.chair:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.table:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bed:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.alna:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.almirah:
                        viewPager.setCurrentItem(4);
                        break;
                    case R.id.wardrobe:
                        viewPager.setCurrentItem(5);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("");
//        toolbar.setSubtitle("");
//        toolbar.setLogo(R.drawable.chair_logo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, false);
        } else {
            finish();
        }
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//      //  System.exit(0);
//    }
}
