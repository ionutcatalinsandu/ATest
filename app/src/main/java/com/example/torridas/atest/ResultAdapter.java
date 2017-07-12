package com.example.torridas.atest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Torridas on 11-Jul-17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.CostumViewHolder> {

    private List<Result> resultList;

    public ResultAdapter(List<Result> resultList ){
        this.resultList = resultList;
    }

    @Override
    public CostumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        CostumViewHolder costumViewHolder = new CostumViewHolder(view);
        return costumViewHolder;
    }

    @Override
    public void onBindViewHolder(CostumViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.textViewID.setText(String.valueOf(result.getId()));
        holder.textViewUrl.setText(result.getUrl());
        holder.textViewName.setText(result.getName());
    }

    @Override
    public int getItemCount() {
        if(resultList != null )
            return resultList.size();
        else
            return 0;
    }

    public class CostumViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewUrl;

        public CostumViewHolder(View itemView) {
            super(itemView);
            textViewID = (TextView)itemView.findViewById(R.id.resultID);
            textViewName = (TextView)itemView.findViewById(R.id.name);
            textViewUrl = (TextView)itemView.findViewById(R.id.url);
        }
    }
}
