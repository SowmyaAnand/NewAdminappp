package com.dailyestoreapp.adminapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponsListingForEditing extends Fragment   {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String tag = "fragment4";
    private Integer selectedSubCategoryNo;
    int start = 0,limit = 3;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ACProgressFlower dialog;
    ProgressBar progressBar;
    //  ArrayList Images_sub = new ArrayList<>(Arrays.asList(R.drawable.h1,R.drawable.h2, R.drawable.h1, R.drawable.h2, R.drawable.h1));

    ArrayList<String> Coupon_Names = new ArrayList<>();
    ArrayList<String> Coupon_Desc = new ArrayList<>();
    ArrayList<Integer> Coupon_percentage = new ArrayList<>();
    ArrayList<Integer> Coupon_status = new ArrayList<>();
    ArrayList<Integer> Coupon_id = new ArrayList<>();
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Coupon_Adapter customAdapter_coupons;
    NestedScrollView nestedScrollViewItemsbasedonsubCategory;


    public CouponsListingForEditing() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }


public void couponList()
{
    dialog = new ACProgressFlower.Builder(getContext())
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
    Call<ListCategoryResponse> call = mainInterface.viewallcoupons();
    call.enqueue(new Callback<ListCategoryResponse>() {
        @Override
        public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {

            String success = response.body().getResponsedata().getSuccess();

            Log.e("frag4","success="+success);
            try {
                if(success.equals("1"))
                {
                    String cpNames,cpDesc;

                    Integer percnt,st,cpId;
                    cpNames = response.body().getResponsedata().getData().get(1).getCouponName();
                    cpDesc = response.body().getResponsedata().getData().get(1).getDescription();
                    percnt = Integer.parseInt(response.body().getResponsedata().getData().get(1).getPercent());

                    int len = response.body().getResponsedata().getData().size();
                    Log.e("couponsedit","length"+len);
                    Coupon_Names.clear();
                    Coupon_Desc.clear();
                    Coupon_percentage.clear();
                    Coupon_status.clear();
                    Coupon_id.clear();
                    for(int i=0;i<len;i++)
                    {
                        cpNames = response.body().getResponsedata().getData().get(i).getCouponName();
                        cpDesc = response.body().getResponsedata().getData().get(i).getDescription();
                        percnt = Integer.parseInt(response.body().getResponsedata().getData().get(i).getPercent());
                        st = Integer.valueOf(response.body().getResponsedata().getData().get(i).getStatus());
                        cpId= Integer.valueOf(response.body().getResponsedata().getData().get(i).getCouponId());
                        Log.e("couponsedit","++"+cpNames+cpDesc+percnt+st);
                        if(st==1)
                        {
                            Coupon_Names.add(cpNames);
                            Coupon_Desc.add(cpDesc);
                            Coupon_percentage.add(percnt);
                            Coupon_status.add(st);
                            Coupon_id.add(cpId);
                        }

                    }
                }
                Log.e("couponsedit","++ values"+Coupon_Names+Coupon_Desc+Coupon_percentage+Coupon_id);
                dialog.dismiss();
                customAdapter_coupons.notifyDataSetChanged();

            }
            catch (Exception e)
            {
Log.e("couponsedit","the coupons are exception"+e);
            }
        }

        @Override
        public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
            dialog.dismiss();
            Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.e(tag,"onactivityview called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString("category");



        }
        View rootView = inflater.inflate(R.layout.couponlistingforediting, container, false);
      couponList();
        //nested recyclerview

        //second recyclerview
        recyclerView_offers = (RecyclerView) rootView.findViewById(R.id.itemrecycler_coupons);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call th,"e constructor of CustomAdapter to send the reference and data to Adapter
Log.e("couponsedit","values="+Coupon_Names+Coupon_Desc+Coupon_percentage);
        customAdapter_coupons = new Coupon_Adapter(rootView.getContext(),Coupon_Names,Coupon_Desc,Coupon_percentage,Coupon_status,Coupon_id);
        recyclerView_offers.setAdapter(customAdapter_coupons);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




}
