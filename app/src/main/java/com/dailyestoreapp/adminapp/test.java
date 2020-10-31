package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class test  extends RecyclerView.Adapter<test.MyViewHolder2> {
    ArrayList personNames;
    ArrayList<String> Imagesoffer;
    Context context;
    ArrayList subid;
    int selectedposition_forhighlight=-1;
    String url = "http://dailyestoreapp.com/dailyestore/";
    categorySubcategoryCommunicaion mComminication;
    public test(Context context, ArrayList personNames, ArrayList<String> Imagesoffer, ArrayList subid,categorySubcategoryCommunicaion communication) {
        this.context = context;
        this.personNames = personNames;
        this.Imagesoffer =Imagesoffer;
        this.mComminication=communication;
        this.subid=subid;
    }
    @Override
    public test.MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_trial, parent, false);
        // set the view's size, margins, paddings and layout parameters
        test.MyViewHolder2 vh = new test.MyViewHolder2(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(test.MyViewHolder2 holder, final int position) {
        if(selectedposition_forhighlight==position)
        {

          holder.name.setTextAppearance(context,R.style.fontforselectedsubcategory);
        }
        else
        {
            holder.name.setTextAppearance(context,R.style.fontfornormalsubcategory);
        }
        Log.e("test","personanames="+personNames);
        // set the data in items
        String name = (String) personNames.get(position);

        holder.name.setText(name);
        if(Imagesoffer.size()>0)
        {
            String imageurl=  url+Imagesoffer.get(position);
            Log.e("test","onbined image"+imageurl);
            Glide.with(context).load(imageurl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.img);

        }
        // holder.name.setText(name);
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              selectedposition_forhighlight=position;

                String selectedname = (String) personNames.get(position);
                Integer selectedid = (Integer)subid.get(position);
                Log.e("test","sub category id = "+selectedid);
mComminication.respond(selectedid);
notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView img;
        public MyViewHolder2(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ct_image);
            name = (TextView)itemView.findViewById(R.id.ct_text);
            // get the reference of item view's

        }
    }
}
