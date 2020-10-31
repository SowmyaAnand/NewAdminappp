package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;

public class EditSubCategoriesAdapter extends RecyclerView.Adapter<EditSubCategoriesAdapter.MyViewHolder> {
    ArrayList<String> subcat_names = new ArrayList<String>();
    Context context;
    ArrayList Images;

    final String url1 = "http://dailyestoreapp.com/dailyestore/";

    ArrayList<String> lts = new ArrayList<String>();
   // ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;

    public EditSubCategoriesAdapter(Context context, ArrayList adaptersubcat_names, ArrayList Images) {
        this.context = context;
        this.subcat_names = adaptersubcat_names;
        this.Images=Images;
        this.lts.addAll(adaptersubcat_names);

    }

    @Override
    public EditSubCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editsubcategorieslayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EditSubCategoriesAdapter.MyViewHolder vh = new EditSubCategoriesAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final EditSubCategoriesAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        if(subcat_names.size()==0)
        {
            Toast.makeText(context,"No sub category", Toast.LENGTH_LONG).show();
        }
        String name = (String) subcat_names.get(position);

holder.name_subbedit.setText(name);

        String imageurl_total = url1 + Images.get(position);
        Glide.with(context).load(imageurl_total)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image_image_subbedit);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        holder.ed_subbedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.ed_subbedit.getText().toString();
                if(text.equals("SAVE"))
                {
                    holder.ed_subbedit.setText("EDIT");
                    holder.name_subbedit.setEnabled(false);
                }
                else
                {
                    holder.name_subbedit.setEnabled(true);
                    holder.ed_subbedit.setText("SAVE");
                    //holder.ed_pic_subbedit.setVisibility(View.VISIBLE);
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return subcat_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText name_subbedit, quantityy;// init the item view's
        Button ed_subbedit,ed_pic_subbedit;
        ImageView image_image_subbedit;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_subbedit = (EditText) itemView.findViewById(R.id.Title_subbedit);
            ed_subbedit = (Button) itemView.findViewById(R.id.edit_subbedit);
            ed_pic_subbedit =(Button)itemView.findViewById(R.id.editpic_subbedit);
            image_image_subbedit=(ImageView) itemView.findViewById(R.id.im_subbedit);
        }
    }



}
