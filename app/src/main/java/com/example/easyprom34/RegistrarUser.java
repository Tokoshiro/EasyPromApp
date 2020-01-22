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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegistrarUser extends AppCompatActivity {

    //Definimos las vistas
    private EditText txtEmail, txtPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;

    //Declaramos un objeto firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_user);

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        txtEmail = (EditText) findViewById(R.id.txtEmailR);
        txtPassword = (EditText) findViewById(R.id.txtPassR);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        progressDialog = new ProgressDialog(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar(){
        final Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);

        //obtenemos datos
        String email = txtEmail.getText().toString().trim();
        String pass = txtPassword.getText().toString().trim();

        //Validamos
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Debe ingresar un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Registrando usuario

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //Checkeando si está completo
                if(task.isSuccessful()){
                    //registramos
                    Toast.makeText(RegistrarUser.this, "Te registraste con el email " + txtEmail.getText(), Toast.LENGTH_LONG).show();
                    startActivity(myIntent);
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//Si se presenta una colisión
                        Toast.makeText(RegistrarUser.this, "Ya hay alguien registrado con este correo :(", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistrarUser.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }
        });
    }
}
