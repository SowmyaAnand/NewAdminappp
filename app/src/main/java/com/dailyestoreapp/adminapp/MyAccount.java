package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAccount extends AppCompatActivity {
Button logout;
    TextView edit_account ;
    private String tag = "myaccount";
    EditText name_account,mob_account,email_account,place_account,address_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        name_account = (EditText) findViewById(R.id.Name_account);
        mob_account = (EditText) findViewById(R.id.Mobilenumber_account);
        email_account = (EditText)findViewById(R.id.emailid_account);
        logout =(Button)findViewById(R.id.logout_admin);

        String userid="1";
        userdetailsB("1");
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(logout.getText().equals("EDIT"))
        {
                logout.setText("SAVE");
                name_account.setEnabled(true);name_account.setFocusable(true);
                mob_account.setEnabled(true);mob_account.setFocusable(true);
                email_account.setEnabled(true);email_account.setFocusable(true);



        }
        else
        {
            Integer check = validation();
            if(check==1) {
               String firstname,lastname,email,phone,address,pincode,dob;

               firstname = name_account.getText().toString();
               lastname = "";
               email =email_account.getText().toString();
               phone = mob_account.getText().toString();
               address="abc";
               pincode ="585225";
               dob="0000-00-00";
               Log.e("admin","phno ="+phone);
                userdetailsEdit("1",firstname,lastname,email,phone,address,pincode,dob);



            }
        }

    }
});


    }

    private Integer validation() {
        if((name_account.getText().length()==0)||(mob_account.getText().length()==0)||(email_account.getText().length()==0))
        {
            Toast.makeText(MyAccount.this,"Please fill all details",Toast.LENGTH_LONG).show();
            return 0;
        }
        else if (mob_account.getText().length()!=10)
        {
            Toast.makeText(MyAccount.this,"Invalid Mobile Number",Toast.LENGTH_LONG).show();
            return 0;
        }
        return 1;
    }
    private void userdetailsB(String subId)
    {



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
        Call<ListCategoryResponse> call = mainInterface.Myaccount(1);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                Log.e(tag,"error"+success);

                try {
//

                    if(success==1)
                    {
                        int data_length = response.body().getResponsedata().getData().size();
                       String name_accountVal =  response.body().getResponsedata().getData().get(0).getFirstName();
                        String mob_accountVal =  response.body().getResponsedata().getData().get(0).getPhone();
                        String email_accountVal =  response.body().getResponsedata().getData().get(0).getEmail();
                        String address_accountVal =  response.body().getResponsedata().getData().get(0).getAddress();
                        name_account.setText(name_accountVal);
                        mob_account.setText(mob_accountVal);
                        email_account.setText(email_accountVal);
                        logout =(Button)findViewById(R.id.logout_admin);
                        logout.setText("EDIT");

                    }
                    else {
                        Toast.makeText(MyAccount.this,"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    
                    Toast.makeText(MyAccount.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                    Log.e(tag,"error"+t.getMessage());
                Toast.makeText(MyAccount.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void userdetailsEdit(final String Id, String firstname, final String lastname, final String email,final String phone,final String address,final String pincode,final String dob)
    {



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
        Call<LoginResponse> call = mainInterface.UpdateMyaccount(1,firstname,lastname,email,phone,address,pincode,dob);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                LoginResponse listCategoryResponseobject = response.body();
                String success = response.body().getResponsedata().getSuccess();
                Log.e(tag,"error"+success);

                try {
//

                    if(success.equals("1"))
                    {
                        Toast.makeText(MyAccount.this,"Profile Updated",Toast.LENGTH_LONG).show();
//

                    }
                    else {
                        Toast.makeText(MyAccount.this,"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(MyAccount.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(tag,"error"+t.getMessage());
                Toast.makeText(MyAccount.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}
