package com.example.testgooglesearch.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglesearch.R;
import com.example.testgooglesearch.model.room.Data;

import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Data> dataList;

    RVAdapter(Context context, List<Data> dataList) {
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVAdapter.ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.head.setText(data.getHead());
        holder.uri.setText(data.getUri());
        holder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView head, uri, description;

        ViewHolder(View view) {
            super(view);
            head = view.findViewById(R.id.head);
            uri = view.findViewById(R.id.uri);
            description = view.findViewById(R.id.description);
        }
    }
}