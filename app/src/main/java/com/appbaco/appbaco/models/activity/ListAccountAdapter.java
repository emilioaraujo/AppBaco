package com.appbaco.appbaco.models.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.fragment.AccountList;
import com.appbaco.appbaco.models.entity.Account;


import java.util.List;

/**
 * Created by MARAUJO on 12/28/2016.
 */

public class ListAccountAdapter<T extends Account> extends ArrayAdapter {

    private Activity activity;
    private List<Account> entityList;
    private Fragment callerFragment;

    public ListAccountAdapter(Activity context, Fragment callerFragment, int resource, List<Account> entities) {
        super(context, resource, entities);
        this.activity = context;
        this.entityList=entities;

        this.callerFragment = callerFragment;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_account_item, null, true);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.lvDescription);
        TextView description2 = (TextView) convertView.findViewById(R.id.lvDescription2);
        final ImageView itemActions = (ImageView) convertView.findViewById(R.id.item_actions);


        title.setText(entityList.get(position).getName());
        description.setText(entityList.get(position).getDescription());
        Double balance=20.00;
        description2.setText("Actual Balance: "+balance);
        if(entityList.get(position).getAccountTypeId()==1 || entityList.get(position).getAccountTypeId()==2){
            if(balance>0){
                description2.setTextColor(Color.rgb(00,99,00));
            }else{
                description2.setTextColor(Color.BLACK);
            }
        }
        if(entityList.get(position).getAccountTypeId()==3 || entityList.get(position).getAccountTypeId()==4){
            if(balance>0){
                description2.setTextColor(Color.rgb(99,00,00));
            }else{
                description2.setTextColor(Color.BLACK);
            }
        }

        //Drawable mDrawable = null;
        if(entityList.get(position).getAccountTypeId()==1) {
            image.setImageResource(R.drawable.wallet);
            //mDrawable = convertView.getResources().getDrawable(R.drawable.wallet);
        }
        if(entityList.get(position).getAccountTypeId()==2) {
            image.setImageResource(R.drawable.currency_usd);
            //mDrawable = convertView.getResources().getDrawable(R.drawable.currency_usd);
        }
        if(entityList.get(position).getAccountTypeId()==3) {
            image.setImageResource(R.drawable.cash_multiple);
            //mDrawable = convertView.getResources().getDrawable(R.drawable.cash_multiple);
        }
        if(entityList.get(position).getAccountTypeId()==4) {
            image.setImageResource(R.drawable.credit_card);
            //mDrawable = convertView.getResources().getDrawable(R.drawable.credit_card);
        }
        //image.setImageDrawable(mDrawable);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(entityList.get(position).getColor(),PorterDuff.Mode.SRC_ATOP);

        image.setColorFilter(porterDuffColorFilter);


        //---
        itemActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(activity, itemActions);
                popup.getMenuInflater().inflate(R.menu.list_view_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        AccountList fragment = (AccountList) callerFragment;

                        if (i == R.id.action_show_details) {
                            fragment.showRecordDetail(entityList.get(position));
                            return true;
                        }
                        if (i == R.id.action_edit) {
                            fragment.showCreateUpdateDialog(entityList.get(position));
                            return true;
                        }
                        if (i == R.id.action_delete) {
                            fragment.deleteRecord(entityList.get(position));
                            return true;
                        }
                        return onMenuItemClick(item);
                    }
                });

                popup.show();
            }
        });
        //---

        return convertView;
    }
}