package com.example.easyprom34;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignWithMail extends AppCompatActivity {

    private ProgressDialog progressDialog;

    //Declaramos un objeto firebase
    private FirebaseAuth firebaseAuth;

    private  FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_with_mail);


        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        Button login = (Button) findViewById(R.id.btnIngresar);
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){

        final Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);

        final EditText user = (EditText) findViewById(R.id.txtUsuario);
        EditText pass = (EditText) findViewById(R.id.txtContraseña);

        final String txtEmail = user.getText().toString();
        String txtPassword = pass.getText().toString();

        //Validamos
        if (TextUtils.isEmpty(txtEmail)){
            Toast.makeText(this, "Debe ingresar un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtPassword)){
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.show();

        //Logear usuario
        firebaseAuth.signInWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //registramos
                            Toast.makeText(SignWithMail.this, "Bienvenido " + txtEmail, Toast.LENGTH_LONG).show();
                            startActivity(myIntent);
                        }else  {
                                Toast.makeText(SignWithMail.this, "Datos erroneos o usuario no creado :(", Toast.LENGTH_LONG).show();
                            }
                        
                        progressDialog.dismiss();
                    }
                });

    }
}
