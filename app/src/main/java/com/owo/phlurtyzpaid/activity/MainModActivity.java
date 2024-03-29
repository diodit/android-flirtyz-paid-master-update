package com.owo.phlurtyzpaid.activity;

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

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.ViewPagerAdapter;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.ApiCallback;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment10;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment11;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment12;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment13;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment14;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment15;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment2;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment3;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment4;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment5;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment6;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment7;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment8;
import com.owo.phlurtyzpaid.fragment.MainModCategoryFragment9;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;
import com.owo.phlurtyzpaid.utils.Prefs;
import com.owo.phlurtyzpaid.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Response;

public class MainModActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static ArrayList<AllCategory> fetchedData;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private static final String PNG = ".png";

    private static final String ITEM_NAME_KEY="itemNames";

    private static final String IMAGE_PATH_KEY="imagePaths";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = this;

        Prefs prefs = new Prefs(this);

        prefs.open();

        if(prefs.isFirstTime()){

            Utils.copyAssets(this);

        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        getCategories();

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager, final String filterText) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        int i = 0;
        for (ListIterator<AllCategory> fetched = fetchedData.listIterator(); fetched.hasNext(); i++) {
            AllCategory allCategory = fetched.next();
            Bundle bundle = new Bundle();
            if(i == 0){
                MainModCategoryFragment mainModCategoryFragment = new MainModCategoryFragment();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment, allCategory.getName());
            }

            if(i == 1){
                MainModCategoryFragment2 mainModCategoryFragment2 = new MainModCategoryFragment2();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment2.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment2, allCategory.getName());
            }

            if(i == 2){
                MainModCategoryFragment3 mainModCategoryFragment3 = new MainModCategoryFragment3();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment3.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment3, allCategory.getName());
            }

            if(i == 3){
                MainModCategoryFragment4 mainModCategoryFragment4 = new MainModCategoryFragment4();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment4.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment4, allCategory.getName());
            }

            if(i == 4){
                MainModCategoryFragment5 mainModCategoryFragment5 = new MainModCategoryFragment5();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment5.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment5, allCategory.getName());
            }

            if(i == 5){
                MainModCategoryFragment6 mainModCategoryFragment6 = new MainModCategoryFragment6();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment6.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment6, allCategory.getName());
            }

            if(i == 6){
                MainModCategoryFragment7 mainModCategoryFragment7 = new MainModCategoryFragment7();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment7.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment7, allCategory.getName());
            }

            if(i == 7){
                MainModCategoryFragment8 mainModCategoryFragment8 = new MainModCategoryFragment8();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment8.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment8, allCategory.getName());
            }

            if(i == 8){
                MainModCategoryFragment9 mainModCategoryFragment9 = new MainModCategoryFragment9();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment9.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment9, allCategory.getName());
            }

            if(i == 9){
                MainModCategoryFragment10 mainModCategoryFragment10 = new MainModCategoryFragment10();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment10.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment10, allCategory.getName());
            }

            if(i == 10){
                MainModCategoryFragment11 mainModCategoryFragment11 = new MainModCategoryFragment11();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment11.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment11, allCategory.getName());
            }

            if(i == 11){
                MainModCategoryFragment12 mainModCategoryFragment12 = new MainModCategoryFragment12();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment12.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment12, allCategory.getName());
            }

            if(i == 12){
                MainModCategoryFragment13 mainModCategoryFragment13 = new MainModCategoryFragment13();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment13.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment13, allCategory.getName());
            }

            if(i == 13){
                MainModCategoryFragment14 mainModCategoryFragment14 = new MainModCategoryFragment14();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment14.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment14, allCategory.getName());
            }

            if(i == 14){
                MainModCategoryFragment15 mainModCategoryFragment15 = new MainModCategoryFragment15();
                bundle.putString("filterText", filterText);
                bundle.putString("categoryId", String.valueOf(allCategory.getId()));

                mainModCategoryFragment15.setArguments(bundle);
                adapter.addFragment(mainModCategoryFragment15, allCategory.getName());
            }

        }

        if(!filterText.isEmpty()){
            adapter.notifyDataSetChanged();
        }
        viewPager.setAdapter(adapter);
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

            startActivity(new Intent(MainModActivity.this, FavoriteActivity.class));

//        }else if (id == R.id.nav_special) {
//
//            startActivity(new Intent(MainModActivity.this, SpecialsActivity.class));

        } else if (id == R.id.nav_recent) {

            startActivity(new Intent(MainModActivity.this, RecentActivity.class));

        } else if (id == R.id.nav_suggest) {

            startActivity(new Intent(MainModActivity.this, SuggestionActivity.class));

        }

//        else if (id == R.id.nav_account) {
//
//            if(prefs.isLoggedIn()){
//                startActivity(new Intent(MainModActivity.this, ProfileActivity.class));
//            }else{
//                startActivity(new Intent(MainModActivity.this, LoginActivity.class));
//            }
//
//
//        } else if (id == R.id.nav_myaccount){
//            Intent intent = new Intent(MainModActivity.this, GetStarted.class);
//            startActivity(intent);
//
//        }
        else if (id == R.id.Inapp) {

            Intent intent = new Intent(MainModActivity.this, FlirtyGroupPage.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void getCategories(){
        getCategoriesFromApi(MainModActivity.this, new ApiCallback() {
            @Override
            public void onResponse(boolean success) {
                if(success){
                    setupViewPager(viewPager, "");
                }else {

                    AlertDialog alertDialog = new AlertDialog.Builder(MainModActivity.this).create();
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    getCategories();
                                }
                            });

                    if(isOnline(MainModActivity.this)){
                        alertDialog.setTitle("Oops!");
                        alertDialog.setMessage("Please check your internet connectivity and try again.");
                        alertDialog.show();
                    }else{
                        alertDialog.setTitle("Oops!");
                        alertDialog.setMessage("We could not get any emogi at this time.");
                        alertDialog.show();
                    }


                }
            }
        });
    }

    public boolean isOnline(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());

    }


    public static void getCategoriesFromApi(final Context context, final ApiCallback callback){
        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getAllData(ApiEndPoints.CategoryAll);

        call.enqueue(new retrofit2.Callback<List<AllCategory>>() {
            @Override
            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {

                if (response.isSuccessful()) {

                    ArrayList<AllCategory> imageObjects = new ArrayList<>();

                    for(AllCategory fetchd : response.body()) {
                        AllCategory allCategory = new AllCategory();
                        allCategory.setName(fetchd.getName());
                        allCategory.setId(fetchd.getId());

                        imageObjects.add(allCategory);
                    }
                    fetchedData = imageObjects;


                    if(response.body().size() < 1){
                        Toast.makeText(context, "No categories found.", Toast.LENGTH_SHORT).show();
                    }

                    callback.onResponse(response.body() != null);
                }else{
                    Toast.makeText(context, "An error occurred while fetching categories." + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    callback.onResponse(false);
                }

            }

            @Override
            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
                call.cancel();
                Log.e("error", "onFailure: ",t );
                Toast.makeText(context, "Connection Error.", Toast.LENGTH_SHORT).show();
                callback.onResponse(false);
            }
        });

    }

}
