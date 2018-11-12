package com.ducpaph.fpt.contentprovider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Viewholder> {
    private Context context;
    private ArrayList<model> strings;

    public Adapter(Context context, ArrayList<model> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardviewimg,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtName.setText(strings.get(position).getName());

        Bitmap bitmap= BitmapFactory.decodeFile(strings.get(position).getUri());
        holder.imageView.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
