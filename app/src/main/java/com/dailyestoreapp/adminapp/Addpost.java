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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addpost extends AppCompatActivity {
    TextView addattach;
    ImageView imgaeitem;
    File additemImageFile;
    String selectedPathadditem="";
    Button post;
    Integer item_Id;
    String item_Name;
Button addoffer;
    Integer SelectedCategoryNumber=0;
    Integer SelectedSubCategoryNumber=0;
    Spinner Category_spinner;
EditText post_itemname,post_amount,post_offer,post_description,post_quantity,qty_val__detail;
    Spinner Sub_Category_spinner;
    public static final String MY_PREFS_NAME = "AdminApp";
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    ArrayList<Integer> categoriescatno_edit = new ArrayList<>();
    ArrayList<Integer> matchingcategoriescatno_edit = new ArrayList<>();
    ArrayList<Integer> subcategoriescatno_edit = new ArrayList<>();
    ArrayAdapter<String> subadapter1;
    ACProgressFlower dialog;
    Integer selected_cat_no;
    List<String> subcategorylist1;
    String cod_eligibility="COD ELIGIBLE";
    String refund_eligibility="REPLACEMENT ELIGIBLE";
    RadioButton rb1;
    RadioButton rb12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD ITEM");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        final ArrayList<String> categoriesEditCategies = new ArrayList<>();
        Category_spinner = findViewById(R.id.categoryspinner);
        addoffer = findViewById(R.id.btnoffer);
        Sub_Category_spinner = findViewById(R.id.subcategoryspinner);
        addattach = (TextView) findViewById(R.id.txtAttachment);
        imgaeitem = (ImageView) findViewById(R.id.imageitem);
        post_itemname=(EditText)findViewById(R.id.itemname_post);
        post_amount=(EditText)findViewById(R.id.amount_post);
        qty_val__detail=findViewById(R.id.quantity_post_detail);
       post_description=(EditText)findViewById(R.id.description_post);
       post_quantity=(EditText)findViewById(R.id.quantity_post);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.cod);
        addoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Addpost.this,OffersActivity.class);
                next.putExtra("id",item_Id);
                next.putExtra("name",item_Name);
                startActivity(next);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(checkedId);
                cod_eligibility = String.valueOf(rb1.getText());
            }
        });
        RadioGroup radiogroup1 = (RadioGroup) findViewById(R.id.refund) ;
        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) findViewById(checkedId);
                refund_eligibility = String.valueOf(rb2.getText());

            }
        });

        post = (Button) findViewById(R.id.btnPost);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String it_name_post,amount_post,description_post,offer_post,quantity_post,quantity_post_detail,quantity_post_val,cashondelivery,replace;
                it_name_post=post_itemname.getText().toString();
                amount_post =post_amount.getText().toString();
                description_post=post_description.getText().toString();
                quantity_post_val=post_quantity.getText().toString();
                quantity_post_detail=qty_val__detail.getText().toString();
                quantity_post =quantity_post_val+" "+quantity_post_detail;

                Integer sb = Sub_Category_spinner.getSelectedItemPosition();
                Log.e("addpost","post sub id "+subcategoriescatno_edit.get(sb));
                Integer ct = Category_spinner.getSelectedItemPosition();
                Log.e("addpost","cat id "+categoriescatno_edit.get(ct));
                if(cod_eligibility.equals("COD ELIGIBLE"))
                {
                    cashondelivery="1";
                }
                else
                {
                    cashondelivery="0";
                }
                if(refund_eligibility.equals("REPLACEMENT ELIGIBLE"))
                {
                    replace="1";
                }
                else
                {
                    replace="0";
                }

                if((selectedPathadditem==null)||(selectedPathadditem.length()==0))
                {
                    Toast.makeText(Addpost.this,"Please select an image",Toast.LENGTH_SHORT).show();
                }
                 else if ((it_name_post == null)||(it_name_post.length()==0))
                {
                    Toast.makeText(Addpost.this,"Please enter item name",Toast.LENGTH_SHORT).show();
                }
                else if ((amount_post == null)||(amount_post.length()==0))
                {
                    Toast.makeText(Addpost.this,"Please enter amount",Toast.LENGTH_SHORT).show();
                }
                else if ((description_post == null)||(description_post.length()==0))
                {
                    Toast.makeText(Addpost.this,"Please  enter description",Toast.LENGTH_SHORT).show();
                }
                else if ((quantity_post == null)||(quantity_post.length()==0))
                {
                    Toast.makeText(Addpost.this,"Please enter quantity",Toast.LENGTH_SHORT).show();
                }
                 else if((categoriescatno_edit.get(ct)==0)||(subcategoriescatno_edit.get(sb)==0))
                {
                    Toast.makeText(Addpost.this,"Please select both category and sub category",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    if(categoriescatno_edit.get(ct)==matchingcategoriescatno_edit.get(sb))
                    {

                        uploadItem(additemImageFile,categoriescatno_edit.get(ct).toString(),subcategoriescatno_edit.get(sb).toString(),it_name_post,description_post,quantity_post,amount_post,"0","1",cashondelivery,replace);
                    }
                    else
                    {
                        Toast.makeText(Addpost.this,"Selected Sub Category does not belong to the selected category ",Toast.LENGTH_SHORT).show();

                    }
                }


                }

            }
        );
        // Cat_name
        SharedPreferences shared = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        Set<String> set = shared.getStringSet("categories", null);
//        List<String> categorylist = new ArrayList<String>();
//        categorylist.add("Select Category");
//        categorylist.addAll(set);
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
categoriescatno_edit.add(0);
matchingcategoriescatno_edit.add(0);
subcategoriescatno_edit.add(0);
        for (String number : numbers) {
            categoriescatno_edit.add(Integer.valueOf(number));
        }

//// sub_Cat_number
//        SharedPreferences shared3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String savedString3 = shared3.getString("sub_categories_no", "");
//
//
//        String[] numbers_sub = savedString3.split(",");//if spaces are uneven, use \\s+ instead of " "
//
//        for (String number : numbers_sub) {
//            if()
//            subcategoriescatno_edit.add(Integer.valueOf(number));
//        }
////// sub_Cat_ name
        subcategorylist1 = new ArrayList<String>();
//        SharedPreferences shared4 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        Set<String> set4 = shared4.getStringSet("categories", null);
//
        subcategorylist1.add("Select Sub Category");
//        subcategorylist1.addAll(set4);



        subadapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subcategorylist1);

        subadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub_Category_spinner.setAdapter(subadapter1);
        Sub_Category_spinner.setOnItemSelectedListener(new SubCategoriesSpinnerClass());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorylist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category_spinner.setAdapter(adapter);
        Category_spinner.setOnItemSelectedListener(new CategoriesSpinnerClass());
        addattach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(Addpost.this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("MAin", "Item selected =" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(Addpost.this, Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account1 = new Intent(Addpost.this, MyAccount.class);
                startActivity(account1);
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
                imgaeitem.setImageURI(resultUri);
                Uri selectedFileUri = data.getData();
                Log.e("the"," file is "+selectedFileUri);
                selectedPathadditem = FileUtils.getPath(getApplicationContext(),resultUri);

                additemImageFile=new File(selectedPathadditem);
                Log.e("the"," file is "+additemImageFile);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    class CategoriesSpinnerClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_cat_no = categoriescatno_edit.get(position);
                Log.e("addpost","after selecting the category"+selected_cat_no+position);
            Log.e("addpost","after selecting the category"+categoriescatno_edit);
                subcategoryactivatepost(selected_cat_no);

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
                            if (!subcategorylist1.contains(sub_name)) {
                                subcategorylist1.add(sub_name);
                                subcategoriescatno_edit.add(item_no);
                                matchingcategoriescatno_edit.add(cat_item_no);

                            }

                        }
                    if(subcategorylist1.size()==0)
{


}
                        subadapter1.notifyDataSetChanged();


                        //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    Toast.makeText(Addpost.this,"No Sub Categories Found Under This Category",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }

    public void uploadItem(final File file, final String typeid, final String subId, final String itemName,final String description,final String quantity,final String price,final String status,final String createdBy,final String cod,final String refund) {
        Log.e("test", "path=" + file);

        //        String refreshToken = HelperClass.getRefreshToken(PostActivity.this);
//        String userADID = HelperClass.getUserADID(PostActivity.this);
//
//        final ProgressDialog progressDialog=new ProgressDialog(PostActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Please Wait");
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
                dialog = new ACProgressFlower.Builder(Addpost.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addItems")
                .addMultipartFile("image", file)
                .addMultipartParameter("typeId", String.valueOf(typeid))
                .addMultipartParameter("subId", subId)
                .addMultipartParameter("itemName", itemName)
                .addMultipartParameter("description", description)
                .addMultipartParameter("quantity", quantity)
                .addMultipartParameter("price", price)
                .addMultipartParameter("status", "1")
                .addMultipartParameter("createdBy", "1")
                .addMultipartParameter("CashOnDelivery", cod)
                .addMultipartParameter("Refund", refund)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH).doNotCacheResponse()
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                        // do anything with progress

                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject cat_no_post = (JSONObject) jsonObj.get("responsedata");
                            String data_post = String.valueOf(cat_no_post.get("data"));
                            Integer newaddedcat_no_post = Integer.valueOf(data_post);
                            String success_value_post = String.valueOf(cat_no_post.get("success"));
                            Integer s  = Integer.valueOf(success_value_post);
                            Log.e("addcat","the cat no is "+s);
                            if(s==1)
                            {
                                Toast.makeText(Addpost.this,"Item Added Successfully",Toast.LENGTH_SHORT).show();
                                item_Name=post_itemname.getText().toString();
                                item_Id=newaddedcat_no_post;
                                addoffer.setVisibility(View.VISIBLE);

                                post_itemname.setText("");
                               post_amount.setText("");
                                post_description.setText("");
                               post_offer.setText("");
                              post_quantity.setText("");

                            }
                        }
                        catch (Exception e)
                        {
Log.e("addpost",e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("addpost",anError.getMessage());
                    }
                });
dialog.dismiss();

    }
}