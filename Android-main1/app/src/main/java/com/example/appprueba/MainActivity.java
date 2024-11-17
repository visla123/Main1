package com.example.appprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText usrEditText;
    private EditText pwsEditText;
    private Button ingresarButton;
    private TextView relojTextView;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrEditText = findViewById(R.id.usr);
        pwsEditText = findViewById(R.id.pws);
        ingresarButton = findViewById(R.id.btnIngresar);

    }

    public void Acceder(View view) {
        String usuario = usrEditText.getText().toString();
        String pass = pwsEditText.getText().toString();

        if (usuario.equals("") && pass.equals("")) {
            Toast.makeText(this, "Ingreso Correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PantallaPrincipal.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o Contraseña incorrectas", Toast.LENGTH_SHORT).show();
        }


    }

}
