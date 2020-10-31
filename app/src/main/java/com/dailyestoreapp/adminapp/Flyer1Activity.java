package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.theartofdev.edmodo.cropper.CropImage;

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

public class Flyer1Activity extends AppCompatActivity {
    ArrayList<String> first_flyers_id = new ArrayList<>();
    File firstFlyerImage,secondFlyerImage,thirdFlyerImage,fourthFlyerImage;
    String selectedPathfirstFlyerImage="";
    String selectedPathsecondFlyerImage="";
    String selectedPaththirdFlyerImage="";
    String selectedPathFourthFlyerImage="";
    int flagpic1,flagpic2,flagpic3,flagpic4;
    ImageView img11,img21,img31,img41;
    Button ed1,ed2,ed3,ed4;
    Button ad1,ad2,ad3,ad4;
    ACProgressFlower dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyer1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD IMAGES");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        
        first_flyers_id.add("0");
        first_flyers_id.add("0");
        first_flyers_id.add("0");
        first_flyers_id.add("0");
        FirstViewFlyers("0");
        ed1=(Button)findViewById(R.id.edit1);
        ed2=(Button)findViewById(R.id.edit2);
        ed3=(Button)findViewById(R.id.edit3);
        ed4=(Button)findViewById(R.id.edit4);
        ad1=(Button)findViewById(R.id.add1);
        ad2=(Button)findViewById(R.id.add2);
        ad3=(Button)findViewById(R.id.add3);
        ad4=(Button)findViewById(R.id.add4);
        img11=(ImageView)findViewById(R.id.image1);
        img21=(ImageView)findViewById(R.id.image2);
        img31=(ImageView)findViewById(R.id.image3);
        img41=(ImageView)findViewById(R.id.image4);
        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                next.putExtra("flag", 0);
                next.putExtra("type","0");
                startActivity(next);            }
        });
        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                next.putExtra("flag", 0);
                next.putExtra("type","0");
                startActivity(next);
            }
        });
        ad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                next.putExtra("flag", 0);
                next.putExtra("type","0");
                startActivity(next);
            }
        });
        ad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                next.putExtra("flag", 0);
                next.putExtra("type","0");
                startActivity(next);
            }
        });
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_flyers_id.get(0)=="0")
                {
                    Toast.makeText(Flyer1Activity.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                    next.putExtra("flag_flyers", 1);
                    next.putExtra("flyerid",first_flyers_id.get(0));
                    next.putExtra("type","0");
                    startActivity(next);
                }
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_flyers_id.get(1)=="0")
                {
                    Toast.makeText(Flyer1Activity.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                    next.putExtra("flag_flyers", 1);
                    next.putExtra("flyerid",first_flyers_id.get(1));
                    next.putExtra("type","0");
                    startActivity(next);
                }
            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_flyers_id.get(2)=="0")
                {
                    Toast.makeText(Flyer1Activity.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                    next.putExtra("flag_flyers", 1);
                    next.putExtra("flyerid",first_flyers_id.get(2));
                    next.putExtra("type","0");
                    startActivity(next);
                }
            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_flyers_id.get(3)=="0")
                {
                    Toast.makeText(Flyer1Activity.this,"PLease an image before making changes ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent next = new Intent(Flyer1Activity.this,AddFlyersImage.class);
                    next.putExtra("flag_flyers", 1);
                    next.putExtra("flyerid",first_flyers_id.get(3));
                    next.putExtra("type","0");
                    startActivity(next);
                }
            }
        });
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
                Intent cart = new Intent(Flyer1Activity.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account3 = new Intent(Flyer1Activity.this,MyAccount.class);
                startActivity(account3);
                return true;
            case R.id.refresh:
                    FirstViewFlyers("0");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri_flyers = result.getUri();
                if(flagpic1==1)
                {
                    selectedPathfirstFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    firstFlyerImage=new File(selectedPathfirstFlyerImage);
                    img11.setImageURI(resultUri_flyers);

                }
                else if(flagpic2==1)
                {
                    selectedPathsecondFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    secondFlyerImage=new File(selectedPathsecondFlyerImage);
                    img21.setImageURI(resultUri_flyers);
                }
                else if(flagpic3==1)
                {
                    selectedPaththirdFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    thirdFlyerImage=new File(selectedPaththirdFlyerImage);
                    img31.setImageURI(resultUri_flyers);
                }
                else if(flagpic4==1)
                {
                    selectedPathFourthFlyerImage = FileUtils.getPath(getApplicationContext(),resultUri_flyers);
                    fourthFlyerImage=new File(selectedPathFourthFlyerImage);
                    img41.setImageURI(resultUri_flyers);
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    private void FirstViewFlyers(final String tid)
    {
        dialog = new ACProgressFlower.Builder(Flyer1Activity.this)
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
        if(tid.equals("0"))
        {
            Call<ListCategoryResponse> call = mainInterface.allFlyers();
            call.enqueue(new Callback<ListCategoryResponse>() {
                @Override
                public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                    ListCategoryResponse listCategoryResponseobject = response.body();
                    int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                    Log.e("firstpop","the succes value is "+listCategoryResponseobject.getResponsedata().getSuccess());
                    int data_length = response.body().getResponsedata().getData().size();


                    try {


                        if(success==1) {

                            for (int i = 0; i < data_length; i++) {
                                String type = response.body().getResponsedata().getData().get(i).getType();
                                Log.e("Firstpopup.this", "selected flyerid =" + type);
                                Log.e("Firstpopup.this", "selected flyerid =" + tid);

                                    String selectedflyerid = response.body().getResponsedata().getData().get(i).getFlyId();
                                    Log.e("Firstpopup.this", "selected flyerid =" + selectedflyerid);



                                first_flyers_id.add(i,selectedflyerid);


                                    Log.e("Firstpopup.this", "selected flyerid =" + first_flyers_id);

                                    String imageurl = response.body().getResponsedata().getData().get(i).getImage();
                                    String imageurl_total = url1 + imageurl;
                                    Log.e("flyers", "the succes value is "+img11.getDrawable()+imageurl_total);
                                    if (i==0)
                                    {
                                        Glide.with(getApplicationContext()).load(imageurl_total)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(img11);
                                        ad1.setVisibility(View.GONE);

                                    }
                                    else if (i==1) {
                                        Glide.with(getApplicationContext()).load(imageurl_total)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(img21);
                                        ad2.setVisibility(View.GONE);
                                    }
                                    else if (i==2) {
                                        Glide.with(getApplicationContext()).load(imageurl_total)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(img31);
                                        ad3.setVisibility(View.GONE);
                                    }
                                    else if (i==3) {
                                        Glide.with(getApplicationContext()).load(imageurl_total)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(img41);
                                        ad4.setVisibility(View.GONE);
                                    }
                                
                            }


                        }
                        else {

                            Toast.makeText(Flyer1Activity.this,"No Data found",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Flyer1Activity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

                    Toast.makeText(Flyer1Activity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }




        dialog.dismiss();

    }
}
