package com.example.torridas.atest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Torridas on 13-Jul-17.
 */

public class CostumAutoCompleteAdapter extends ArrayAdapter<Result> {

    private Context context;
    private int layoutResource;
    private List<Result> resultList;

    public class CostumeViewHolder {
        private TextView textView1;

        public CostumeViewHolder(View view) {
            textView1 = (TextView)view.findViewById(R.id.url);
        }

    }

    public CostumAutoCompleteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Result> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.resultList = objects;
    }

    @Override
    public int getCount() {
        if (resultList != null )
            return resultList.size();
        else return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;
        CostumeViewHolder costumeViewHolder;
        final int currentPosition = position;

        if( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResource, parent, false);
            costumeViewHolder =  new CostumeViewHolder(view);
            view.setTag(costumeViewHolder);

        } else {
            view = convertView;
            costumeViewHolder = (CostumeViewHolder)view.getTag();
        }

        Result result = resultList.get(currentPosition);
        costumeViewHolder.textView1.setText(result.getUrl());
        return view;
    }
}
