package com.example.easyprom34.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyprom34.R;
import com.example.easyprom34.adapter.ProductosAdapter;
import com.example.easyprom34.entidades.Producto;

public class activityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtTitulo, txtDescripcion;
    public ImageView imagen;

    public ProductosAdapter.OnActivityListener onActivityListener;

    public activityViewHolder(@NonNull View itemView, ProductosAdapter.OnActivityListener onActivityListener) {
        super(itemView);
        txtTitulo= (TextView) itemView.findViewById(R.id.tvTitulo);
        txtDescripcion= (TextView) itemView.findViewById(R.id.tvDescripcion);
        imagen=(ImageView) itemView.findViewById(R.id.imageView);

        this.onActivityListener = onActivityListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onActivityListener.onActivityClick(getAdapterPosition());
    }
}
