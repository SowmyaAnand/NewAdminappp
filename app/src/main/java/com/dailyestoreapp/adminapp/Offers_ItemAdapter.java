package com.dailyestoreapp.adminapp;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.telephony.CellInfoNr;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import cc.cloudist.acplibrary.ACProgressPie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Offers_ItemAdapter extends RecyclerView.Adapter<Offers_ItemAdapter.MyViewHolder> {
    ArrayList personNames = new ArrayList<String>();
    Context context;

    ArrayList<Integer> adapteritem_id = new ArrayList<>();
    ArrayList<Integer> item_id_offerss = new ArrayList<>();
    ArrayList<String>Images=new ArrayList<>();
    ArrayList<String>Item_desc=new ArrayList<>();
ArrayList<Integer> it_quantity = new ArrayList<>() ;
    ArrayList<Integer> it_price = new ArrayList<>();
    ArrayList<String> lts = new ArrayList<String>();
    ArrayList<Integer> item_status_adapter = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;
    String text_item_status;
    private String tag ="OfferItemadapter";
    ACProgressFlower dialog;
    public Offers_ItemAdapter(Context context, ArrayList personNames, ArrayList<String> Images,ArrayList itm_quantity,ArrayList itm_price,ArrayList adpaterit_id,ArrayList item_status,ArrayList item_description,ArrayList item_offers) {
        this.context = context;
        this.personNames = personNames;
        this.Images=Images;
        this.it_price=itm_price;
        this.it_quantity=itm_quantity;
        this.Item_desc=item_description;
        this.item_id_offerss=item_offers;
        this.item_status_adapter=item_status;
        this.lts.addAll(personNames);
        this.adapteritem_id= adpaterit_id;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
Log.e(tag,"items price"+Item_desc);
        // set the data in items
        final String name = (String) personNames.get(position);
        Log.e(tag,"items price"+personNames);
        holder.name.setText(name);
        if(Images.size()>0)
        {
            Log.e("offeradapter","images array is "+Images);
            String imageurl=  Images.get(position);
            Log.e(tag,"onbined image"+imageurl);
            Glide.with(context).load(imageurl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image_image);

        }
if(item_status_adapter.size()>0)
{
    if(item_status_adapter.get(position)==1)
    {
        holder.outofstock.setText("Active");
    }
    else
    {
        holder.outofstock.setText("Out of stock");
    }
}

        Log.e(tag,"price + quantity "+it_quantity+it_quantity.size());
        if(it_quantity.size()>0)
        {
            String text = "Quantity:"+String.valueOf(it_quantity.get(position));
            holder.i_quantityy.setText(text);
        }
        if(it_price.size()>0)
        {
            String prce  = String.valueOf(it_price.get(position));
            holder.i_price.setText("Rs "+prce+" /-");
        }
        if(item_id_offerss.size()>0)
        {
            int i= item_id_offerss.get(position);
            if(i>0)
            {
                String ofr = String.valueOf(item_id_offerss.get(position));
                String ofr_t =ofr+"% OFF";
                holder.offer_percent.setText(ofr_t);
            }
            else
            {
                String ofr = String.valueOf("");
                holder.offer_percent.setText(ofr);
            }

        }
        if(Item_desc.size()>0)
        {
            String d=Item_desc.get(position);
            if(d.contains("none"))
            {
                holder.offer_desc.setText("");
            }
            else
            {
                holder.offer_desc.setText(Item_desc.get(position));
            }

        }
String p= String.valueOf(holder.offer_percent.getText());
        String dd = holder.offer_desc.getText().toString();
//        if(p.equals("")&&dd.equals(""))
//        {
//            holder.adddoffer_adapter.setVisibility(View.VISIBLE);
//        }

//        if(ofr_pcntg>0)
//        {
//            holder.adddoffer_adapter.setVisibility(View.GONE);
//            String ofr = String.valueOf(item_id_offerss.get(position));
//            String ofr_t =ofr+"% OFF";
//            holder.offer_percent.setText(ofr_t);
//
//        }

//        holder.adddoffer_adapter.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Integer idd= adapteritem_id.get(position);
//                    String name_ofr = (String) personNames.get(position);
//                    Intent next = new Intent(context,OffersActivity.class);
//                    next.putExtra("id",idd);
//                    next.putExtra("name",name_ofr);
//                    context.startActivity(next);
//                }
//            });
//            if(Item_des.equals("none"))
//            {
//
//            }
//            else
//            {
//                holder.adddoffer_adapter.setVisibility(View.GONE);
//                holder.offer_desc.setText(Item_des);
//
//            }
//        if((item_id_offerss.get(position)==0)&&(Item_des.equals("none")))
//        {
//
//
//
//        }


        //holder.i_price.setText(it_price.get(position));
        //holder.i_quantityy.setText(it_quantity.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("person","person"+personNames);

            }
        });

holder.outofstock.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int clicked_itemId = adapteritem_id.get(position);
        text_item_status = holder.outofstock.getText().toString();
        int status_param;
        if(text_item_status.equals("Active"))
        {
         status_param=0;
        }
        else
        {
status_param=1;
        }

         final int item_clicked_id = adapteritem_id.get(position);
        Log.e("url","url");
        dialog = new ACProgressFlower.Builder(context)
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
        ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
        Call<ItemActivateResponse> call = mainInterface.ItemActivate(clicked_itemId,status_param);
        call.enqueue(new Callback<ItemActivateResponse>() {
            @Override
            public void onResponse(Call<ItemActivateResponse> call, retrofit2.Response<ItemActivateResponse> response) {
                ItemActivateResponse obj =response.body();
                int success = Integer.parseInt(obj.getResponsedata().getSuccess());
                Log.e(tag,"success="+obj.getResponsedata().getSuccess());
                if(success==1)
                {
                    if(text_item_status.equals("Active"))
                    {
                        text_item_status="OUT OF STOCK";
                        holder.outofstock.setText(text_item_status);
                        CustomDialogClass1 cdd1=new CustomDialogClass1(context,text_item_status);
                        cdd1.show();
                    }
                    else
                    {
                        text_item_status="Active";
                        holder.outofstock.setText(text_item_status);
                        CustomDialogClass1 cdd2=new CustomDialogClass1(context,text_item_status);
                        cdd2.show();
                    }
                }
                  dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ItemActivateResponse> call, Throwable t) {
Log.e(tag,"error"+t.getMessage());
dialog.dismiss();
            }
        });


    }
});


    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, i_quantityy,i_price,offer_percent,offer_desc,adddoffer_adapter;// init the item view's
        Button outofstock;

        ImageView image_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Title);
            i_quantityy = (TextView)itemView.findViewById(R.id.publishNme);
            i_price = (TextView)itemView.findViewById(R.id.prce);
            outofstock = (Button) itemView.findViewById(R.id.outofstock);
            image_image=(ImageView)itemView.findViewById(R.id.im);
            offer_percent=(TextView)itemView.findViewById(R.id.percentage_offer);
offer_desc=(TextView)itemView.findViewById(R.id.offerdesc);
            adddoffer_adapter=(TextView)itemView.findViewById(R.id.adddoffer);
        }
    }

    public void filter(String charText) {
        Log.e("texting if", "persons=" + charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2", "persons=" + charText);
        personNames.clear();
        Iterator itr = personNames.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3", "persons=" + charText);
            personNames.addAll(lts);
        } else {
            for (int i = 0; i < lts.size(); i++) {
                Log.e("texting else", "persons=" + lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    personNames.add(s);
                }
            }
        }
        Log.e("text", "persons=" + personNames);
        notifyDataSetChanged();
    }

    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a,String t) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
            this.txt = t;
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Your Item is marked as "+this.txt);
            yes.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    dismiss();
                    break;

                default:
                    break;
            }

        }
    }
    private void Activate(final int itemIdparam, final String statusparam){
        final String url = "http://dailyestoreapp.com/dailyestore/api/activateItem";

        final JSONObject obj = new JSONObject();
        try {
            obj.put("itemId", itemIdparam);
            obj.put("status", statusparam);
             } catch (JSONException e)
            {
            e.printStackTrace();
            }
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("url",""+url);
                        Log.e("obj",""+obj);
                        Log.e("RESPONSE",""+response.toString());

                        // response start
                        JSONObject sub = new JSONObject();
                        try {
                            sub.put("success","0");
                            sub.put("data",0);
                            response.put("responsedata",sub);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//response end
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if(jsonObject.has("responsedata")) {
                                Log.e("jsonObject","jsonObject is "+jsonObject);
                                JSONObject json2 = new JSONObject(String.valueOf(jsonObject));
                                JSONObject res = (JSONObject) json2.get("responsedata");
                                JSONObject actualvalue = new JSONObject(res.toString());
                                Log.e("json2","json2is "+json2);
                                Log.e("res","res is"+res);
                                Log.e("actualvalue","actualvalue is"+actualvalue);
                                String result = actualvalue.getString("success");
                                Log.e("actualvalue","success  is"+result);
                                if(result.equals("0"))
                                {

                                }
                                else
                                {

                                }
                              // Object json2 = jsonObject.get("responsedata");
                               // Log.e("json2","json2 is "+json2);

                               // Object result = jsonObject.get("success");
                                 //  Log.e("success","success is "+result);


                                // String token = jsonObject.getString("token");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.dismiss();
                        // showToast("Unable to connect Server,please try after sometime!");
                        Log.e("ERROR",""+error);
                    }
                });

        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
        Log.d("request>>>>>>", queue.toString());
        //                String URL ="http://dailyestoreapp.com/dailyestore/api/userDetails";
//                JSONObject jsonBody = new JSONObject();
//                try {
//                    jsonBody.put("userId", "1");
//                    final String requestBody = jsonBody.toString();
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Log.e("VOLLEY", response);
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("VOLLEY", error.toString());
//                        }
//                    }) {
//                        @Override
//                        public String getBodyContentType() {
//                            return "application/json; charset=utf-8";
//                        }
//
//                        @Override
//                        public byte[] getBody() throws AuthFailureError {
//                            try {
//                                return requestBody == null ? null : requestBody.getBytes("utf-8");
//                            } catch (UnsupportedEncodingException uee) {
//                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                                return null;
//                            }
//                        }
//
//                        @Override
//                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                            String responseString = "";
//                            if (response != null) {
//                                responseString = String.valueOf(response.statusCode);
//                                // can get more details such as response.headers
//                            }
//                            Log.e("tresponse","the response"+response);
//                            return  super.parseNetworkResponse(response);
//                        }
//                    };
//                    queue.add(stringRequest);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e("error","error"+e);
//                }
    }

}