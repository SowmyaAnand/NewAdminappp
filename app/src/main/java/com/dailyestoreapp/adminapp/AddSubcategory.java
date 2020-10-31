package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddSubcategory extends AppCompatActivity {
    String selectedPathsub_new="";
    Spinner Category_spinner_new;
    private Uri filePath_sub;
    Integer newaddedcat_no_new;
    String sbnewname_new;
    File subImage_new;
   EditText sbcategoryname_new;
    public static final String MY_PREFS_NAME = "AdminApp";
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    ArrayList<Integer> categoriescatno_edit_new = new ArrayList<>();
    ACProgressFlower dialog;
    Integer selected_cat_no;
  
    Button sbaddattachcategory_new,savesubcategory_new;
    ImageView sbimageitemcategory_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subcategory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD SUB CATEGORY");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        final ArrayList<String> categoriesEditCategies = new ArrayList<>();
        Category_spinner_new = findViewById(R.id.categoryspinner_new);
        sbaddattachcategory_new=(Button)findViewById(R.id.subaddcategoryimage_new);
        savesubcategory_new=(Button)findViewById(R.id.savesubcategory_new);
        sbcategoryname_new=(EditText)findViewById(R.id.subcatvalue1_new);
        sbimageitemcategory_new=(ImageView)findViewById(R.id.subcategoryImg_new);
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
        categoriescatno_edit_new.add(0);

        for (String number : numbers) {
            categoriescatno_edit_new.add(Integer.valueOf(number));
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorylist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category_spinner_new.setAdapter(adapter);
        Category_spinner_new.setOnItemSelectedListener(new AddSubcategory.CategoriesSpinnerClass());
        savesubcategory_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbnewname_new=sbcategoryname_new.getText().toString();
                if((selectedPathsub_new==null)||(selectedPathsub_new.length()==0))
                {
                    Toast.makeText(AddSubcategory.this,"Please select an image",Toast.LENGTH_SHORT).show();
                }
                else if ((sbnewname_new==null)||(sbnewname_new.length()==0))
                {
                    Toast.makeText(AddSubcategory.this,"Please enter sub category name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadSubCategory(subImage_new,sbnewname_new,"1");
                }

            }
        });
        sbaddattachcategory_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(AddSubcategory.this);
            }
        });
        
    }


    public class CategoriesSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selected_cat_no = categoriescatno_edit_new.get(position);
            Log.e("addpost", "after selecting the category" + selected_cat_no + position);
            Log.e("addpost", "after selecting the category" + categoriescatno_edit_new);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                filePath_sub = data.getData();
                //selectedPath = FileUtils.getPath(getApplicationContext(), selectedFileUri);
                Log.e("selected img","selected img"+resultUri);

                    selectedPathsub_new = FileUtils.getPath(getApplicationContext(), resultUri);
                    subImage_new=new File(selectedPathsub_new);
                    sbimageitemcategory_new.setImageURI(resultUri);

                
                


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    public void uploadSubCategory(final File file, final String newsubcategoryname, final String createdby){
        dialog = new ACProgressFlower.Builder(AddSubcategory.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .borderPadding(1)
                .fadeColor(Color.WHITE).build();
        dialog.show();
        Integer ct = Category_spinner_new.getSelectedItemPosition();
        newaddedcat_no_new =categoriescatno_edit_new.get(ct);
        Log.e("addsub","parameters="+file+newsubcategoryname+String.valueOf(newaddedcat_no_new));
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addSubCategory")
                .addMultipartFile("subItemImage",file)
                .addMultipartParameter("subName", newsubcategoryname)
                .addMultipartParameter("createdBy", "1")
                .addMultipartParameter("typeId", String.valueOf(newaddedcat_no_new))
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
                                Toast.makeText(AddSubcategory.this," Sub Category Added Successfully.You can now add new sub category",Toast.LENGTH_SHORT).show();
                                sbcategoryname_new.setText("");
                                sbimageitemcategory_new.setImageResource(R.drawable.ic_image_black_24dp);
                            }
                            else
                            {

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("AddSubcategory",e.getMessage());
                        }



                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AddSubcategory.this,"Error"+anError.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                });
        dialog.dismiss();
    }
}