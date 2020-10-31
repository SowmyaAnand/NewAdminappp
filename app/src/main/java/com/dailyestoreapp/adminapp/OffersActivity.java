package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OffersActivity extends AppCompatActivity {
    ACProgressFlower dialog;
    EditText actualprice,reducedprice,description_item_offer,offer_cal;
    CardView offer_card;
    Button offerActvitybtn;
String item_name_offers,description;
Integer item_id_offers,actual_amount,reduced_amount;
Double item_offer_percentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
String id = getIntent().getExtras().getString("name");
int i = getIntent().getExtras().getInt("id");
Log.e("offers",""+id+i);
        item_id_offers=i;

        actualprice=findViewById(R.id.actualamount__itemoffer);
        reducedprice=findViewById(R.id.Reducedamount_post_itemoffer);
        description_item_offer=findViewById(R.id.description_post_itemoffer);

        offer_card = findViewById(R.id.offerpercentage_calculated);
        offer_cal=findViewById(R.id.offer_percent_calculated);
        offerActvitybtn = findViewById(R.id.offer_btn);
        offerActvitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCoupon();
            }
        });
    }
    private void addCoupon()
    {
        description= description_item_offer.getText().toString();
        actual_amount= Integer.valueOf(actualprice.getText().toString());
        reduced_amount= Integer.valueOf(reducedprice.getText().toString());
        Double  dif = Double.valueOf(actual_amount-reduced_amount);
        Double p = dif/actual_amount;
        double pt = p*100;
        Log.e("offer","calculate"+dif+""+p+""+pt);
        item_offer_percentage=pt;
        offer_card.setVisibility(View.VISIBLE);
offer_cal.setText(item_offer_percentage+"%");
        dialog = new ACProgressFlower.Builder(OffersActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();


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
Log.e("offeractivity","params are ="+item_id_offers);
        Log.e("offeractivity","params are ="+actual_amount);
        Log.e("offeractivity","params are ="+reduced_amount);
        Log.e("offeractivity","params are ="+item_offer_percentage);
        Log.e("offeractivity","params are ="+description);
        ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
        Call<LoginResponse> call = mainInterface.addOfferItem(item_id_offers,actual_amount,reduced_amount,item_offer_percentage,description,"0000-00-00","0000-00-00");
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                String success = response.body().getResponsedata().getSuccess();
                dialog.dismiss();
                Log.e("frag4","success="+success);

                try {

                    if(success.equals("1"))
                    {


                        Toast.makeText(OffersActivity.this,"Offer Added successfully",Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(OffersActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(OffersActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
