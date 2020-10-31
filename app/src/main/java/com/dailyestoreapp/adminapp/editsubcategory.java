package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editsubcategory extends AppCompatActivity {
    RecyclerView subcategories;
    Integer edit_sub_maincategory_no;
    StringBuilder strbul  = new StringBuilder();
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    EditSubCategoriesAdapter sub_customAdapter_offers;
    public static final String MY_PREFS_NAME = "AdminApp";
    ArrayList<Integer> SubcategoriesEditCategies_no = new ArrayList<>();
    ArrayList<String> SubcategoriesEditCategies = new ArrayList<>();
    ArrayList<String> SubcategoriesEditCategies_image = new ArrayList<>();
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editsubcategory);
        Bundle bundle = getIntent().getExtras();

        Integer edit_sub_maincategory = bundle.getInt("edit_cat_no");
         edit_sub_maincategory_no = edit_sub_maincategory;
        subcategories = (RecyclerView)findViewById(R.id.subcategorieslist);
        subcategoryactivate();
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
       subcategories.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        sub_customAdapter_offers = new EditSubCategoriesAdapter(getApplicationContext(), SubcategoriesEditCategies,SubcategoriesEditCategies_image);
        subcategories.setAdapter(sub_customAdapter_offers);
    }
    private void subcategoryactivate()
    {


        int type = edit_sub_maincategory_no;
        String url = "http://dailyestoreapp.com/dailyestore/api/";

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
        Call<ListCategoryResponse> call = mainInterface.SubCategory(type);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                if(success==1)
                {
                    try {
                        JSONObject jo2 = new JSONObject(obj.toString());
                        JSONArray categoriesarray = jo2.getJSONArray("data");
                        Log.e("Fragment4","subcategories="+jo2);

                        for(int i=0; i<categoriesarray.length(); i++)
                        {
                            JSONObject j1= categoriesarray.getJSONObject(i);
                            String sub_name = j1.getString("subName");
                            int item_no = Integer.parseInt(j1.getString("subId"));

                            if(!SubcategoriesEditCategies.contains(sub_name))
                            {
                                SubcategoriesEditCategies.add(sub_name);
                                SubcategoriesEditCategies_no.add(item_no);


                            String sub_img = j1.getString("subItemImage");
                          SubcategoriesEditCategies_image.add(sub_img);

                            }

                        }

                        sub_customAdapter_offers.notifyDataSetChanged();

                        SharedPreferences.Editor editor1 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        Set<String> set = new HashSet<String>();
                        set.addAll(SubcategoriesEditCategies);
                        editor1.putStringSet("sub_categories", set);
                        editor1.apply();

                        Iterator<Integer> iter = SubcategoriesEditCategies_no.iterator();
                        while(iter.hasNext())
                        {
                            strbul.append(iter.next());
                            if(iter.hasNext()){
                                strbul.append(",");
                            }
                        }
                        strbul.toString();
                        Log.e("res","res="+strbul);
                        SharedPreferences.Editor editor2 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor2.putString("sub_categories_no", strbul.toString());
                        editor2.apply();



                        //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(editsubcategory.this,"No Data found",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
}
