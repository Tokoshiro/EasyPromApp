package com.example.easyprom34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easyprom34.fragments.listarProductosImagen;
import com.example.easyprom34.interfaces.IFragments;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity implements IFragments {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //obtenemos el administrador de fragment
        FragmentManager manager = getSupportFragmentManager();

        // crear transaccion
        FragmentTransaction transaction = manager.beginTransaction();

        //creamos fragment y añadimos
        listarProductosImagen fragmentListarProductos = new listarProductosImagen();
        transaction.add(R.id.contenedor, fragmentListarProductos);

        transaction.commit();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }


        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
