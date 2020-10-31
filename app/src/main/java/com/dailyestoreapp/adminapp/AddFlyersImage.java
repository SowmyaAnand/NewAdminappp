package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class AddFlyersImage extends AppCompatActivity {
Button addimage_flyer,saveimage_flyer;
ImageView Flyerimg;
String flyer_cat;
String selecetd_flyer_id;
int flag_flyers=0;
ACProgressFlower dialog;
    File flyersImage;
    String selectedPathflyers="";
    Bundle val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flyers_image);
        addimage_flyer=(Button)findViewById(R.id.edit1_flyer);
        saveimage_flyer=(Button)findViewById(R.id.edit_flyer_save);
        Flyerimg =(ImageView)findViewById(R.id.first_popup_img_flyer);
         val = getIntent().getExtras();
        flyer_cat=val.getString("type");
        flag_flyers=val.getInt("flag_flyers");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        Date d=new Date();
        final String from_to_date=simpleDateFormat.format(d);

        saveimage_flyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_flyers==0)
                {
                    uploadFlyer(flyersImage,from_to_date,flyer_cat);
                }
                else
                {
                    selecetd_flyer_id=val.getString("flyerid");
                    EditFirstPopup(flyersImage,from_to_date,flyer_cat);
                }
            }
        });

        addimage_flyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flyer_cat=="1")
                {
                    CropImage.activity()
                            .start(AddFlyersImage.this);
                }
                else
                { CropImage.activity()
                        .start(AddFlyersImage.this);

                }

            }
        });
    }

    public void uploadFlyer(final File file, final String fromdate,final String cat){
        dialog = new ACProgressFlower.Builder(AddFlyersImage.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addFlyers")
                .addMultipartFile("image",file)
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("type", cat)
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
                                Toast.makeText(AddFlyersImage.this,"Pop Up Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(AddFlyersImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                        }
dialog.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AddFlyersImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                  dialog.dismiss();
                    }
                });

    }
    public void EditFirstPopup(final File file,final String fromdate,final String cat){
        dialog = new ACProgressFlower.Builder(AddFlyersImage.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        Log.e("firstpopup","selected flyerid"+selecetd_flyer_id+file);
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/editFlyers")
                .addMultipartFile("image",file)
                .addMultipartParameter("flyId", selecetd_flyer_id)
                .addMultipartParameter("description", "popup")
                .addMultipartParameter("type", cat)
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
                                Toast.makeText(AddFlyersImage.this,"Pop Up Added Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(AddFlyersImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("addcategory",e.getMessage());
                        }
dialog.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AddFlyersImage.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
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
                selectedPathflyers = FileUtils.getPath(getApplicationContext(), resultUri_firstpopup);
                flyersImage=new File(selectedPathflyers);
                Flyerimg.setImageURI(resultUri_firstpopup);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
