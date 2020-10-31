package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dailyestoreapp.adminapp.R.color.green;
import static com.dailyestoreapp.adminapp.R.color.white;

public class PendingNotificationAdapterOrderList extends RecyclerView.Adapter<PendingNotificationAdapterOrderList.MyViewHolder> {
    //ArrayList<String> pending_orders_list_array_item = new ArrayList<String>();
    Context context;
    Integer orderid_adapter;
    Integer order_common=0;
    ACProgressFlower dialog;
    ArrayList<String> lts=new ArrayList<String>();
    ArrayList<String> pending_orders_list_array_item_image = new ArrayList<>();
    ArrayList<String> common_order_no = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_item_postcode = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_item = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_address = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_quantity = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_amount = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_satus = new ArrayList<>();
    ArrayList<Integer> pending_orders_list_array_orderid = new ArrayList<>();
    ArrayList<String> offer_desc = new ArrayList<>();
    ArrayList<String> payment_typeadapter_pending_adpater = new ArrayList<>();
    ArrayList<String> count_typeadapter_pending_adapter = new ArrayList<>();
    ArrayList<String> order_Date_pending_adapter = new ArrayList<>();
    ArrayList<Integer> common_order_noo_adapter = new ArrayList<>();
    //ArrayList pending_orders_list_array_item_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity=1;
    public PendingNotificationAdapterOrderList(Context context, ArrayList<String> pending_orders_list_array_address,ArrayList<String> pending_orders_list_array_item, ArrayList<String> pending_orders_list_array_satus, ArrayList<Integer> pending_orders_list_array_orderid,ArrayList<String> pending_orders_list_array_qnty,ArrayList<String> pending_orders_list_array_amnt,ArrayList<String> pending_orders_list_array_item_img,    ArrayList<String> payment_typeadapter_pending_adpater, ArrayList<String> count_typeadapter_pending_adapter,ArrayList<String> order_Date_pending_adapter,ArrayList<String> offer_desc,ArrayList<String> pending_orders_list_array_item_postcode,ArrayList<Integer> common_order_noo_adapter) {
        this.context = context;
        this.pending_orders_list_array_address=pending_orders_list_array_address;
        this.pending_orders_list_array_item_image=pending_orders_list_array_item_img;
        this.payment_typeadapter_pending_adpater=payment_typeadapter_pending_adpater;
        this.count_typeadapter_pending_adapter=count_typeadapter_pending_adapter;
        this.order_Date_pending_adapter=order_Date_pending_adapter;
        this.pending_orders_list_array_item=pending_orders_list_array_item;
        this.pending_orders_list_array_satus=pending_orders_list_array_satus;
        this.pending_orders_list_array_orderid = pending_orders_list_array_orderid;
        this.pending_orders_list_array_quantity=pending_orders_list_array_qnty;
        this.pending_orders_list_array_amount=pending_orders_list_array_amnt;
        this.offer_desc=offer_desc;
        this.common_order_noo_adapter=common_order_noo_adapter;
        this.pending_orders_list_array_item_postcode=pending_orders_list_array_item_postcode;
        this.lts.addAll(pending_orders_list_array_item);
        order_common=0;

    }
    @Override
    public PendingNotificationAdapterOrderList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_orders_list_no, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PendingNotificationAdapterOrderList.MyViewHolder vh = new PendingNotificationAdapterOrderList.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final PendingNotificationAdapterOrderList.MyViewHolder holder, final int position) {
        Integer co  = common_order_noo_adapter.get(position);
        final String stringco = String.valueOf(co);
        holder.ordernoo_vl.setText(stringco);
        // set the data in items
       String pin =  pending_orders_list_array_item_postcode.get(position);
//        String name = pending_orders_list_array_item.get(position);
//        holder.name_pending.setText(name);
        final String address = pending_orders_list_array_address.get(position);
       holder.address_pending.setText(address);
        String pin_string = pin;
        holder.pincode.setText(pin_string);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent orderdes = new Intent(context,PendingOrdersDescription.class);
                Bundle bundle = new Bundle();
//Add your data to bundle
                bundle.putString("address", address);
                bundle.putString("orderNo", stringco);

//Add the bundle to the intent
                orderdes.putExtras(bundle);
                context.startActivity(orderdes);

            }
        });
//        Integer co  = common_order_noo_adapter.get(position);
//        if(co==0)
//        {
//            holder.ordernoo_vl.setText("");
//        }
//        else
//        {
//            String stringco = String.valueOf(co);
//            holder.ordernoo_vl.setText(stringco);
//        }
//        holder.ofr_description.setText(offer_desc.get(position));
//        String qty = pending_orders_list_array_quantity.get(position);
//        String price_val = pending_orders_list_array_amount.get(position);
//        String ccnntt = count_typeadapter_pending_adapter.get(position);
//        String[] separated = price_val .split(" ");
//        Log.e("cart","the value is "+separated[1] );
//        String val = separated[1];
//        int price_val_int = Integer.parseInt(val);
//        int ccnntt_int = Integer.parseInt(ccnntt);
//        int tot_price_val = price_val_int*ccnntt_int;
//        String int_tot_price_val = String.valueOf(tot_price_val);
//        String pr = "PRICE:"+ "RS "+int_tot_price_val;
//        String imageurl=  pending_orders_list_array_item_image.get(position);
//        Glide.with(context).load(imageurl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.pImage);
//        holder.q_pending.setText(qty);

//        holder.p_pending.setText(pr);
//        holder.pay_type_pending.setText(payment_typeadapter_pending_adpater.get(position));
//        String cntt ="COUNT: "+count_typeadapter_pending_adapter.get(position);
//        holder.cnt_pending.setText(cntt);
//        holder.dt_pending.setText(order_Date_pending_adapter.get(position));
//        if(pending_orders_list_array_satus.get(position).equals("2"))
//        {
//            holder.pd_pending.setText("RETURN REQUESTED");
//        }
//        holder.pd_pending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String st =pending_orders_list_array_satus.get(position);
//                int intial_status_val = Integer.parseInt(st);
//                int changes_status_val=1;
//                if(intial_status_val==0)
//                {
//                    holder.pd_pending.setText("Approved");
//                    changes_status_val=1;
//                }
//                else if(intial_status_val==2)
//                {
//                    holder.pd_pending.setText("Return Approved");
//                    changes_status_val=3;
//                }
//                Log.e("pending","changes_status_val="+changes_status_val);
//                holder.pd_pending.setTextColor(ContextCompat.getColor(context, white));
//                holder.pd_pending.setBackgroundColor(ContextCompat.getColor(context, green));
//                // Toast.makeText(context,"Aproved",Toast.LENGTH_LONG).show();
//                dialog = new ACProgressFlower.Builder(context)
//                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                        .borderPadding(1)
//                        .fadeColor(Color.WHITE).build();
//                dialog.show();
//                String url = "http://dailyestoreapp.com/dailyestore/api/";
//
//                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                        .addInterceptor(loggingInterceptor)
//                        .build();
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(url)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(okHttpClient)
//                        .build();
//                ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
//                orderid_adapter = pending_orders_list_array_orderid.get(position);
//                Log.e("pending","changes_status_val="+changes_status_val+orderid_adapter);
//                Call<ListCategoryResponse> call = mainInterface.changeOrderStatus(orderid_adapter,changes_status_val);
//                call.enqueue(new Callback<ListCategoryResponse>() {
//                    @Override
//                    public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
//                        ListCategoryResponse listCategoryResponseobject = response.body();
//                        int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
//                        try {
//
//
//                            if(success==1)
//                            {
//                                holder.pd_pending.setText("Approved");
//                                holder.pd_pending.setTextColor(ContextCompat.getColor(context, white));
//                                holder.pd_pending.setBackgroundColor(ContextCompat.getColor(context, green));
//                                Toast.makeText(context,"Aproved",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                Toast.makeText(context,"No Data found",Toast.LENGTH_LONG).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(context,"something went wrong",Toast.LENGTH_SHORT).show();
//                        }
//
//
//
//                        dialog.dismiss();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
//
//                        dialog.dismiss();
//                    }
//                });
//
//
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return common_order_noo_adapter.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_pending,address_pending,q_pending,p_pending,pay_type_pending,cnt_pending,dt_pending,ofr_description;// init the item view's
        TextView pincode;
        Button pd_pending;
        ImageView pImage;
        TextView ordernoo_vl;
        public MyViewHolder(View itemView) {
            super(itemView);
//            name_pending = (TextView) itemView.findViewById(R.id.Title_pending);
//            pd_pending=(Button)itemView.findViewById(R.id.pending_pending);
            address_pending=(TextView)itemView.findViewById(R.id.order_common_address_text);
//            q_pending=(TextView)itemView.findViewById(R.id.Quantity_pending);
//            p_pending=(TextView)itemView.findViewById(R.id.Price_pending);
//            pImage=(ImageView)itemView.findViewById(R.id.img_pending);
//            pay_type_pending=itemView.findViewById(R.id.payment_pending);
//            cnt_pending=itemView.findViewById(R.id.count_pending);
//            dt_pending=itemView.findViewById(R.id.oder_date_pending);
//            ofr_description=itemView.findViewById(R.id.offer_desc);
            pincode=itemView.findViewById(R.id.order_common_pincode_text);
            ordernoo_vl=itemView.findViewById(R.id.order_common_no_text);
            // get the reference of item view's

        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        pending_orders_list_array_item.clear();
        Iterator itr=pending_orders_list_array_item.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            pending_orders_list_array_item.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    pending_orders_list_array_item.add(s);
                }
            }
        }
        Log.e("text","persons="+pending_orders_list_array_item);
        notifyDataSetChanged();
    }
}

