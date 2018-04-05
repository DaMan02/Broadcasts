package com.dayal.broadcasts.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dayal.broadcasts.R;
import com.dayal.broadcasts.model.IncomingNumber;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<IncomingNumber> numberList = new ArrayList<>();

    public RecyclerViewAdapter(List<IncomingNumber> numberList) {
        this.numberList = numberList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.number.setText(numberList.get(position).getNumber());
        holder.id.setText(Integer.toString(numberList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
     return numberList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id ;
        TextView number;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.id);
            number = (TextView)itemView.findViewById(R.id.number);
        }

    }
}
