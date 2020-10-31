package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDealsImage extends AppCompatActivity {
    Spinner Category_spinner_deal,Sub_Category_spinner_deal;
    Button addimage;
    ACProgressFlower dialog;
    public static final String MY_PREFS_NAME = "AdminApp";
    ArrayList<Integer> categoriescatno_edit_deals = new ArrayList<>();
    ArrayList<Integer> matchingcategoriescatno_edit_deals = new ArrayList<>();
    ArrayList<Integer> subcategoriescatno_edit_deals = new ArrayList<>();
    ArrayAdapter<String> subadapter1_deals;
    Integer selected_cat_no_deal;
    List<String> subcategorylist1_deals;
    Button save_deal;
    ImageView Deal_img;
    File firstpopUpImage_deal;
    int flag_tyoe;
   String dealId;
    Bundle val;
    String selectedPathfirstpopUp_deal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deals_image);
         val = getIntent().getExtras();
        flag_tyoe=val.getInt("flag");
        if(flag_tyoe==1)
        {
            dealId=val.getString("dealid");
        }
        Category_spinner_deal = findViewById(R.id.deals_categoryspinner);
        save_deal =(Button)findViewById(R.id.edit_deal_save);
        addimage=(Button)findViewById(R.id.edit1);
        Deal_img =(ImageView)findViewById(R.id.first_popup_img);
        Sub_Category_spinner_deal = findViewById(R.id.deals_subcategoryspinner);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(AddDealsImage.this);
            }
        });
        save_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer sb = Sub_Category_spinner_deal.getSelectedItemPosition();
                Log.e("addpost","post sub id "+subcategoriescatno_edit_deals.get(sb));
                Integer ct = Category_spinner_deal.getSelectedItemPosition();
                Log.e("addpost","cat id "+categoriescatno_edit_deals.get(ct));
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
                Date d=new Date();
                final String from_to_date_deal=simpleDateFormat.format(d);



                if(categoriescatno_edit_deals.get(ct)==matchingcategoriescatno_edit_deals.get(sb))
                {
                    if(flag_tyoe==0)
                    {
                        uploadFirstPopup(firstpopUpImage_deal,categoriescatno_edit_deals.get(ct).toString(),subcategoriescatno_edit_deals.get(sb).toString(),from_to_date_deal);
                    }
                    else
                    {
                       Log.e("adddeals","deal id "+dealId);
                        updateDeal(firstpopUpImage_deal,categoriescatno_edit_deals.get(ct).toString(),subcategoriescatno_edit_deals.get(sb).toString(),from_to_date_deal);

                    }
                }
                else
                {
                    Toast.makeText(AddDealsImage.this,"Selected Sub Category does not belong to the selected category ",Toast.LENGTH_SHORT).show();

                }
            }
        });
        SharedPreferences shared = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedcatString = shared.getString("categories_new", "");
        String[] cats = savedcatString.split(",");//if spaces are uneven, use \\s+ instead of " "
        List<String> categorylist = new ArrayList<String>();
        categorylist.add("Select Category");
        for (String ct : cats) {

            categorylist.add(ct);
        }
//Cat_number
        SharedPreferences shared2 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedString = shared2.getString("categories_no_new", "subId");
        String[] numbers = savedString.split(",");//if spaces are uneven, use \\s+ instead of " "
        categoriescatno_edit_deals.add(0);
        matchingcategoriescatno_edit_deals.add(0);
        subcategoriescatno_edit_deals.add(0);
        for (String number : numbers) {
            categoriescatno_edit_deals.add(Integer.valueOf(number));
        }


        subcategorylist1_deals = new ArrayList<String>();


        subcategorylist1_deals.add("Select Sub Category");


        subadapter1_deals = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subcategorylist1_deals);

        subadapter1_deals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub_Category_spinner_deal.setAdapter(subadapter1_deals);
        Sub_Category_spinner_deal.setOnItemSelectedListener(new AddDealsImage.SubCategoriesSpinnerClass());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorylist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category_spinner_deal.setAdapter(adapter);
        Category_spinner_deal.setOnItemSelectedListener(new AddDealsImage.CategoriesSpinnerClass());
    }

    class CategoriesSpinnerClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            selected_cat_no_deal = categoriescatno_edit_deals.get(position);
            Log.e("addpost","after selecting the category"+selected_cat_no_deal+position);
            Log.e("addpost","after selecting the category"+categoriescatno_edit_deals);
            subcategoryactivatepost(selected_cat_no_deal);

        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class SubCategoriesSpinnerClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void subcategoryactivatepost(final Integer catid_dropdown) {

//        dialog = new ACProgressFlower.Builder(Addpost.this)
//                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                .themeColor(Color.WHITE)
//                .borderPadding(1)
//
//                .fadeColor(Color.DKGRAY).build();
//        dialog.show();

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
        Call<ListCategoryResponse> call = mainInterface.SubCategory(catid_dropdown);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res = new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
//                dialog.dismiss();
                if (success == 1) {
                    try {
                        JSONObject jo2 = new JSONObject(obj.toString());
                        JSONArray categoriesarray = jo2.getJSONArray("data");


                        for (int i = 0; i < categoriesarray.length(); i++) {
                            JSONObject j1 = categoriesarray.getJSONObject(i);
                            String sub_name = j1.getString("subName");
                            int item_no = Integer.parseInt(j1.getString("subId"));
                            int cat_item_no = Integer.parseInt(j1.getString("typeId"));
                            Log.e("Addpost","subname="+sub_name+catid_dropdown);
                            if (!subcategorylist1_deals.contains(sub_name)) {
                                subcategorylist1_deals.add(sub_name);
                                subcategoriescatno_edit_deals.add(item_no);
                                matchingcategoriescatno_edit_deals.add(cat_item_no);

                            }

                        }
                        if(subcategorylist1_deals.size()==0)
                        {


                        }
                        subadapter1_deals.notifyDataSetChanged();


                        //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    Toast.makeText(AddDealsImage.this,"No Sub Categories Found Under This Category",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
    public void uploadFirstPopup(final File file, final String ct_deal,final String sb_deal,String from_Date){
        dialog = new ACProgressFlower.Builder(AddDealsImage.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addDeal")
                .addMultipartFile("image",file)
                .addMultipartParameter("typeId", ct_deal)
                .addMultipartParameter("subId", sb_deal)
                .addMultipartParameter("itemId", "0")
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("fromDate", from_Date)
                .addMultipartParameter("toDate", from_Date)
                .setTag("uploads/items/")
                .setPriority(Priority.HIGH).doNotCacheResponse()
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {


                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response","response = "+response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject cat_no = (JSONObject) jsonObj.get("responsedata");

                            String success_value = String.valueOf(cat_no.get("success"));
                            Integer s  = Integer.valueOf(success_value);
                            Log.e("addcat","the cat no is "+s);
                            if(s==1)
                            {
                                Toast.makeText(AddDealsImage.this,"Deal Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(AddDealsImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AddDealsImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                    }
                });
dialog.dismiss();
    }
    public void updateDeal(final File file, final String ct_deal,final String sb_deal,String from_Date){
        dialog = new ACProgressFlower.Builder(AddDealsImage.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/editDeals")
                .addMultipartFile("image",file)
                .addMultipartParameter("dealId", dealId)
                .addMultipartParameter("typeId", ct_deal)
                .addMultipartParameter("subId", sb_deal)
                .addMultipartParameter("itemId", "0")
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("fromDate", from_Date)
                .addMultipartParameter("toDate", from_Date)
                .setTag("uploads/items/")
                .setPriority(Priority.HIGH).doNotCacheResponse()
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {


                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response","response = "+response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject cat_no = (JSONObject) jsonObj.get("responsedata");

                            String success_value = String.valueOf(cat_no.get("success"));
                            Integer s  = Integer.valueOf(success_value);
                            Log.e("addcat","the cat no is "+s);
                            if(s==1)
                            {
                                Toast.makeText(AddDealsImage.this,"Deal Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(AddDealsImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AddDealsImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                    }
                });
dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri_firstpopup = result.getUri();
                selectedPathfirstpopUp_deal = FileUtils.getPath(getApplicationContext(), resultUri_firstpopup);
                firstpopUpImage_deal=new File(selectedPathfirstpopUp_deal);
                Deal_img.setImageURI(resultUri_firstpopup);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
