package com.example.test.Activity;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokerHolder>{
    private List<String> categories = new ArrayList<>();
    private onClickListener onClickListener;

    public JokesAdapter() {
    }


    interface onClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(JokesAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public JokesAdapter(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public JokerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new JokerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokerHolder holder, int position) {
            holder.textView.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class JokerHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public JokerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        onClickListener.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}

