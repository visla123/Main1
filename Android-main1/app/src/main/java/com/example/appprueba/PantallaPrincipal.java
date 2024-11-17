package com.example.appprueba;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;

public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

    }

    public void mediciondiaria(View view) {
        // Crear un Intent para redirigir a MedicionDiaria
        Intent intent = new Intent(PantallaPrincipal.this, Mediciondiaria.class);
        startActivity(intent);
    }

    public void promediomedicion(View view) {
        // Crear un Intent para redirigir a promediomedicion
        Intent intent = new Intent(PantallaPrincipal.this, promediomedicion.class);
        startActivity(intent);
    }

    public void Mapas(View view) {
        // Crear un Intent para redirigir a MedicionDiaria
        Intent intent = new Intent(PantallaPrincipal.this, Mapas.class);
        startActivity(intent);
    }

    public void nosotros(View view) {
        // Crear un Intent para redirigir a MedicionDiaria
        Intent intent = new Intent(PantallaPrincipal.this, nosotros.class);
        startActivity(intent);

    }

}