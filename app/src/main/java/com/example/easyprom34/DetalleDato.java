package com.example.easyprom34;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.easyprom34.entidades.Producto;
import com.example.easyprom34.entidades.VolleySingleton;

import java.util.List;

public class DetalleDato extends AppCompatActivity {

    public List<Producto> listaProductos;
    Producto producto;
    TextView titulo, detalle;
    ImageView imagen;
    private String rutaImagen;
    String nombreProducto, productoId, localId, productoPrecio,productoDescuento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dato);

        reclamar();
        locales();

        titulo = (TextView) findViewById(R.id.tvTituloDetalle);
        detalle = (TextView) findViewById(R.id.tvDescripcionDetalle);
        imagen = (ImageView) findViewById(R.id.imgFotoDetalle);

        producto = getIntent().getExtras().getParcelable("selectedActivity");
        if (producto != null) {
            loadData(producto);
        }
    }

    private void loadData(Producto producto) {
        titulo.setText(producto.getProducto_nombre());
        detalle.setText(producto.getProducto_detalle());
        rutaImagen = producto.getRutaImagen();
        //para qr
        nombreProducto = producto.getProducto_nombre();
        productoId = String.valueOf(producto.getIdProducto());
        localId = String.valueOf(producto.getLocal_Id());
        productoPrecio = String.valueOf(producto.getProducto_precio());
        productoDescuento = String.valueOf(producto.getProducto_descuento());

        if (rutaImagen == "null"){

            imagen.setImageResource(R.drawable.easy);

        }else {

            String ip = "https://easy-prom.000webhostapp.com";

            String urlImagen = ip + "/fotosProductos/" + rutaImagen;
            urlImagen = urlImagen.replace(" ", "%20");

            ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imagen.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(DetalleDato.this, "Esta promo no tiene imagen :(", Toast.LENGTH_SHORT).show();
                }

            });
            VolleySingleton.getIntanciaVolley(DetalleDato.this).addToRequestQueue(imageRequest);
        }
    }

    public void reclamar(){

        Button boton = (Button) findViewById(R.id.btnReclamar);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(DetalleDato.this, DatosCanje.class);
                myIntent.putExtra("nombreProducto", nombreProducto);
                myIntent.putExtra("productoId", productoId);
                myIntent.putExtra("localId", localId);
                myIntent.putExtra("productoPrecio", productoPrecio);
                myIntent.putExtra("productoDescuento", productoDescuento);
                startActivity(myIntent);

            }
        });
    }

    public void locales(){
        Button boton = (Button) findViewById(R.id.btnLocales);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DetalleLocales.class);

                startActivity(myIntent);
            }
        });
    }
}
