package com.gmail.hmazud.submissionmovie2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.hmazud.submissionmovie2.fragment.FavoriteFragment;
import com.gmail.hmazud.submissionmovie2.fragment.NowPlayingFragment;
import com.gmail.hmazud.submissionmovie2.fragment.UpComingFragment;
import com.gmail.hmazud.submissionmovie2.settings.SettingsService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabs);
        MainActivity.AdapterFragment adapterFragment = new AdapterFragment(getSupportFragmentManager());
        mViewPager.setAdapter(adapterFragment);
        tabLayout.setupWithViewPager(mViewPager);
        
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_np) {
            mViewPager.setCurrentItem(0);
            drawer.closeDrawers();
        } else if (id == R.id.nav_uc) {
            mViewPager.setCurrentItem(1);
            drawer.closeDrawers();
        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(MainActivity.this,SearchMovie.class);
            startActivity(intent);
        } else if (id == R.id.nav_lang) {
            Intent intent = new Intent(MainActivity.this,SettingsService.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class AdapterFragment extends FragmentStatePagerAdapter {
        public AdapterFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position == 0){
                fragment = new NowPlayingFragment();
            } else if (position == 1){
                fragment = new UpComingFragment();
            } else if (position == 2){
                fragment = new FavoriteFragment();
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String name = null;
            if (position == 0){
                name = getResources().getString(R.string.nowP);
            }
            if (position == 1){
                name = getResources().getString(R.string.upC);
            }
            if (position == 2){
                name = getResources().getString(R.string.fav);
            }
            return name;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
