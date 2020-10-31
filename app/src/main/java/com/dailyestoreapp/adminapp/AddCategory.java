package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONObject;

import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class AddCategory extends AppCompatActivity {
  Button addattachcategory,addcategory,savesubcategory,sbaddattachcategory;
  ImageView imgaeitemcategory,sbimageitemcategory;
  TextView addsubcategoryhd;
  EditText newcategoryname,sbcategoryname;
  String NewCategoryname;
  String sbnewname;
  String sbimage;
  int flag=0;
  Integer newaddedcat_no;
  File subImage;
  File catImage;
  int categoryuploadflag=0;
  int subcategoryuploadflag=0;
  String selectedPathsub="";
  String selectedPathMain="";
  ACProgressFlower dialog;
  Integer sucess_value_catgeory;
  Integer sucess_value_sub_catgeory;
  ArrayList<String> categoriesEditCategies = new ArrayList<>();
  ArrayList<String> categoriesEditCategies_image = new ArrayList<>();
  public static final String MY_PREFS_NAME = "AdminApp";
  ArrayList<String> subcategoryarray = new ArrayList<String>();
  CardView card1,card2,card3,card4,card5,card6,card7,card8;
  //Bitmap to get image from gallery
  private Bitmap bitmap;

  //Uri to store the image uri
  private Uri filePath;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_category);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle("ADD CATERGORY");

    toolbar.setTitleTextColor(Color.WHITE);
    setSupportActionBar(toolbar);
    newcategoryname=(EditText)findViewById(R.id.category_title);
    addsubcategoryhd=(TextView)findViewById(R.id.subcategoryheading);
    addattachcategory=(Button)findViewById(R.id.addcategoryimage);
    sbaddattachcategory=(Button)findViewById(R.id.subaddcategoryimage);
    addcategory=(Button)findViewById(R.id.addcategory);
    sbcategoryname=(EditText)findViewById(R.id.subcatvalue1);
    savesubcategory=(Button)findViewById(R.id.savesubcategory);
    imgaeitemcategory=(ImageView)findViewById(R.id.categoryImg);
    sbimageitemcategory=(ImageView)findViewById(R.id.subcategoryImg);
    card1=(CardView)findViewById(R.id.headersub1);
    card2=(CardView)findViewById(R.id.headersub2);
    card3=(CardView)findViewById(R.id.headersub3);
    card4=(CardView)findViewById(R.id.headersub4);
    card5=(CardView)findViewById(R.id.headersub5);
    card6=(CardView)findViewById(R.id.headersub6);
    card7=(CardView)findViewById(R.id.headersub7);
    card8=(CardView)findViewById(R.id.headersub8);
    SharedPreferences shared = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    String savedcatString = shared.getString("categories", "");
    String[] cats = savedcatString.split(",");//if spaces are uneven, use \\s+ instead of " "

    savesubcategory.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sbnewname=sbcategoryname.getText().toString();
        if((selectedPathsub==null)||(selectedPathsub.length()==0))
        {
          Toast.makeText(AddCategory.this,"Please select an an  image",Toast.LENGTH_SHORT).show();
        }
        else if ((sbnewname==null)||(sbnewname.length()==0))
        {
          Toast.makeText(AddCategory.this,"Please enter sub category name",Toast.LENGTH_SHORT).show();
        }
        else
        {
          uploadSubCategory(subImage,sbnewname,"1");
        }

      }
    });

    addcategory.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        flag=1;
        NewCategoryname=newcategoryname.getText().toString();
        if((selectedPathMain == null)||(selectedPathMain.length()==0))
        {
          Toast.makeText(AddCategory.this,"Please select an image",Toast.LENGTH_LONG).show();
        }
        else if ((NewCategoryname == null)||(NewCategoryname.length()==0))
        {
          Toast.makeText(AddCategory.this,"Please enter category name",Toast.LENGTH_SHORT).show();
        }
        else
        {

          String enteredcategoryname =newcategoryname.getText().toString();
          uploadCategory(catImage,enteredcategoryname,"1");


        }




      }
    });
    addattachcategory.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CropImage.activity()
                .start(AddCategory.this);
      }
    });
    sbaddattachcategory.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CropImage.activity()
                .start(AddCategory.this);
      }
    });
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu3, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    Log.e("MAin","Item selected ="+item.getItemId());
    switch (item.getItemId()) {
      case R.id.logout:
        Intent cart = new Intent(AddCategory.this,Login.class);
        startActivity(cart);
        return true;
      case R.id.account:
        Intent account = new Intent(AddCategory.this,MyAccount.class);
        startActivity(account);
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
        Uri resultUri = result.getUri();
        filePath = data.getData();
        //selectedPath = FileUtils.getPath(getApplicationContext(), selectedFileUri);
        Log.e("selected img","selected img"+resultUri);

        if(flag==1)
        {
          selectedPathsub = FileUtils.getPath(getApplicationContext(), resultUri);
          subImage=new File(selectedPathsub);
          sbimageitemcategory.setImageURI(resultUri);

        }
        else
        {
          selectedPathMain = FileUtils.getPath(getApplicationContext(), resultUri);
          catImage=new File(selectedPathMain);
          imgaeitemcategory.setImageURI(resultUri);
        }


      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Exception error = result.getError();
      }
    }
  }


  public void uploadSubCategory(final File file, final String newsubcategoryname, final String createdby){
    dialog = new ACProgressFlower.Builder(AddCategory.this)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .borderPadding(1)
            .fadeColor(Color.WHITE).build();
    dialog.show();
    subcategoryuploadflag=0;
    AndroidNetworking.enableLogging();
    AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addSubCategory")
            .addMultipartFile("subItemImage",file)
            .addMultipartParameter("subName", newsubcategoryname)
            .addMultipartParameter("createdBy", "1")
            .addMultipartParameter("typeId", String.valueOf(newaddedcat_no))
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
                  String success_value_sub = String.valueOf(cat_no.get("success"));
                  Integer sub  = Integer.valueOf(success_value_sub);
                  Log.e("addcat","the cat no is "+sub);
                  if(sub==1)
                  {
                    Toast.makeText(AddCategory.this," Sub Category Added Successfully.You can now add new sub category",Toast.LENGTH_SHORT).show();
                    sbcategoryname.setText("");
                    sbimageitemcategory.setImageResource(R.drawable.ic_image_black_24dp);
                  }
                  else
                  {

                  }


                } catch (JSONException e) {
                  e.printStackTrace();
                  Log.e("addcategory",e.getMessage());
                }



              }

              @Override
              public void onError(ANError anError) {

                Toast.makeText(AddCategory.this,"Error"+anError.getMessage(),Toast.LENGTH_SHORT).show();

              }

            });
    dialog.dismiss();
  }
  public void uploadCategory(final File file, final String newcatgoryname, final String createdby){
    dialog = new ACProgressFlower.Builder(AddCategory.this)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .borderPadding(1)
            .fadeColor(Color.WHITE).build();
    dialog.show();
    AndroidNetworking.enableLogging();
    AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addcategory")
            .addMultipartFile("categoryImage",file)
            .addMultipartParameter("categoryName", newcatgoryname)
            .addMultipartParameter("createdBy", "1")
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
                  String data = String.valueOf(cat_no.get("data"));
                  newaddedcat_no = Integer.valueOf(data);
                  String success_value = String.valueOf(cat_no.get("success"));
                  Integer s  = Integer.valueOf(success_value);
                  Log.e("addcat","the cat no is "+s);
                  if(s==1)
                  {
                    categoriesEditCategies.add(newcatgoryname);

                    Toast.makeText(AddCategory.this,"Category Added Successfully",Toast.LENGTH_SHORT).show();
                    card1.setVisibility(View.VISIBLE);
                    card2.setVisibility(View.GONE);
                    card3.setVisibility(View.GONE);
                    card4.setVisibility(View.GONE);
                    card5.setVisibility(View.GONE);
                    card6.setVisibility(View.GONE);
                    card7.setVisibility(View.GONE);
                    card8.setVisibility(View.GONE);
                    savesubcategory.setVisibility(View.VISIBLE);
                    addsubcategoryhd.setVisibility(View.VISIBLE);
                    newcategoryname.setEnabled(false);
                    addattachcategory.setVisibility(View.INVISIBLE);
                  }
                  else
                  {
                    Toast.makeText(AddCategory.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                  Log.e("addcategory",e.getMessage());
                }

dialog.dismiss();

              }

              @Override
              public void onError(ANError anError) {
                categoryuploadflag=0;
                Toast.makeText(AddCategory.this,"Something went wrong.Please try again",Toast.LENGTH_SHORT).show();
              }
            });

  }
}