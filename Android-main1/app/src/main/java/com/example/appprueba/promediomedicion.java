package com.example.appprueba;

import static android.R.layout.*;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class promediomedicion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promediomedicion);
    }
    public void regreso(View view){
        finish();
    }
}





