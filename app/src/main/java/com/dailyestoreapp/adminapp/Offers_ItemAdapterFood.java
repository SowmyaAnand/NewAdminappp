package com.dailyestoreapp.adminapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class Offers_ItemAdapterFood extends RecyclerView.Adapter<Offers_ItemAdapterFood.MyViewHolder> {
    ArrayList<String> personNames = new ArrayList<String>();
    Context context;
    ArrayList Images;
    ArrayList<String> lts = new ArrayList<String>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;

    public Offers_ItemAdapterFood(Context context, ArrayList personNames, ArrayList Images) {
        this.context = context;
        this.personNames = personNames;
        this.Images=Images;
        this.lts.addAll(personNames);

    }

    @Override
    public Offers_ItemAdapterFood.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Offers_ItemAdapterFood.MyViewHolder vh = new Offers_ItemAdapterFood.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final Offers_ItemAdapterFood.MyViewHolder holder, final int position) {

        // set the data in items
        String name = (String) personNames.get(position);
        int n= (int) Images.get(position);
        holder.image_image.setImageResource(n);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodlist = new Intent(context,Food_second_layer.class);
                context.startActivity(foodlist);

            }
        });
        holder.outofstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.outofstock.getText().toString();
                if(text.equals("Activate"))
                {
                    text="OUT OF STOCK";
                    holder.outofstock.setText(text);
                    CustomDialogClass2 cdd1=new CustomDialogClass2(context,text);
                    cdd1.show();
                }
                else
                {
                    text="Activate";
                    holder.outofstock.setText(text);
                    CustomDialogClass2 cdd2=new CustomDialogClass2(context,text);
                    cdd2.show();
                }
                // CustomDialogClass1 cdd1=new CustomDialogClass1(context,position);
                // cdd1.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return Images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantityy;// init the item view's
        Button outofstock;
        ImageView image_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Title);
            outofstock = (Button) itemView.findViewById(R.id.outofstock);
            image_image=(ImageView)itemView.findViewById(R.id.im);

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

    public class CustomDialogClass2 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass2(Context a,String t) {
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
}
