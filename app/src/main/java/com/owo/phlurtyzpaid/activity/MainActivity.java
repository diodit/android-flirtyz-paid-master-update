package com.owo.phlurtyzpaid.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.ViewPagerAdapter;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.fragment.CategoryFragment;
import com.owo.phlurtyzpaid.model.CategoryAsset;
import com.owo.phlurtyzpaid.model.CategoryView;
import com.owo.phlurtyzpaid.utils.Prefs;
import com.owo.phlurtyzpaid.utils.Utils;

import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        setupViewPager(viewPager, "");

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager, final String filterText) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Map<String, CategoryView> categoryViewMap = new HashMap<>();

        try {

            FilenameFilter filenameFilter =new FilenameFilter() {
                @Override
                public boolean accept(File dir, String fileName) {

                    if(!filterText.isEmpty() && fileName.endsWith(PNG)){
                        if(fileName.toLowerCase().replaceAll(" ", "_").contains(filterText.toLowerCase().replaceAll(" ", "_"))  || fileName.equalsIgnoreCase(filterText.toLowerCase())){
                            return true;
                        }
                    }else{
                        if (fileName.endsWith(PNG)) {
                            return true;
                        }
                    }
                    return false;
                }
            };

            String[] imgPaths = this.getFilesDir().list(filenameFilter);

            for(String imgPath : imgPaths){

                String[] fileNameWithCategory = imgPath.split("-");

                String[] fileNameWithExt = fileNameWithCategory[1].split("\\.");

                String categoryName = fileNameWithCategory[0];

                String fileName = WordUtils.capitalizeFully(fileNameWithExt[0].replaceAll("_", " "));

                CategoryView categoryView = new CategoryView();

                CategoryAsset asset = new CategoryAsset();

                asset.setAssetName(fileName);

                asset.setAssetPath(imgPath);

                if(!categoryViewMap.containsKey(categoryName)){

                    categoryView.setCategoryName(categoryName);

                    List<CategoryAsset> assets = new ArrayList<>();

                    assets.add(asset);

                    categoryView.setCategoryAssetList(assets);

                    categoryViewMap.put(categoryName, categoryView);

                }else {

                    categoryViewMap.get(categoryName).getCategoryAssetList().add(asset);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        for (Map.Entry<String, CategoryView> entry : categoryViewMap.entrySet()) {

            Bundle bundle = new Bundle();

            ArrayList<String> itemNames = new ArrayList<>();

            ArrayList<String> imagePaths = new ArrayList<>();

            for (CategoryAsset asset : entry.getValue().getCategoryAssetList()) {

                itemNames.add(asset.getAssetName());

                imagePaths.add(asset.getAssetPath());

            }

            bundle.putStringArrayList(ITEM_NAME_KEY, itemNames);

            bundle.putStringArrayList(IMAGE_PATH_KEY, imagePaths);

            CategoryFragment fragment = new CategoryFragment();

            fragment.setArguments(bundle);

            adapter.addFragment(fragment, entry.getValue().getCategoryName());

        }

        viewPager.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(3);
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

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_favorites) {

            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));

//        }
//        else if (id == R.id.nav_special) {
//
//            startActivity(new Intent(MainActivity.this, SpecialsActivity.class));

        } else if (id == R.id.nav_recent) {

            startActivity(new Intent(MainActivity.this, RecentActivity.class));

        } else if (id == R.id.nav_suggest) {

            startActivity(new Intent(MainActivity.this, SuggestionActivity.class));

        }
//        else if (id == R.id.nav_unlock) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
