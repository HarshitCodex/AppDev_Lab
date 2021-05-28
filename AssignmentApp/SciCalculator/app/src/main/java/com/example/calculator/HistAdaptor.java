package com.example.calculator;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistAdaptor extends RecyclerView.Adapter<HistAdaptor.CalcViewHolder> {

    private ArrayList<HistoryItem> mHistList;

    @NonNull
    @Override
    public CalcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hist_item, parent, false);
        CalcViewHolder evh = new CalcViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CalcViewHolder holder, int position) {
        HistoryItem currentItem = mHistList.get(position);
        holder.mTextView.setText(currentItem.getCalcResource());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.mTextView.getContext(),MainActivity.class);
                intent.putExtra("HistVal",holder.mTextView.getText().toString());
                holder.mTextView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHistList.size();
    }

    public static class CalcViewHolder extends RecyclerView.ViewHolder {
    
        public TextView mTextView;

        public CalcViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.calc);
        }
    }

    public HistAdaptor(ArrayList<HistoryItem> exampleList) {
        mHistList = exampleList;
    }

}
