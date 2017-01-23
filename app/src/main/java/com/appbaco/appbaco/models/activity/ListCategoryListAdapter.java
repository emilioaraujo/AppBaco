package com.appbaco.appbaco.models.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.appbaco.appbaco.controllers.activity.CategoryList;
import com.appbaco.appbaco.models.entity.TransactionCategory;
import com.appbaco.appbaco.R;



import java.util.List;

/**
 * Created by MARAUJO on 12/28/2016.
 */

public class ListCategoryListAdapter<T extends TransactionCategory> extends ArrayAdapter {

    private Activity activity;
    private List<TransactionCategory> transactionCategories;
    private Fragment callerFragment;

    public ListCategoryListAdapter(Activity context, Fragment callerFragment, int resource, List<TransactionCategory> tags) {
        super(context, resource, tags);
        this.activity = context;
        this.transactionCategories=tags;

        this.callerFragment = callerFragment;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_category_item, null, true);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.lvDescription);
        final ImageView itemActions = (ImageView) convertView.findViewById(R.id.item_actions);

        //get first and second letter of each String item
        String letters;

        if (transactionCategories.get(position).getName().contains(" ")) {
            letters = String.valueOf(transactionCategories.get(position).getName().charAt(0)).toUpperCase()
                    + String.valueOf(transactionCategories.get(position).getName().charAt(transactionCategories.get(position).getName().toString().indexOf(' ') + 1)).toUpperCase();
        } else {
            letters = String.valueOf(transactionCategories.get(position).getName().charAt(0)).toUpperCase()
                    + String.valueOf(transactionCategories.get(position).getName().charAt(1)).toUpperCase();
        }
        //int color = colorId.get(position);
        TextDrawable drawable = TextDrawable.builder().buildRound(letters, transactionCategories.get(position).getColor()); // radius in px

        title.setText(transactionCategories.get(position).getName());
        description.setText(transactionCategories.get(position).getDescription());
        image.setImageDrawable(drawable);

        //---
        itemActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(activity, itemActions);
                popup.getMenuInflater().inflate(R.menu.list_view_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        CategoryList fragment = (CategoryList) callerFragment;

                        if (i == R.id.action_show_details) {
                            fragment.showRecordDetail(transactionCategories.get(position));
                            return true;
                        }
                        if (i == R.id.action_edit) {
                            fragment.showCreateUpdateDialog(transactionCategories.get(position));
                            return true;
                        }
                        if (i == R.id.action_delete) {
                            fragment.deleteRecord(transactionCategories.get(position));
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