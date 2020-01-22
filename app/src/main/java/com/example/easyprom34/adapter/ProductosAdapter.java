package com.example.easyprom34.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.easyprom34.R;
import com.example.easyprom34.entidades.Producto;
import com.example.easyprom34.entidades.VolleySingleton;
import com.example.easyprom34.interfaces.clickProductoForDetalle;
import com.example.easyprom34.viewHolder.activityViewHolder;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<activityViewHolder>{

    List<Producto> listaProductos;
    Context context;
    private OnActivityListener onActivityListener;

    public ProductosAdapter(List<Producto> listaProductos, Context context, OnActivityListener onActivityListener) {
        this.listaProductos = listaProductos;
        this.context = context;
        this.onActivityListener = onActivityListener;
    }



    @Override
    public activityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistview, parent, false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new activityViewHolder(vista, onActivityListener);
    }


    @Override
    public void onBindViewHolder(@NonNull activityViewHolder holder, int position) {
        holder.txtDescripcion.setText(listaProductos.get(position).getProducto_detalle());
        holder.txtTitulo.setText(listaProductos.get(position).getProducto_nombre());

        if (listaProductos.get(position).getRutaImagen()=="null"){
            holder.imagen.setImageResource(R.drawable.easy);
        }else{
            cargarImagenUrl(listaProductos.get(position).getRutaImagen(),holder);
        }
    }

    private void cargarImagenUrl(String rutaImagen, final activityViewHolder holder) {

        String ip="https://easy-prom.000webhostapp.com";

        String urlImagen = ip+"/fotosProductos/"+rutaImagen;
        urlImagen = urlImagen.replace(" ", "%20");

        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Algunas imagenes están tardando en cargar :(",Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);

    }


    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public interface OnActivityListener {
        void onActivityClick(int position);
    }
}
