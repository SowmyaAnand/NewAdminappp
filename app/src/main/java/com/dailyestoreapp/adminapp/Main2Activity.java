package com.dailyestoreapp.adminapp;
//  port androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
    private List<String> categoryNameList;
    private List<Integer>  categoryID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout2);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
        categoryNameList.add("ADD FIRST POP UP");
        categoryID.add(6);
        categoryNameList.add("ADD NEW COUPONS");
        categoryID.add(7);
        categoryNameList.add("ADD NEW SUB CATEGORY");
        categoryID.add(8);

if(savedInstanceState == null)
{
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new HomeFragment()).commit();
    navigationView.setCheckedItem(R.id.nav_home);
}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_home:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new HomeFragment()).commit();
            break;
            case R.id.nav_gallery:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new OrdersFragment()).commit();
                break;
            case R.id.nav_slideshow:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new PendingFragment()).commit();
                break;
            case R.id.nav_msg:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new MessagesFragment()).commit();
                break;
            case R.id.nav_cat:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new EditCategories()).commit();
                break;
            case R.id.nav_coupons:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new CouponsListingForEditing()).commit();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.e("MAin","Item selected ="+item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(Main2Activity.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account = new Intent(Main2Activity.this,MyAccount.class);
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
                    Intent i = new Intent(Main2Activity.this, AddCategory.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }

                else if(categoryId[which].equals(2))
                {
                    Intent i = new Intent(Main2Activity.this, Addpost.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(3))
                {
                    Intent i = new Intent(Main2Activity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(4))
                {
                    Intent i = new Intent(Main2Activity.this, Flyer1Activity.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(5))
                {
                    Intent i = new Intent(Main2Activity.this, Flyer2Activity.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }

                else if(categoryId[which].equals(6))
                {
                    Intent i = new Intent(Main2Activity.this, FirstpopUp.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(7))
                {
                    Intent i = new Intent(Main2Activity.this, CouponsList.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(8))
                {
                    Intent i = new Intent(Main2Activity.this, AddSubcategory.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
