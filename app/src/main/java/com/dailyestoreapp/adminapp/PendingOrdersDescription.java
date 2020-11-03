package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PendingOrdersDescription extends AppCompatActivity {
    RecyclerView recyclerView_offers_desc;
    ACProgressFlower dialog;
TextView order_coupon_applied_total_description,order_common_total;
    PendingNotificationAdapter customAdapter_offers_pending;
    // ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    ArrayList<String> item_image_pending = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_item = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_address = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_satus = new ArrayList<>();
    ArrayList<String> offer_desc = new ArrayList<>();
    ArrayList<String> total_coupons = new ArrayList<>();
    ArrayList<String> total_values_forsame_address= new ArrayList<>();
    ArrayList<String> pending_orders_preorderes_book_type = new ArrayList<>();
    ArrayList<String> pending_orders_preorderes_book_date = new ArrayList<>();
    ArrayList<String> orders_list_array_quantity_pending = new ArrayList<>();
    ArrayList<String> orders_list_array_amount_pending = new ArrayList<>();
    ArrayList<Integer> pending_orders_list_array_orderid = new ArrayList<>();
    ArrayList<String> common_order_no = new ArrayList<>();
    ArrayList<String> common_order_noo = new ArrayList<>();
    ArrayList<String> payment_typeadapter_pending = new ArrayList<>();
    ArrayList<String> payment_typeadapter_postcode = new ArrayList<>();
    ArrayList<String> count_typeadapter_pending = new ArrayList<>();
    ArrayList<String> order_Date_pending = new ArrayList<>();
    Integer order_no_val=0;

    TextView order_no_pendingdesc,tot_amt_pendingdesc,cpn_name_pendingdesc,addr_pendingdesc,pay_mode_pendingdesc,pin__pendingdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders_description);
        Bundle bundle = getIntent().getExtras();
        order_no_pendingdesc=findViewById(R.id.order_common_no_text_description);
        tot_amt_pendingdesc=findViewById(R.id.order_common_total_text_description);
        cpn_name_pendingdesc=findViewById(R.id.order_coupon_applied_total_text_description);
        addr_pendingdesc=findViewById(R.id.order_common_address_text_description);
        pay_mode_pendingdesc=findViewById(R.id.order_common_paymentmode_text_description);
        pin__pendingdesc=findViewById(R.id.order_common_pincode_text_description);
        order_coupon_applied_total_description=findViewById(R.id.order_coupon_applied_total_description);
        order_common_total=findViewById(R.id.order_common_total);
//Extract the data…
        String receivedaddress = bundle.getString("address");
        String orderNo=bundle.getString("orderNo");
        String pre_book_type=bundle.getString("pre_book_type");
        pending_orders_list(receivedaddress,orderNo,pre_book_type);
        order_no_pendingdesc.setText("orderNo_func");
        recyclerView_offers_desc = (RecyclerView) findViewById(R.id.itemrecycler_offers_pending_desc);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_offers_desc.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers_pending = new PendingNotificationAdapter(getApplicationContext(),pending_orders_list_array_address,pending_orders_list_array_item,pending_orders_list_array_satus,pending_orders_list_array_orderid,orders_list_array_quantity_pending,orders_list_array_amount_pending,item_image_pending,payment_typeadapter_pending,count_typeadapter_pending, order_Date_pending,offer_desc,payment_typeadapter_postcode,common_order_noo,pending_orders_preorderes_book_type,pending_orders_preorderes_book_date);
        recyclerView_offers_desc.setAdapter(customAdapter_offers_pending);

    }
    private void pending_orders_list(final String correspondingaddresss, final String orderNo_func,final String pre_book_type)
    {
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .borderPadding(1)
                .fadeColor(Color.WHITE).build();
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
        Call<ListCategoryResponse> call = mainInterface.orderslist();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                try {

                    pending_orders_list_array_item.clear();
                    pending_orders_list_array_address.clear();
                    pending_orders_list_array_satus.clear();
                    common_order_noo.clear();
                    if(success==1)
                    {
                        int data_length = response.body().getResponsedata().getData().size();

                        double tot_val=0.0;
                        String tot_cpn="";
                        String boooking_type="";
                        for(int i=0; i<data_length; i++)
                        {
                            ListCategoryResponseData dt = response.body().getResponsedata().getData().get(i);
                            String item_name = dt.getItemName();
                            String status = dt.getStatus();
                            int st = Integer.parseInt(status);

                                String address = dt.getAddress();
                            String bk_type=dt.getbookingType();

                            String bk_date=dt.getpreBookingDate();

                                if ((correspondingaddresss.equals(address))&&(bk_type.equals("1")))
                                {
                                    boooking_type="1";
                                    Log.e("pedning","bk_tyoe="+boooking_type);
                                String itemname_orders = dt.getItemName();

                                String orderid = dt.getOrderId();
                                int order_no = Integer.parseInt(orderid);
                                String quantity_pending = dt.getQuantity();
                                String amount_pending = dt.getPrice();
                                String imageurl = dt.getImage();
                                String imageurl_total = url1 + imageurl;
                                String order_dt = dt.getCreatedAt();
                                String count = dt.getCount();
                                String offer_descc = dt.getDescription();
                                String pay_type = dt.getpaymentType();
                                String pay_text = "";
                                String ptcode = dt.getPostCode();
                                String tot_amtt = dt.getTotalOrdersPrice();
                                String cpn = dt.getCouponApplied();

                                if (pay_type.equals("0")) {

                                    pay_text = "CASH ON DELIVERY";
                                } else if (pay_type.equals("1")) {
                                    pay_text = "PAID ON GPAY";
                                }
                                payment_typeadapter_pending.add(pay_text);
                                //  payment_typeadapter_postcode.add(ptcode);
                                count_typeadapter_pending.add(count);
                                order_Date_pending.add(order_dt);
                                offer_desc.add(offer_descc);
                                pending_orders_list_array_satus.add(status);
                                item_image_pending.add(imageurl_total);
                                    pending_orders_preorderes_book_date.add(bk_date);
                                    pending_orders_preorderes_book_type.add(bk_type);
                                //pending_orders_list_array_address.add(address);
                                pending_orders_list_array_item.add(itemname_orders);
                                pending_orders_list_array_orderid.add(order_no);
                                orders_list_array_quantity_pending.add(quantity_pending);
                                orders_list_array_amount_pending.add(amount_pending);
                                    pending_orders_list_array_address.add(address);
                                    payment_typeadapter_postcode.add(ptcode);
                                    common_order_noo.add(address);
                                   // setchanges(orderNo_func,ptcode,address,tot_amtt,pay_text,cpn);
                                order_no_pendingdesc.setText(orderNo_func);
                                pin__pendingdesc.setText(ptcode);
                                    addr_pendingdesc.setText(address);
                                    cpn_name_pendingdesc.setVisibility(View.GONE);
                                    order_common_total.setVisibility(View.GONE);
                                    order_coupon_applied_total_description.setVisibility(View.GONE);
                                    pay_mode_pendingdesc.setText( pay_text);
                                   tot_amt_pendingdesc.setVisibility(View.GONE);

                            }
else if((correspondingaddresss.equals(address))&&(bk_type.equals("0")))

                                {
                                    boooking_type="0";
                                    Log.e("pedning","bk_tyoe="+boooking_type);
                                    Log.e("pedning","bk_tyoe zero="+bk_type);
                                    String itemname_orders = dt.getItemName();

                                    String orderid = dt.getOrderId();
                                    int order_no = Integer.parseInt(orderid);
                                    String quantity_pending = dt.getQuantity();
                                    String amount_pending = dt.getPrice();
                                    String imageurl = dt.getImage();
                                    String imageurl_total = url1 + imageurl;
                                    String order_dt = dt.getCreatedAt();
                                    String count = dt.getCount();
                                    String offer_descc = dt.getDescription();
                                    String pay_type = dt.getpaymentType();
                                    String pay_text = "";
                                    String ptcode = dt.getPostCode();
                                    String tot_amtt = dt.getTotalOrdersPrice();
                                    String cpn = dt.getCouponApplied();
                                    Log.e("pending","order description ==>"+tot_amtt+cpn);
                                    if(!(total_values_forsame_address.contains(tot_amtt)))
                                    {
                                        total_values_forsame_address.add(tot_amtt);
                                    }

                                        total_coupons.add(cpn);


                                    Log.e("pendingorders","total_values_forsame_address "+total_values_forsame_address);
                                    Log.e("pendingorders","total_coupons "+total_coupons);


                                    if (pay_type.equals("0")) {

                                        pay_text = "CASH ON DELIVERY";
                                    } else if (pay_type.equals("1")) {
                                        pay_text = "PAID ON GPAY";
                                    }
                                    payment_typeadapter_pending.add(pay_text);
                                    //  payment_typeadapter_postcode.add(ptcode);
                                    count_typeadapter_pending.add(count);
                                    order_Date_pending.add(order_dt);
                                    offer_desc.add(offer_descc);
                                    pending_orders_list_array_satus.add(status);
                                    item_image_pending.add(imageurl_total);
                                    pending_orders_preorderes_book_date.add(bk_date);
                                    pending_orders_preorderes_book_type.add(bk_type);
                                    //pending_orders_list_array_address.add(address);
                                    pending_orders_list_array_item.add(itemname_orders);
                                    pending_orders_list_array_orderid.add(order_no);
                                    orders_list_array_quantity_pending.add(quantity_pending);
                                    orders_list_array_amount_pending.add(amount_pending);
                                    pending_orders_list_array_address.add(address);
                                    payment_typeadapter_postcode.add(ptcode);
                                    common_order_noo.add(address);
                                    // setchanges(orderNo_func,ptcode,address,tot_amtt,pay_text,cpn);
                                    order_no_pendingdesc.setText(orderNo_func);
                                    pin__pendingdesc.setText(ptcode);
                                    addr_pendingdesc.setText(address);
                                   // cpn_name_pendingdesc.setVisibility(View.VISIBLE);
                                    pay_mode_pendingdesc.setText( pay_text);
                                    //tot_amt_pendingdesc.setVisibility(View.VISIBLE);
                                }

                        }

                        for(int i=0;i<total_values_forsame_address.size();i++)
                        {
                            try {
                                double t = Double.parseDouble(total_values_forsame_address.get(i));

                                tot_val=tot_val+t;
                                Log.e("pendingorders","tot_val "+t);
                            }
                            catch (Exception e)
                            {
                                Log.e("pending","the exception is "+e);
                            }
                        }
                        for(int i=0;i<total_coupons.size();i++)
                        {
                            if(i==0)
                            {
                                tot_cpn=total_coupons.get(i);
                            }
                            else
                            {
                                tot_cpn=tot_cpn+","+total_coupons.get(i);
                            }
                        }
                        Log.e("pedning","bk_tyoe="+boooking_type);
if(boooking_type.equals("0"))
{
    cpn_name_pendingdesc.setText(tot_cpn);
    String t = String.valueOf(tot_val);
    tot_amt_pendingdesc.setText(t);
}
else
{
    cpn_name_pendingdesc.setVisibility(View.GONE);
    order_common_total.setVisibility(View.GONE);
    tot_amt_pendingdesc.setVisibility(View.GONE);
    order_coupon_applied_total_description.setVisibility(View.GONE);
}

                        Log.e("pdndngdesc","values="+pending_orders_list_array_item+ pending_orders_list_array_address);
                        customAdapter_offers_pending.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(PendingOrdersDescription.this,"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("pending","eror==>"+e.getMessage());
                    Toast.makeText(PendingOrdersDescription.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });


    }
//    public void setchanges( String orderNo_func, String ptcode, String address, String tot_amtt, String pay_text, String cpn)
//    {
//        order_no_pendingdesc.setText(orderNo_func);
//        pin__pendingdesc.setText(ptcode);
//        addr_pendingdesc.setText(address);
//        cpn_name_pendingdesc.setText(tot_amtt);
//        pay_mode_pendingdesc.setText( pay_text);
//        tot_amt_pendingdesc.setText(cpn);
//    }

}
