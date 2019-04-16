package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YOUSSEF on 24/06/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {
     int colors;
    public WordAdapter(Activity context, ArrayList<Word> swords,int colo) {
        super(context, 0, swords);
        colors=colo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word swor = getItem(position);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.eng_word);
        defaultTextView.setText(swor.getTranslation());

        TextView miwTextView = (TextView) listItemView.findViewById(R.id.mi_word);
        miwTextView.setText(swor.getmiw_translation());

        ImageView mimage =(ImageView)listItemView.findViewById(R.id.ima_id);
        if (swor.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            mimage.setImageResource(swor.getImageResourceId());
            // Make sure the view is visible
            mimage.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            mimage.setVisibility(View.GONE);
        }

   View textcontainer=listItemView.findViewById(R.id.miw_la);
        int color= ContextCompat.getColor(getContext() , colors);
        textcontainer.setBackgroundColor(color);


        return listItemView;
    }
}