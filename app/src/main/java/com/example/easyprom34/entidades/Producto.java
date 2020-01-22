package com.example.easyprom34.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

public class Producto implements Parcelable {

    private Integer idProducto, producto_precio, producto_descuento, producto_stock, local_Id;
    private String producto_nombre, producto_categoria, producto_detalle, dato, rutaImagen;
    private Bitmap producto_imagen;

    public Producto(){

    }

    protected Producto(Parcel in) {
        if (in.readByte() == 0) {
            idProducto = null;
        } else {
            idProducto = in.readInt();
        }
        if (in.readByte() == 0) {
            producto_precio = null;
        } else {
            producto_precio = in.readInt();
        }
        if (in.readByte() == 0) {
            producto_descuento = null;
        } else {
            producto_descuento = in.readInt();
        }
        if (in.readByte() == 0) {
            producto_stock = null;
        } else {
            producto_stock = in.readInt();
        }
        if (in.readByte() == 0) {
            local_Id = null;
        } else {
            local_Id = in.readInt();
        }
        producto_nombre = in.readString();
        producto_categoria = in.readString();
        producto_detalle = in.readString();
        dato = in.readString();
        rutaImagen = in.readString();
        producto_imagen = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getProducto_precio() {
        return producto_precio;
    }

    public void setProducto_precio(Integer producto_precio) {
        this.producto_precio = producto_precio;
    }

    public Integer getProducto_descuento() {
        return producto_descuento;
    }

    public void setProducto_descuento(Integer producto_descuento) {
        this.producto_descuento = producto_descuento;
    }

    public Integer getProducto_stock() {
        return producto_stock;
    }

    public void setProducto_stock(Integer producto_stock) {
        this.producto_stock = producto_stock;
    }

    public Integer getLocal_Id() {
        return local_Id;
    }

    public void setLocal_Id(Integer local_Id) {
        this.local_Id = local_Id;
    }

    public String getProducto_nombre() {
        return producto_nombre;
    }

    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    public String getProducto_categoria() {
        return producto_categoria;
    }

    public void setProducto_categoria(String producto_categoria) {
        this.producto_categoria = producto_categoria;
    }

    public String getProducto_detalle() {
        return producto_detalle;
    }

    public void setProducto_detalle(String producto_detalle) {
        this.producto_detalle = producto_detalle;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode= Base64.decode(dato, Base64.DEFAULT);
            //this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.producto_imagen= Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Bitmap getProducto_imagen() {
        return producto_imagen;
    }

    public void setProducto_imagen(Bitmap producto_imagen) {
        this.producto_imagen = producto_imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idProducto == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idProducto);
        }
        if (producto_precio == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(producto_precio);
        }
        if (producto_descuento == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(producto_descuento);
        }
        if (producto_stock == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(producto_stock);
        }
        if (local_Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(local_Id);
        }
        dest.writeString(producto_nombre);
        dest.writeString(producto_categoria);
        dest.writeString(producto_detalle);
        dest.writeString(dato);
        dest.writeString(rutaImagen);
        dest.writeParcelable(producto_imagen, flags);
    }

}
