package com.example.appprueba;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

    public class nosotros extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.nosotros);  // Cambia el layout si es necesario

            // Configuración de las imágenes con enlaces
            ImageView imageView1 = findViewById(R.id.imageView1);
            ImageView imageView2 = findViewById(R.id.imageView2);

            // URL de destino
            String url1 = "https://www.instagram.com";
            String url2 = "https://www.tiktok.com";

            // Configura un listener para abrir la URL cuando la imagen es tocada
            imageView1.setOnClickListener(v -> {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                startActivity(intent1);
            });

            imageView2.setOnClickListener(v -> {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                startActivity(intent2);
            });

            // Configuración del VideoView
            VideoView videoView = findViewById(R.id.videoView);

            // Establece la ruta del video en 'raw'
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.campo);

            // Configura el VideoView
            videoView.setVideoURI(videoUri);

            // Configurar el listener para reiniciar el video al finalizar
            videoView.setOnCompletionListener(mp -> videoView.start());

            // Inicia la reproducción del video
            videoView.start();
        }
    }