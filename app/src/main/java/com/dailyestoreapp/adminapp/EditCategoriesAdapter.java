package com.dailyestoreapp.adminapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
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
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
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

public class EditCategoriesAdapter extends RecyclerView.Adapter<EditCategoriesAdapter.MyViewHolder> {
    ArrayList<String> categories_editcategory = new ArrayList<String>();
    ArrayList<Integer> categories_no_editcategory= new ArrayList<Integer>();
    Context context;
    ACProgressFlower dialog;
    final String url1 = "http://dailyestoreapp.com/dailyestore/";
    ArrayList<String> Images = new ArrayList<String>();
    ArrayList<String> lts = new ArrayList<String>();
  //  ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;
WebServices webServices;
    public EditCategoriesAdapter(Context context, ArrayList personNames, ArrayList Images,ArrayList catno_edit) {
        this.context = context;
        this.categories_editcategory = personNames;
        this.Images=Images;
        categories_no_editcategory = catno_edit;
        this.lts.addAll(personNames);

    }

    @Override
    public EditCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editcategorieslayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EditCategoriesAdapter.MyViewHolder vh = new EditCategoriesAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final EditCategoriesAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        holder.ed_pic_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String name_editcategory = (String) categories_editcategory.get(position);
        Log.e("editcategories","the images are "+Images.get(position)+name_editcategory);
        String imageurl_total = url1 + Images.get(position);
        holder.name_edit.setText(name_editcategory);
        Glide.with(context).load(imageurl_total)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image_image_edit);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next= new Intent(context,editsubcategory.class);
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putInt("edit_cat_no", categories_no_editcategory.get(position));

//Add the bundle to the intent
                next.putExtras(bundle);
                context.startActivity(next);

            }
        });
       holder.ed_edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               String text = holder.ed_edit.getText().toString();
               if(text.equals("SAVE"))
               {
                   holder.ed_edit.setClickable(false);
                   holder.ed_edit.setEnabled(false);
                   dialog = new ACProgressFlower.Builder(context)
                           .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                           .borderPadding(1)
                           .fadeColor(Color.WHITE).build();
                   dialog.show();
                   String typeId_editcategory = String.valueOf(categories_no_editcategory.get(position));
                   String categoryname_editcategory = holder.name_edit.getText().toString();
                   String image_editcategory = Images.get(position);
                   int result=0;
                   String url = "http://dailyestoreapp.com/dailyestore/api/";
                   HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                   loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                   OkHttpClient okHttpClient = new OkHttpClient.Builder()
                           .addInterceptor(loggingInterceptor)
                           .build();
                   final Retrofit retrofit = new Retrofit.Builder()
                           .baseUrl(url)
                           .addConverterFactory(GsonConverterFactory.create())
                           .client(okHttpClient)
                           .build();
                   ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
                   Call<LoginResponse> call = mainInterface.EditCategory(typeId_editcategory,"1",categoryname_editcategory,image_editcategory);
                   call.enqueue(new Callback<LoginResponse>() {
                       @Override
                       public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                           LoginResponse res= response.body();
                           String success= res.getResponsedata().getSuccess();

                           if(success.equals("1"))
                           {
                               holder.name_edit.setEnabled(false);
                               holder.ed_edit.setText("EDIT");
                               Toast.makeText(context,"Successfully Edited ",Toast.LENGTH_SHORT).show();
                               dialog.dismiss();
                               holder.ed_edit.setClickable(true);
                               holder.ed_edit.setEnabled(true);
                           }
                           else
                           {
                               Toast.makeText(context,"Something went wrong ",Toast.LENGTH_SHORT).show();
                               dialog.dismiss();
                               holder.ed_edit.setClickable(true);
                               holder.ed_edit.setEnabled(true);
                           }

                       }

                       @Override
                       public void onFailure(Call<LoginResponse> call, Throwable t) {

                           Toast.makeText(context,"Something went wrong ",Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                           holder.ed_edit.setClickable(true);
                           holder.ed_edit.setEnabled(true);
                       }
                   });

               }
               else
               {
                   holder.name_edit.setEnabled(true);
                   holder.ed_edit.setText("SAVE");

               }

           }
       });


    }


    @Override
    public int getItemCount() {
        return categories_editcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText name_edit, quantityy;// init the item view's
        Button ed_edit,ed_pic_edit;
        ImageView image_image_edit;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_edit = (EditText) itemView.findViewById(R.id.Title_editcategory);
            ed_edit = (Button) itemView.findViewById(R.id.edit_editcategory);
           ed_pic_edit =(Button)itemView.findViewById(R.id.editpic_editcategory);
            image_image_edit=(ImageView) itemView.findViewById(R.id.im_editcategory);
        }
    }

    public void EditCatgeories( final String typeId,final String categoryName,final String createdBy,final String categoryImage)
    {


        //return return_value_EditCatgeories;

    }

}