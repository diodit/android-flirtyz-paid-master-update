package com.owo.phlurtyzpaid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.ViewPagerAdapter;
import com.owo.phlurtyzpaid.fragment.ActionFirstFragment;
import com.owo.phlurtyzpaid.fragment.ActionSecondFragment;
import com.owo.phlurtyzpaid.fragment.ActionThirdFragment;
import com.owo.phlurtyzpaid.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class ActionScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ActionFirstFragment actionFirstFragment;
    private ActionSecondFragment actionSecondFragment;
    private ActionThirdFragment actionThirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_screen);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);

        navigationView.setNavigationItemSelectedListener(this);

        actionFirstFragment = new ActionFirstFragment();
        actionSecondFragment = new ActionSecondFragment();
        actionThirdFragment = new ActionThirdFragment();

        tabLayout.setupWithViewPager(viewPager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(actionFirstFragment, "");
        viewPagerAdapter.addFragment(actionSecondFragment, "");
        viewPagerAdapter.addFragment(actionThirdFragment, "");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setText("home");
        tabLayout.getTabAt(1).setText("action");
        tabLayout.getTabAt(2).setText("basic");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);

        // return true;
        MenuInflater inflater = getMenuInflater();

        //redo menu
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Prefs prefs = new Prefs(this);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_favorites) {

            startActivity(new Intent(ActionScreen.this, FavoriteActivity.class));

//        }else if (id == R.id.nav_special) {
//
//            startActivity(new Intent(MainModActivity.this, SpecialsActivity.class));

        } else if (id == R.id.nav_recent) {

            startActivity(new Intent(ActionScreen.this, RecentActivity.class));

        } else if (id == R.id.nav_suggest) {

            startActivity(new Intent(ActionScreen.this, SuggestionActivity.class));

        }

//        else if (id == R.id.nav_account) {
//
//            if(prefs.isLoggedIn()){
//                startActivity(new Intent(ActionScreen.this, ProfileActivity.class));
//            }else{
//                startActivity(new Intent(ActionScreen.this, LoginActivity.class));
//            }
//
//
//        } else if (id == R.id.nav_myaccount){
//            Intent intent = new Intent(ActionScreen.this, GetStarted.class);
//            startActivity(intent);
//
//        }

        else if (id == R.id.Inapp) {
            Intent intent = new Intent(ActionScreen.this, FlirtyGroupPage.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        //add fragment to the viewpager
        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
        //to setup title of the tab layout
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}