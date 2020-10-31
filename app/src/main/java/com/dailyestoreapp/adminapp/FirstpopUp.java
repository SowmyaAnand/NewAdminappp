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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstpopUp extends AppCompatActivity {
ImageView firstpopUp_img;
    Button edit_firstpopUp_change_img ;
ACProgressFlower dialog;
Button firstpopUp_change_img ;
Button firstpopUp_save_img;
    Button edit_firstpopUp_save_img;
String selectedflyerid="0";
    File firstpopUpImage;
    String selectedPathfirstpopUp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpop_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD FIRST POP UP");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        FirstPopup();
        firstpopUp_img =(ImageView)findViewById(R.id.first_popup_img);
        firstpopUp_change_img = (Button)findViewById(R.id.addImageFirstPopup);
        edit_firstpopUp_change_img = (Button)findViewById(R.id.editImageFirstPopup);
        firstpopUp_save_img = (Button)findViewById(R.id.saveFirstPopup);
        edit_firstpopUp_save_img = (Button)findViewById(R.id.edit_saveFirstPopup);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        Date d=new Date();
        final String from_to_date=simpleDateFormat.format(d);

        firstpopUp_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(FirstpopUp.this);
            }
        });


        firstpopUp_save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((selectedPathfirstpopUp==null)||(selectedPathfirstpopUp.length()==0))
                {
                    Toast.makeText(FirstpopUp.this,"Please select an image",Toast.LENGTH_SHORT).show();
                }
                else
                {
            uploadFirstPopup(firstpopUpImage,from_to_date);
                }
            }
        });



        edit_firstpopUp_save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((selectedPathfirstpopUp==null)||(selectedPathfirstpopUp.length()==0))
                {
                    Toast.makeText(FirstpopUp.this,"Please select an image",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    EditFirstPopup(firstpopUpImage,from_to_date);
                }

            }
        });


        edit_firstpopUp_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(FirstpopUp.this);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri_firstpopup = result.getUri();
                selectedPathfirstpopUp = FileUtils.getPath(getApplicationContext(), resultUri_firstpopup);
                firstpopUpImage=new File(selectedPathfirstpopUp);
                firstpopUp_img.setImageURI(resultUri_firstpopup);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("MAin", "Item selected =" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(FirstpopUp.this, Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account1 = new Intent(FirstpopUp.this, MyAccount.class);
                startActivity(account1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void uploadFirstPopup(final File file,final String fromdate){

        dialog = new ACProgressFlower.Builder(FirstpopUp.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addFlyers")
                .addMultipartFile("image",file)
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("type", "1")
                .addMultipartParameter("status", "1")
                .addMultipartParameter("fromDate", fromdate)
                .addMultipartParameter("toDate", fromdate)
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

                                Toast.makeText(FirstpopUp.this,"Pop Up Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(FirstpopUp.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                            dialog.dismiss();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.dismiss();
                        Toast.makeText(FirstpopUp.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void EditFirstPopup(final File file,final String fromdate){
        dialog = new ACProgressFlower.Builder(FirstpopUp.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
Log.e("firstpopup","selected flyerid"+selectedflyerid+file);
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/editFlyers")
                .addMultipartFile("image",file)
                .addMultipartParameter("flyId", selectedflyerid)
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("type", "1")
                .addMultipartParameter("status", "1")
                .addMultipartParameter("fromDate", fromdate)
                .addMultipartParameter("toDate", fromdate)
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

                                Toast.makeText(FirstpopUp.this,"Pop Up Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(FirstpopUp.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                            dialog.dismiss();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(FirstpopUp.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void FirstPopup()
    {
        dialog = new ACProgressFlower.Builder(FirstpopUp.this)
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
        Call<ListCategoryResponse> call = mainInterface.firstpop();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
Log.e("firstpop","the succes value is "+listCategoryResponseobject.getResponsedata().getSuccess());
                int data_length = response.body().getResponsedata().getData().size();
                if(data_length>0)
                {
                    edit_firstpopUp_change_img.setVisibility(View.VISIBLE);
                    firstpopUp_change_img.setVisibility(View.GONE);
                    edit_firstpopUp_save_img.setVisibility(View.VISIBLE);
                    firstpopUp_save_img.setVisibility(View.GONE);
                }
                else
                {
                    edit_firstpopUp_change_img.setVisibility(View.GONE);
                    firstpopUp_change_img.setVisibility(View.VISIBLE);
                    edit_firstpopUp_save_img.setVisibility(View.GONE);
                    firstpopUp_save_img.setVisibility(View.VISIBLE);
                }
                try {


                    if(success==1) {

                        for (int i = 0; i < data_length; i++)

                        {
                            selectedflyerid=response.body().getResponsedata().getData().get(i).getFlyId();
                            Log.e("Firstpopup.this","selected flyerid ="+selectedflyerid);
                            String imageurl = response.body().getResponsedata().getData().get(i).getImage();
                            String imageurl_total=url1+imageurl;
                            Log.e("firstpop","the succes value is "+imageurl_total);
                            Glide.with(getApplicationContext()).load(imageurl_total)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into( firstpopUp_img);
                        }


                    }
                    else {

                        Toast.makeText(FirstpopUp.this,"No Data found",Toast.LENGTH_SHORT).show();
                    }
dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(FirstpopUp.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(FirstpopUp.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
