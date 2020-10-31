package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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

public class Login extends AppCompatActivity

{
Button lg;


    List<String> cat_no = new ArrayList<String>();
    ACProgressFlower dialog;

    public static final String MY_PREFS_NAME = "AdminApp";
    private String tag = "Login";
   EditText username;
   EditText pswd;
   String uname;
   String password;
   String login_type="1";
   int login_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_flag=0;
        Log.e("login","the login flag value is"+login_flag);
        lg = (Button)findViewById(R.id.login);
        username = (EditText) findViewById(R.id.edit_text_user);
        pswd = (EditText)findViewById(R.id.edit_text2_pswd);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname =username.getText().toString();
                password = pswd.getText().toString();
                if( (uname==null)||(uname.length()==0)||(password==null)|(password.length()==0))
                {
                    Toast.makeText(Login.this,"Please enter valid username and Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   try
                   {
                          lg.setClickable(false);
                          lg.setEnabled(false);
                       Log.e("login","the login flag value is"+login_flag);
                      Activate();
                   }
                   catch (Exception e)
                   {
                       Toast.makeText(Login.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                   }

                }

            }
        });
    }



    void login_call(String usname, String pass)
    {
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
        Call<LoginResponse> call = mainInterface.Loginapi(uname,password,login_type);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                LoginResponse obj =response.body();
                Log.e(tag,"success="+response.body().getResponsedata());
               int success = Integer.parseInt(obj.getResponsedata().getSuccess());
                Log.e(tag,"success="+success);
                dialog.dismiss();
                if(success==1)
                {

                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent next = new Intent(Login.this,Main2Activity.class);
                    startActivity(next);
                }
              else
                {

                    Toast.makeText(Login.this,"Invalid Username and Password",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        lg.setClickable(true);
        lg.setEnabled(true);

    }
    private void Activate()
    {
        final StringBuilder strbul  = new StringBuilder();
        final StringBuilder ct  = new StringBuilder();
        final StringBuilder ct_images  = new StringBuilder();
        dialog = new ACProgressFlower.Builder(Login.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .borderPadding(1)
                .fadeColor(Color.WHITE).build();
        dialog.show();
        final ArrayList<String> categories = new ArrayList<>();
        final ArrayList<String> categories_image = new ArrayList<>();
        final ArrayList<Integer> nums = new ArrayList<>();
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
        Call<ListCategoryResponse> call = mainInterface.CategoryList();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse catObj = response.body();
                int cat_length = catObj.getResponsedata().getData().size();

//                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
//                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
//                    JSONObject jo2 = new JSONObject(obj.toString());
//                    JSONArray categoriesarray = jo2.getJSONArray("data");
//                    Log.e(tag,"categoriesarray"+categoriesarray);
//                    Set<Integer> set3 = new HashSet<Integer>();

                    for(int i=0; i<cat_length; i++)
                    {
//                        JSONObject j1= categoriesarray.getJSONObject(i);
//                        String item = j1.getString("itemName");
//                        String item_image = j1.getString("itemImage");
//                        int item_no = Integer.parseInt(j1.getString("typeId"));
                        ListCategoryResponseData catObj1 = catObj.getResponsedata().getData().get(i);
                        String item = catObj1.getCategoryName();
                        String item_image = catObj1.getCategoryImage();
                        int item_no = Integer.parseInt(catObj1.getTypeId());
                        nums.add(item_no);
                        categories.add(item);
                        categories_image.add(item_image);


                    }
                    Log.e(tag,"value added "+categories_image);
                    Log.e(tag,"value added "+categories);
                    Log.e(tag,"value added "+nums);

                    Iterator<Integer> iter = nums.iterator();
                    while(iter.hasNext())
                    {
                        strbul.append(iter.next());
                        if(iter.hasNext()){
                            strbul.append(",");
                        }
                    }
                    strbul.toString();

                    // to store categories
                    Log.e("res","res="+strbul);
                    Iterator<String> itr_string = categories.iterator();
                    while (itr_string.hasNext())
                    {

                        ct.append(itr_string.next());
                        if(itr_string.hasNext()){
                            ct.append(",");
                        }
                    }


                    Iterator<String> itr_string_image = categories_image.iterator();
                    while (itr_string_image.hasNext())
                    {

                        ct_images.append(itr_string_image.next());
                        if(itr_string_image.hasNext()){
                            ct_images.append(",");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(tag,"catch exception"+e.getMessage());
                }

                Log.e(tag,"categories = "+categories);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("categories_new", ct.toString());
                Log.e("homefragment","the catgeories shared preference are  login  ="+ct.toString());
                editor.apply();
                if(categories_image.size()>0){
                    Log.e(tag,"images array "+ct_images.toString());
                    SharedPreferences.Editor editor3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor3.putString("categories_image_new", ct_images.toString());
                    editor3.apply();
                }


                Log.e(tag,"array of numbers "+strbul.toString());
                SharedPreferences.Editor editor2 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("categories_no_new", strbul.toString());
                editor.apply();

//                SharedPreferences.Editor editor3= getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                editor.putString("categories_no", String.valueOf(nums.get(0)));
//                editor.apply();
                
                login_call(uname,password);

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                lg.setClickable(true);
                lg.setEnabled(true);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}



