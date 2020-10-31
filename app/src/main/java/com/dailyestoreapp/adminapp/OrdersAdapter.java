package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    
    Context context;
    ArrayList<String> lts=new ArrayList<String>();
    ArrayList<String> orders_list_adapter_item_images = new ArrayList<>();
    ArrayList<String> orders_list_adapter_item = new ArrayList<>();
    ArrayList<String> orders_list_adapter_item_address = new ArrayList<>();
    ArrayList<String> orders_list_adapter_quantity = new ArrayList<>();
    ArrayList<String> orders_list_adapter_amount = new ArrayList<>();
    ArrayList<String> orders_list_adapter_date = new ArrayList<>();
    ArrayList<String> orders_list_array_item_status=new ArrayList<>();
    ArrayList<String> orders_list_array_item_description = new ArrayList<>();
    ArrayList<String> payment_typeadapter_pending_adpater = new ArrayList<>();
    ArrayList<String> count_typeadapter_pending_adapter = new ArrayList<>();
    ArrayList<String> order_Date_pending_adapter = new ArrayList<>();
   // ArrayList orders_list_adapter_item_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity=1;
    public OrdersAdapter(Context context, ArrayList<String> orders_list_adapter_item, ArrayList<String> orders_list_adapter_quantity, ArrayList<String> orders_list_adapter_amount, ArrayList<String> orders_list_adapter_date,ArrayList<String> orders_list_adapter_item_address,ArrayList<String> orders_list_adapter_item_img,ArrayList<String> orders_list_array_item_status,ArrayList<String> payment_typeadapter_pending_adpater, ArrayList<String> count_typeadapter_pending_adapter,ArrayList<String> order_Date_pending_adapter,ArrayList<String> orders_list_array_item_description) {
        this.context = context;
        this.orders_list_adapter_amount = orders_list_adapter_amount;
        this.orders_list_adapter_item = orders_list_adapter_item;
        this.orders_list_adapter_date = orders_list_adapter_date;
        this.orders_list_adapter_quantity = orders_list_adapter_quantity;
        this.orders_list_adapter_item_address=orders_list_adapter_item_address;
        this.orders_list_adapter_item_images=orders_list_adapter_item_img;
        this.orders_list_array_item_status=orders_list_array_item_status;
        this.lts.addAll(orders_list_adapter_item);
        this.payment_typeadapter_pending_adpater=payment_typeadapter_pending_adpater;
        this.count_typeadapter_pending_adapter=count_typeadapter_pending_adapter;
        this.order_Date_pending_adapter=order_Date_pending_adapter;
        this.orders_list_array_item_description=orders_list_array_item_description;
    }
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        OrdersAdapter.MyViewHolder vh = new OrdersAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final OrdersAdapter.MyViewHolder holder, final int position) {
        String ccnntt = count_typeadapter_pending_adapter.get(position);
        String cc = "COUNT:"+ccnntt;
       holder.count_orders.setText(cc);
       holder.offer_desc.setText(orders_list_array_item_description.get(position));
holder.payment_type_orders.setText(payment_typeadapter_pending_adpater.get(position));
        String dtt = order_Date_pending_adapter.get(position);
        Log.e("orderfragment","createdat ="+dtt);
        holder.dat_orders.setText(dtt);
        // set the data in items
        if(orders_list_array_item_status.get(position).equals("1"))
        {
            holder.ordr_ststus.setText(" APPROVED");
        }
        else if(orders_list_array_item_status.get(position).equals("3"))
        {
            holder.ordr_ststus.setText("REPLACEMENT APPROVED");
        }
        if(orders_list_adapter_item.size()>0)
        {
            String name = orders_list_adapter_item.get(position);
            holder.name_orders.setText(name);
        }
            if(orders_list_adapter_quantity.size()>0)
            {
                String quantity = orders_list_adapter_quantity.get(position);
                holder.quantityy_orders.setText(quantity);
            }
                if(orders_list_adapter_item_address.size()>0)
                {
                    String created = orders_list_adapter_item_address.get(position);
                    holder.dt_orders.setText(created);
                }
                    if(orders_list_adapter_amount.size()>0) {

                       // String price = "PRICE:"+orders_list_adapter_amount.get(position)+"/-";
                        String price_val = orders_list_adapter_amount.get(position);
                        if(!(price_val.equals(" ")))
                        {
                            String[] separated = price_val .split(" ");
                            Log.e("cart","the value for test is "+price_val );
                            if((separated.length>1))
                            {
                                String val = separated[1];
                                int price_val_int = Integer.parseInt(val);
                                int ccnntt_int = Integer.parseInt(ccnntt);
                                int tot_price_val = price_val_int*ccnntt_int;
                                String int_tot_price_val = String.valueOf(tot_price_val);
                                String pr = "PRICE:"+ "RS "+int_tot_price_val;
                                holder.price_orders.setText(pr);
                            }

                        }


                    }
                    if(orders_list_adapter_item_images.size()>0)
                    {
                        String imageurl=  orders_list_adapter_item_images.get(position);
                        Glide.with(context).load(imageurl)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(holder.ig);
                    }


    }


    @Override
    public int getItemCount() {
        return orders_list_adapter_item.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_orders,quantityy_orders,price_orders,dt_orders,count_orders,payment_type_orders,dat_orders,offer_desc;// init the item view's
        Button addition,substraction,addbtn;
        TextView ordr_ststus;
        ImageView ig;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_orders = (TextView) itemView.findViewById(R.id.Title_orders);
           quantityy_orders = (TextView) itemView.findViewById(R.id.publishNme_orders);
            price_orders = (TextView) itemView.findViewById(R.id.prce_orders);
            dt_orders = (TextView) itemView.findViewById(R.id.dte_orders);
            ig=(ImageView)itemView.findViewById(R.id.img_orders_img);
            ordr_ststus=itemView.findViewById(R.id.order_statuss);
count_orders=itemView.findViewById(R.id.countitems_orders);
payment_type_orders=itemView.findViewById(R.id.payment_orders);
dat_orders=itemView.findViewById(R.id.date_orders);
offer_desc=itemView.findViewById(R.id.offer_desc_orders);

        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        orders_list_adapter_item.clear();
        Iterator itr=orders_list_adapter_item.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            orders_list_adapter_item.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    orders_list_adapter_item.add(s);
                }
            }
        }
        Log.e("text","persons="+orders_list_adapter_item);
        notifyDataSetChanged();
    }
}
