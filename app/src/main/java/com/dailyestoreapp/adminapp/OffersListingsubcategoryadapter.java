package com.dailyestoreapp.adminapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class OffersListingsubcategoryadapter extends RecyclerView.Adapter<OffersListingsubcategoryadapter.MyViewHolder2> {
    ArrayList personNames,Imagesoffer;
    Context context;

    public OffersListingsubcategoryadapter(Context context, ArrayList personNames, ArrayList Imagesoffer) {
        this.context = context;
        this.personNames = personNames;
        this.Imagesoffer =Imagesoffer;
    }
    @Override
    public OffersListingsubcategoryadapter.MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_trial, parent, false);
        // set the view's size, margins, paddings and layout parameters
        OffersListingsubcategoryadapter.MyViewHolder2 vh = new OffersListingsubcategoryadapter.MyViewHolder2(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(OffersListingsubcategoryadapter.MyViewHolder2 holder, final int position) {

        // set the data in items
        String name = (String) personNames.get(position);
        int n= (int) Imagesoffer.get(position);
        holder.img.setImageResource(n);
        holder.name.setText(name);
        // holder.name.setText(name);
        // implement setOnClickListener event on item view.
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // display a toast with person name on item click
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return Imagesoffer.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView img;
        public MyViewHolder2(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ct_image);
            name =(TextView)itemView.findViewById(R.id.ct_text);
            // get the reference of item view's

        }
    }
}
