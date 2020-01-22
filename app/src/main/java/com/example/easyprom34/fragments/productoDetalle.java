package com.example.easyprom34.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.easyprom34.R;
import com.example.easyprom34.entidades.Producto;
import com.example.easyprom34.entidades.VolleySingleton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link productoDetalle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link productoDetalle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class productoDetalle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<Producto> productos;
    Context context;

    private Producto producto;
    private TextView tituloDetalle;
    private TextView descripcionDetalle;
    private String rutaImagen;
    private Button verLocales, reclamar;
    private ImageView imagenProducto;



    public productoDetalle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment productoDetalle.
     */
    // TODO: Rename and change types and number of parameters
    public static productoDetalle newInstance(String param1, String param2) {
        productoDetalle fragment = new productoDetalle();
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

        View vista = inflater.inflate(R.layout.fragment_producto_detalle, container, false);

        Bundle bundle= this.getArguments();
        if (bundle!= null){
            producto = bundle.getParcelable("producto");
            mapearVistas(vista);
            mostrarDatos(producto);
        }
        return vista;
    }

    private void mapearVistas(View vista) {

        tituloDetalle = vista.findViewById(R.id.tvTituloDetalle2);
        descripcionDetalle = vista.findViewById(R.id.tvDescripcionDetalle2);
        verLocales = vista.findViewById(R.id.btnLocales2);
        reclamar = vista.findViewById(R.id.btnReclamar2);
        imagenProducto = vista.findViewById(R.id.imgFotoDetalle2);
    }

    private void mostrarDatos(Producto producto){
        tituloDetalle.setText(producto.getProducto_nombre());
        descripcionDetalle.setText(producto.getProducto_detalle());

        rutaImagen = producto.getRutaImagen();

        if (rutaImagen == "null"){

            imagenProducto.setImageResource(R.drawable.easy);

        }else {

            String ip = "https://easy-prom.000webhostapp.com";

            String urlImagen = ip + "/fotosProductos/" + rutaImagen;
            urlImagen = urlImagen.replace(" ", "%20");

            ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imagenProducto.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Esta promo no tiene imagen :(", Toast.LENGTH_SHORT).show();
                }
            });
            VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
        }
    }






    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
