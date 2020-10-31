package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlyersandDeals extends AppCompatActivity {
String categoryselected;
Integer id;
TextView txt;
Button ed1,ed2,ed3,ed4;
    Button ad1,ad2,ad3,ad4;
    String selectedDealid="0";
    ACProgressFlower dialog;
Button flyers_save,refrsh;
int flagpic1,flagpic2,flagpic3,flagpic4;
ImageView img1,img2,img3,img4;
    File firstFlyerImage,secondFlyerImage,thirdFlyerImage,fourthFlyerImage;
    String selectedPathfirstFlyerImage="";
    String selectedPathsecondFlyerImage="";
    String selectedPaththirdFlyerImage="";
    String selectedPathFourthFlyerImage="";
    ArrayList<String> deals_id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyersand_deals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD IMAGES");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Bundle val = getIntent().getExtras();
        categoryselected=val.getString("CATEGORYID");
        Log.e("iddd","the is selecetd is "+categoryselected);
        id= Integer.valueOf(categoryselected);
        deals_id.add("0");
        deals_id.add("0");
        deals_id.add("0");
        deals_id.add("0");


        ed1=(Button)findViewById(R.id.edit1);
        ed2=(Button)findViewById(R.id.edit2);
        ed3=(Button)findViewById(R.id.edit3);
        ed4=(Button)findViewById(R.id.edit4);
        ad1=(Button)findViewById(R.id.add1);
        ad2=(Button)findViewById(R.id.add2);
        ad3=(Button)findViewById(R.id.add3);
        ad4=(Button)findViewById(R.id.add4);
        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image3);
        img4=(ImageView)findViewById(R.id.image4);
//refrsh=(Button)findViewById(R.id.refresh);
//refrsh.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        if(id==3)
//        {
//            ViewAllDeals();
//        }
//        else if(id==4)
//        {
//            FirstViewFlyers("0");
//        }
//        else if(id==5)
//        {
//            FirstViewFlyers("2");
//        }
//
//    }
//});
ad1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(id==3)
        {
            Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
            next.putExtra("flag", 0);
            startActivity(next);

        }


    }
});
        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                    next.putExtra("flag", 0);
                    startActivity(next);

                }
                else if(id==4)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","0");
                    startActivity(next);
                }
                else if(id==5)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","1");
                    startActivity(next);
                }
            }
        });
        ad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                    next.putExtra("flag", 0);
                    startActivity(next);

                }
                else if(id==4)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","0");
                    startActivity(next);
                }
                else if(id==5)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","1");
                    startActivity(next);
                }
            }
        });
        ad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                    next.putExtra("flag", 0);
                    startActivity(next);

                }
                else if(id==4)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","0");
                    startActivity(next);
                }
                else if(id==5)
                {
                    Intent next = new Intent(FlyersandDeals.this,AddFlyersImage.class);
                    next.putExtra("flag", 0);
                    next.putExtra("type","1");
                    startActivity(next);
                }
            }
        });
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    if(deals_id.get(0)=="0")
                    {
                       Toast.makeText(FlyersandDeals.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                        next.putExtra("flag", 1);
                        next.putExtra("dealid",deals_id.get(0));
                        startActivity(next);
                    }

                }


            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    if(deals_id.get(1)=="0")
                    {
                        Toast.makeText(FlyersandDeals.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                        next.putExtra("flag", 1);
                        next.putExtra("dealid",deals_id.get(1));
                        startActivity(next);
                    }
                }




            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    if(deals_id.get(2)=="2")
                    {
                        Toast.makeText(FlyersandDeals.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3)
                {
                    if(deals_id.get(3)=="3")
                    {
                        Toast.makeText(FlyersandDeals.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent next = new Intent(FlyersandDeals.this,AddDealsImage.class);
                        next.putExtra("flag", 1);
                        next.putExtra("dealid",deals_id.get(3));
                        startActivity(next);
                    }
                }


            }
        });

        if(id==3)
        {
            ViewAllDeals();
           // txt.setText("SELECT DEALS OF THE DAY");

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.e("MAin","Item selected ="+item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(FlyersandDeals.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account3 = new Intent(FlyersandDeals.this,MyAccount.class);
                startActivity(account3);
                return true;
            case R.id.refresh:
                if(id==3)
                {
                    ViewAllDeals();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        id= Integer.valueOf(categoryselected);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri_flyers = result.getUri();
                if(flagpic1==1)
                {
                    selectedPathfirstFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    firstFlyerImage=new File(selectedPathfirstFlyerImage);
                    img1.setImageURI(resultUri_flyers);

                }
                else if(flagpic2==1)
                {
                    selectedPathsecondFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    secondFlyerImage=new File(selectedPathsecondFlyerImage);
                    img2.setImageURI(resultUri_flyers);
                }
                else if(flagpic3==1)
                {
                    selectedPaththirdFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    thirdFlyerImage=new File(selectedPaththirdFlyerImage);
                    img3.setImageURI(resultUri_flyers);
                }
                else if(flagpic4==1)
                {
                    selectedPathFourthFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    fourthFlyerImage=new File(selectedPathFourthFlyerImage);
                    img4.setImageURI(resultUri_flyers);
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle val = getIntent().getExtras();
        categoryselected=val.getString("CATEGORYID");

        Log.e("iddd","the is selecetd start is "+categoryselected);
    }



    private void ViewAllDeals()
    {
        dialog = new ACProgressFlower.Builder(FlyersandDeals.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();


        String url = "http://dailyestoreapp.com/dailyestore/api/";
        final String url1 = "http://dailyestoreapp.com/dailyestore/";
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
        Call<ListCategoryResponse> call = mainInterface.viewalldeal();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                Log.e("firstpop","the succes value is "+listCategoryResponseobject.getResponsedata().getSuccess());
                int data_length = response.body().getResponsedata().getData().size();
                try {

                    if(success==1) {

                        for (int i = 0; i < data_length; i++)

                        {
                            selectedDealid=response.body().getResponsedata().getData().get(i).getFlyId();
                            Log.e("Firstpopup.this","selected flyerid ="+selectedDealid);
                            String imageurl = response.body().getResponsedata().getData().get(i).getImage();
                            String did = response.body().getResponsedata().getData().get(i).getDealId();
                            String imageurl_total=url1+imageurl;
                            Log.e("firstpop","the succes value is "+imageurl_total);
                            deals_id.add(i,did);
                            if(i==0)
                            {

                                Glide.with(getApplicationContext()).load(imageurl_total)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(img1);
                                ad1.setVisibility(View.GONE);
                            }
                            else if(i==1)
                            {

                                Glide.with(getApplicationContext()).load(imageurl_total)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(img2);
                                ad2.setVisibility(View.GONE);
                            }
                            else if(i==2)
                            {
                                Glide.with(getApplicationContext()).load(imageurl_total)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(img3);
                                ad3.setVisibility(View.GONE);
                            }
                            else if(i==3)
                            {
                                Glide.with(getApplicationContext()).load(imageurl_total)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(img4);
                                ad4.setVisibility(View.GONE);
                            }

                        }
//                 if(deals_id.get(1)=="0")
//                 {
//                     ad1.setVisibility(View.VISIBLE);
//                     ed1.setVisibility(View.GONE);
//                 }
//                 else
//                 {
//                     ed1.setVisibility(View.VISIBLE);
//                     ad1.setVisibility(View.GONE);
//                 }
//                        if(deals_id.get(1)=="1")
//                        {
//                            ad1.setVisibility(View.VISIBLE);
//                            ed1.setVisibility(View.GONE);
//                        }
//                        else
//                        {
//                            ed1.setVisibility(View.VISIBLE);
//                            ad1.setVisibility(View.GONE);
//                        }
//                        if(deals_id.get(1)=="2")
//                        {
//                            ad1.setVisibility(View.VISIBLE);
//                            ed1.setVisibility(View.GONE);
//                        }
//                        else
//                        {
//                            ed1.setVisibility(View.VISIBLE);
//                            ad1.setVisibility(View.GONE);
//                        }
//                        if(deals_id.get(1)=="3")
//                        {
//                            ad1.setVisibility(View.VISIBLE);
//                            ed1.setVisibility(View.GONE);
//                        }
//                        else
//                        {
//                            ed1.setVisibility(View.VISIBLE);
//                            ad1.setVisibility(View.GONE);
//                        }

                    }
                    else {

                        Toast.makeText(FlyersandDeals.this,"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(FlyersandDeals.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

                Toast.makeText(FlyersandDeals.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

dialog.dismiss();
    }

}
