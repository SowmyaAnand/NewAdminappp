package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    ArrayList<String> personNames = new ArrayList<String>();
    ArrayList<String> person_Names = new ArrayList<String>();
    ArrayList<String> person_mob = new ArrayList<String>();
    ArrayList<String> person_email = new ArrayList<String>();
    Context context;
    ArrayList<String> lts=new ArrayList<String>();
    //ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
 ArrayList personNames_offers = new ArrayList<>();
    int quantity=1;
    public MessagesAdapter(Context context, ArrayList personNames,ArrayList person_email , ArrayList person_mob) {
        this.context = context;
        this.personNames = personNames;

        this.person_email = person_email;
        this.person_mob = person_mob;
        this.lts.addAll(personNames);

    }
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelisitem, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MessagesAdapter.MyViewHolder vh = new MessagesAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MessagesAdapter.MyViewHolder holder, final int position) {
Log.e("message adapter","message adapter"+personNames+person_email+person_mob);
        // set the data in items
        if(personNames.size()>0)
        {
            String name = (String) personNames.get(position);
            holder.name.setText(name);
        }

            if(person_email.size()>0)
            {
                String email = (String) person_email.get(position);
                holder.email_prs.setText(email);
            }
                if(person_mob.size()>0)
                {
                    String mob = (String) person_mob.get(position);
                    holder.mob_prs.setText(mob);

                }

    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mob_prs,email_prs;
        Button addition,substraction,addbtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Title_msg);
            mob_prs = (TextView) itemView.findViewById(R.id.mob_msg);
            email_prs = (TextView) itemView.findViewById(R.id.email_msg);


        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        personNames.clear();
        Iterator itr=personNames.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            personNames.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    personNames.add(s);
                }
            }
        }
        Log.e("text","persons="+personNames);
        notifyDataSetChanged();
    }
}

