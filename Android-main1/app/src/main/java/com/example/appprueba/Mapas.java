package com.example.appprueba;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class Mapas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapas);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        MapView mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        //1° marcador
        double ipSantoTomasLatitud = -33.3261339; // Latitud del IP Santo Tomás.
        double ipSantoTomasLongitud = -70.7512592; // Longitud del IP Santo Tomás.


        GeoPoint IPsantoTomasPoint = new GeoPoint(ipSantoTomasLatitud, ipSantoTomasLongitud);

        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(IPsantoTomasPoint);

       //confiraciones del marcador
        Marker marcadorSantoTomas = new Marker(mapView);
        marcadorSantoTomas.setPosition(IPsantoTomasPoint);
        marcadorSantoTomas.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorSantoTomas.setTitle("Central");
        marcadorSantoTomas.setSnippet("Estacion de solucion de reclamo");
        mapView.getOverlays().add(marcadorSantoTomas);



        // Coordenadas Parque
        double parqueLatitud = -33.3192292; // Latitud del Parque.
        double parqueLongitud = -71.7326186; // Longitud del Parque.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint parquePoint = new GeoPoint(parqueLatitud, parqueLongitud);

        Marker marcadorParque = new Marker(mapView);
        marcadorParque.setPosition(parquePoint);
        marcadorParque.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorParque.setTitle("Oficina de Reclamos");
        marcadorParque.setSnippet("Abierto 24/7");
        mapView.getOverlays().add(marcadorParque);

        // Crear una línea que conecte los 2 Marcadores
        Polyline linea = new Polyline();
        linea.addPoint(IPsantoTomasPoint);
        linea.addPoint(parquePoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        mapView.getOverlayManager().add(linea);


        Spinner mapTypeSpinner = findViewById(R.id.mapitas);

        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);


        //listener para detectar un cambio de opcion en el espiner

        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){



                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK);
                        break;

                    //mapa transportes
                    case 1:
                        mapView.setTileSource(new XYTileSource(
                                "PublicTransport",
                                0,18,256,".png",new String[]{
                                "https://tile.memomaps.de/tilegen/"
                        }));
                        break;


                }

            }
            // no se hace nada cuando no tiene elemento
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void regreso(View view){
        finish();
    }
}