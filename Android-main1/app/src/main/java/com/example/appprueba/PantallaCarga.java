package com.example.appprueba;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//librerias a utlizar
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;



public class PantallaCarga extends AppCompatActivity{


    //declaramos variable

    private TextView textView;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pantallacarga);

    //enlazamos id


    textView=findViewById(R.id.text1);
    progressBar=findViewById(R.id.progre);

    //crear y ejecutar threads

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {

                //TIEMPO DEL THREADS
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //programamos el hilo

                        progressBar.setVisibility(View.VISIBLE);
                        //ACTUALIZAR MENAJE
                        textView.setText("Gracias por su espera");
                        // redirigir al otra pantalla

                        setContentView(R.layout.activity_pantalla_principal);

                    }
                });



            }
        });
        //iniciamos el hilo
        thread.start();


    }




}

