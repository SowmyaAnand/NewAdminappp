package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class Food_second_layer extends AppCompatActivity {
    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.newvegetable,R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable, R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList<String> item_image = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Offers_ItemAdapter customAdapter_offers;
    ArrayList<Integer> item_id = new ArrayList<>();
    ArrayList<Integer> item_id_offer = new ArrayList<>();
    ArrayList<String> Item_categories_offer_desc = new ArrayList<>();
    OffersListingsubcategoryadapter customadapter2_offers;
    ArrayList<Integer> Item_Quantity = new ArrayList<>();
    ArrayList<Integer> Item_Price = new ArrayList<>();
    ArrayList<Integer> item_id_status = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_second_layer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("FOOD");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        itemlistingcategory_offers = (RecyclerView)findViewById(R.id.recyclerView_categories_offer_categories);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        customadapter2_offers = new OffersListingsubcategoryadapter(getApplicationContext(), personNames_offers,Images_offers);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        recyclerView_offers = (RecyclerView)findViewById(R.id.itemrecycler_offers_categories);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new Offers_ItemAdapter(getApplicationContext(), personNames_offers,item_image,Item_Quantity,Item_Price,item_id,item_id_status,Item_categories_offer_desc,item_id_offer);
        recyclerView_offers.setAdapter(customAdapter_offers);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.e("MAin","Item selected ="+item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(Food_second_layer.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account = new Intent(Food_second_layer.this,MyAccount.class);
                startActivity(account);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
