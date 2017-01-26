package com.appbaco.appbaco.models.activity;

import android.app.Activity;
import android.graphics.Color;
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
import com.appbaco.appbaco.controllers.activity.MainActivity;
import com.appbaco.appbaco.controllers.entity.AccountController;
import com.appbaco.appbaco.controllers.fragment.TransactionList;
import com.appbaco.appbaco.models.entity.Account;
import com.appbaco.appbaco.models.entity.TransactionDetail;
import com.appbaco.appbaco.models.entity.TransactionHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARAUJO on 12/28/2016.
 */

public class ListTransactionAdapter<T extends TransactionHeader> extends ArrayAdapter {

    private Activity activity;
    private List<TransactionHeader> entityList;
    private Fragment callerFragment;
    private AccountController accountController;

    public ListTransactionAdapter(Activity context, Fragment callerFragment, int resource, List<TransactionHeader> entities) {
        super(context, resource, entities);
        this.activity = context;
        this.entityList = entities;

        this.callerFragment = callerFragment;
        this.accountController = new AccountController(MainActivity.appbacoDatabase);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_transaction_item, null, true);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.lvDescription);
        TextView description2 = (TextView) convertView.findViewById(R.id.lvDescription2);
        final ImageView itemActions = (ImageView) convertView.findViewById(R.id.item_actions);


        try {
            ArrayList<TransactionDetail> array =entityList.get(position).getTransactionDetails();
            Account account = this.accountController.findById(array.get(0).getAccountId());
            title.setText(account.getName());
            description2.setText("Amount: " + array.get(0).getAmount());
            if (entityList.get(position).getTransactionTypeId() == 1) {
                title.setText("" +
                        this.accountController.findById(entityList.get(position).getTransactionDetails().get(0).getId()).getName()
                        + " -> " +
                        this.accountController.findById(entityList.get(position).getTransactionDetails().get(1).getId()).getName()
                );
            }
        } catch (Exception ex) {

        }
        description.setText(entityList.get(position).getConcept());



        if (entityList.get(position).getTransactionTypeId() == 1) {
            image.setImageResource(R.drawable.shuffle_variant);
        }
        if (entityList.get(position).getTransactionTypeId() == 2) {
            image.setImageResource(R.drawable.arrow_down_bold);
        }
        if (entityList.get(position).getTransactionTypeId() == 3) {
            image.setImageResource(R.drawable.arrow_up_bold);
        }

        //---
        itemActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(activity, itemActions);
                popup.getMenuInflater().inflate(R.menu.list_view_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        TransactionList fragment = (TransactionList) callerFragment;

                        if (i == R.id.action_show_details) {
                            fragment.showRecordDetail(entityList.get(position));
                            return true;
                        }
                        if (i == R.id.action_edit) {
                            fragment.showCreateUpdateDialog(entityList.get(position),0);
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