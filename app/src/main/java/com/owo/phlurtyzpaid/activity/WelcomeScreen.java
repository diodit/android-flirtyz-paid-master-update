package com.owo.phlurtyzpaid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.ViewPagerAdapter;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.ApiCallback;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.fragment.FirstFragment;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;
import com.owo.phlurtyzpaid.utils.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Response;

public class WelcomeScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private static ArrayList<AllCategory> fetchedData;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

      drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();



        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        viewPager = findViewById(R.id.viewpager);

        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager, final String filterText) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        int i = 0;
        for (ListIterator<AllCategory> fetched = fetchedData.listIterator(); fetched.hasNext(); i++) {
            AllCategory allCategory = fetched.next();
            if(i == 0){
                FirstFragment mainModCategoryFragment = new FirstFragment();
                adapter.addFragment(mainModCategoryFragment, allCategory.getName());
            }
        }

        if(!filterText.isEmpty()){
            adapter.notifyDataSetChanged();
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));



        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                setupViewPager(viewPager, newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                setupViewPager(viewPager, query);
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Prefs prefs = new Prefs(this);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_favorites) {

            startActivity(new Intent(WelcomeScreen.this, FavoriteActivity.class));

//        }else if (id == R.id.nav_special) {
//
//            startActivity(new Intent(MainModActivity.this, SpecialsActivity.class));

        } else if (id == R.id.nav_recent) {

            startActivity(new Intent(WelcomeScreen.this, RecentActivity.class));

        } else if (id == R.id.nav_suggest) {

            startActivity(new Intent(WelcomeScreen.this, SuggestionActivity.class));

        } else if (id == R.id.nav_account) {

            if(prefs.isLoggedIn()){
                startActivity(new Intent(WelcomeScreen.this, ProfileActivity.class));
            }else{
                startActivity(new Intent(WelcomeScreen.this, LoginActivity.class));
            }


        } else if (id == R.id.nav_myaccount){
            Intent intent = new Intent(WelcomeScreen.this, GetStarted.class);
            startActivity(intent);

        }
//        else if (id == R.id.nav_unlock) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}