package com.appbaco.appbaco.utilities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

import com.amulyakhare.textdrawable.TextDrawable;
import com.appbaco.appbaco.R;

import java.util.List;

/**
 * Created by MARAUJO on 1/23/2017.
 */

public class ColorAdapter <T extends Integer> extends ArrayAdapter implements SpinnerAdapter {
    private Activity activity;
    private final List<T> objects;

    public ColorAdapter(Activity context, List<T> objects) {
        super(context, R.layout.item_color, objects);
        this.activity = context;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //super.getDropDownView(position, convertView, parent);
        View rowView = convertView;

        LayoutInflater inflater = this.activity.getLayoutInflater();
        rowView = inflater.inflate(R.layout.item_color, null);

        int color = objects.get(position);
        TextDrawable drawable = TextDrawable.builder().buildRound("", color); // radius in px
        ImageView image = (ImageView) rowView.findViewById(R.id.imageColor);
        image.setImageDrawable(drawable);

        return rowView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        LayoutInflater inflater = this.activity.getLayoutInflater();
        rowView = inflater.inflate(R.layout.item_color, null);

        int color = objects.get(position);
        TextDrawable drawable = TextDrawable.builder().buildRound("", color);
        ImageView image = (ImageView) rowView.findViewById(R.id.imageColor);
        image.setImageDrawable(drawable);

        return rowView;
    }
}