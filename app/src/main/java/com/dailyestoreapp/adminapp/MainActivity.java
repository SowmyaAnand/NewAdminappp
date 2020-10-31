package com.dailyestoreapp.adminapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.dailyestoreapp.adminapp.ui.Messages.Messages;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private List<String> categoryNameList;
    private List<Integer>  categoryID;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // FloatingActionButton fab = findViewById(R.id.fab);
        categoryNameList = new ArrayList<>();
        categoryID = new ArrayList<>();
        categoryNameList.add("ADD NEW CATEGORY");
        categoryID.add(1);
        categoryNameList.add("ADD NEW ITEM");
        categoryID.add(2);
        categoryNameList.add("ADD YOUR DEALS OF THE DAY");
        categoryID.add(3);
        categoryNameList.add("ADD YOUR FIRST FLYERS ");
        categoryID.add(4);
        categoryNameList.add("ADD YOUR SECOND FLYERS");
        categoryID.add(5);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog();
//            }
//        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_msg)
                .setDrawerLayout(drawer)
                .build();
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"Offers");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Contact");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"My Account");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"Home");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Offers");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Contact");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"My Account");
//        ViewPager viewPager = findViewById(R.id.view_pager2);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.e("MAin","Item selected ="+item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(MainActivity.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account = new Intent(MainActivity.this,MyAccount.class);
                startActivity(account);
                return true;
            case R.id.add:
                alertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void alertDialog() {
        final CharSequence[] name = categoryNameList.toArray(new String[categoryNameList.size()]);
        final Integer[] categoryId = categoryID.toArray(new Integer[categoryID.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(name, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // String selectedCartegoryNme = name[which].toString();
                String selectedCategoryId = categoryId[which].toString();
                if(categoryId[which].equals(1))
                {
                    Intent i = new Intent(MainActivity.this, AddCategory.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }

                else if(categoryId[which].equals(2))
                {
                    Intent i = new Intent(MainActivity.this, Addpost.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(3))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(4))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(5))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }


            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.nav_msg:
                //changed during home fragment PLEASE CHECK
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new fragment3("test")).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
