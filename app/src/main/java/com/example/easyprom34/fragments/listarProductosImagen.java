package com.example.easyprom34.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.easyprom34.DetalleDato;
import com.example.easyprom34.R;
import com.example.easyprom34.adapter.ProductosAdapter;
import com.example.easyprom34.entidades.Producto;
import com.example.easyprom34.entidades.VolleySingleton;
import com.example.easyprom34.interfaces.clickProductoForDetalle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listarProductosImagen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listarProductosImagen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listarProductosImagen extends Fragment implements  ProductosAdapter.OnActivityListener,
                                                                Response.Listener<JSONObject>,
                                                                Response.ErrorListener{


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerProductos;
    public List<Producto> listaProductos;

    ProgressDialog dialog;

    // RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public listarProductosImagen() {

    }


    public static listarProductosImagen newInstance(String param1, String param2) {
        listarProductosImagen fragment = new listarProductosImagen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_listar_productos_imagen, container, false);
        recyclerProductos = (RecyclerView) vista.findViewById(R.id.idRecyclerImagenProductos);

        cargarWebService();

        return vista;
    }

    private void cargarWebService(){

        listaProductos=new ArrayList<>();


        recyclerProductos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerProductos.setHasFixedSize(true);

        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Consultando Productos...");
        dialog.show();

        String url="https://easy-prom.000webhostapp.com/webServices/listarProductosImagenUrl.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        Producto producto=null;

        JSONArray json=response.optJSONArray("productos");

        try {

            for (int i=0;i<json.length();i++){
                producto=new Producto();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                producto.setIdProducto(jsonObject.optInt("producto_id"));
                producto.setProducto_nombre(jsonObject.optString("producto_nombre"));
                producto.setProducto_categoria(jsonObject.optString("producto_categoria"));
                producto.setProducto_precio(jsonObject.optInt("producto_precio"));
                producto.setProducto_descuento(jsonObject.optInt("producto_descuento"));
                producto.setProducto_stock(jsonObject.optInt("producto_stock"));
                producto.setProducto_detalle(jsonObject.optString("producto_detalle"));
                producto.setRutaImagen(jsonObject.optString("ruta_imagen"));

                listaProductos.add(producto);
            }
            dialog.hide();
            ProductosAdapter adapter=new ProductosAdapter(listaProductos, getContext(), this){
            };
            recyclerProductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        dialog.hide();
        Log.d("ERROR: ", error.toString());

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //así mando de fragment a appcompact
    @Override
    public void onActivityClick(int position) {
        Producto producto= listaProductos.get(position);
        Intent detalles= new Intent(getContext(), DetalleDato.class);
        detalles.putExtra("selectedActivity", producto);
        startActivity(detalles);
    }

    //así envio de fragment a fragment
    /*@Override
    public void onActivityClick(int position) {
        Producto producto= listaProductos.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("producto", producto);
        Fragment detalle = new productoDetalle();
        detalle.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, detalle)
                .commit();
    }*/


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
