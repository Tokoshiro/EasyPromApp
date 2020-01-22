package com.example.easyprom34;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyprom34.entidades.Producto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class DatosCanje extends AppCompatActivity {

    Producto producto;

    String  nombreProducto,  productoId, localId, productoPrecio, productoDescuento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_canje);

        //recuperamos datos

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            productoId = b.getString("productoId");
            productoPrecio = b.getString("productoPrecio");
            productoDescuento = b.getString("productoDescuento");
            localId = b.getString("localId");
            nombreProducto = b.getString("nombreProducto");
        }



        regresar();
        genererQr();

        producto = getIntent().getExtras().getParcelable("selectedActivity");
        if (producto != null) {
            loadData(producto);
        }


    }

    private void loadData(Producto producto) {
    }

    private void genererQr() {

        final String elementos=     "idProducto:"+productoId+
                                    ",producto_precio:"+productoPrecio+
                                    ",producto_descuento:"+productoDescuento+
                                    ",producto_nombre:"+nombreProducto+
                                    ",local_Id:"+localId;

        final Button generarqr = (Button) findViewById(R.id.btnGenerar);
        final ImageView imagenQr = (ImageView) findViewById(R.id.imagenqr);



        generarqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Generamos el QR

                MultiFormatWriter generarqr = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = generarqr.encode(elementos, BarcodeFormat.QR_CODE,800,800);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    imagenQr.setImageBitmap(bitmap);

                }catch (WriterException e){
                    e.printStackTrace();
                }

            }
        });
    }

    public void regresar(){
        Button regresar = (Button) findViewById(R.id.btnRegresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(regresar);
            }
        });
    }
}
