package com.example.appprueba;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class Mediciondiaria extends AppCompatActivity {

    //declaro variables
    Spinner spSpinner;
    String[] comunas = new String[]{"Puente Alto", "Macul", "San Miguel", "Lampa", "La Florida"};
    EditText edid,edhora, edTemp, edhum;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediciondiaria);

        //defino campos de formularios
        edid =(EditText) findViewById(R.id.edid);
        edhora = (EditText) findViewById(R.id.edhora);
        edTemp = (EditText) findViewById(R.id.edTemp);
        edhum = (EditText) findViewById(R.id.edhum);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comunas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();

    }

    public void CargarLista() {
        DataHelper dh = new DataHelper(this, "medicion.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select id ,hora, temperatura, humedad, comuna from medicion", null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst()) {
            int i = 0;
            do {
                String linea = "||" + c.getInt(0) + "||" + c.getString(1)
                        + "||" + c.getString(2) + "||" + c.getString(3) + "||" + c.getString(4) + "||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
        }
        c.close();  // Cerrar el cursor
        bd.close(); // Cerrar la base de datos
    }

    public void onClickAgregar(View view) {
        if (edid.getText().toString().isEmpty() || edhora.getText().toString().isEmpty() ||
                edTemp.getText().toString().isEmpty() || edhum.getText().toString().isEmpty() ||
                spSpinner.getSelectedItem() == null || spSpinner.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        DataHelper dh = new DataHelper(this, "medicion.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();


        reg.put("id", edid.getText().toString());
        reg.put("hora", edhora.getText().toString());
        reg.put("temperatura", edTemp.getText().toString());
        reg.put("humedad", edhum.getText().toString());
        reg.put("comuna", spSpinner.getSelectedItem().toString());

        long resp = bd.insert("medicion", null, reg);
        bd.close();

        if (resp == -1) {
            Toast.makeText(this, "No se puede ingresar su registro", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Registro Agregado", Toast.LENGTH_LONG).show();
        }

        CargarLista();
        limpiar();
    }

    //Metodo para limpiar los campos de texto
    public void limpiar() {
        edid.setText("");
        edhora.setText("");
        edTemp.setText("");
        edhum.setText("");
    }

    //Metodo para modificar un campo
    public void onClickModificar(View view) {
        if (edid.getText().toString().isEmpty() || edhora.getText().toString().isEmpty() ||
                edTemp.getText().toString().isEmpty() || edhum.getText().toString().isEmpty()||spSpinner.getSelectedItem().toString().isEmpty())
        {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }
        //Conexion a la BDD para manipular los registros
        DataHelper dh = new DataHelper(this, "medicion.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("id", edid.getText().toString());
        reg.put("hora", edhora.getText().toString());
        reg.put("temperatura", edTemp.getText().toString());
        reg.put("humedad", edhum.getText().toString());
        reg.put("comuna",  spSpinner.getSelectedItem().toString());
        //Actualizo el registro de la BBD por el id
        long respuesta = bd.update("medicion", reg, "id=?", new String[]{edid.getText().toString()});
        bd.close();
        //Envio la respuesta al usuario
        if (respuesta == 0) { // No se modificaron filas
            Toast.makeText(this, "Dato No Modificado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }

    //Metodo para Eliminar un Registro
    public void onClickEliminar(View view) {
        if (edid.getText().toString().isEmpty() || edhora.getText().toString().isEmpty() ||
                edTemp.getText().toString().isEmpty() || edhum.getText().toString().isEmpty()||spSpinner.getSelectedItem().toString().isEmpty())
        {
            Toast.makeText(this, "Por favor, igrese registro a eliminar", Toast.LENGTH_LONG).show();
            return;
        }
        //Conecto la BDD para eliminar un registro de t
        DataHelper dh = new DataHelper(this, "medicion.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //Ingreso el rut del registro a modificar
        String idEliminar = edid.getText().toString();
        //Query para eliminar el registro
        long respuesta = bd.delete("medicion", "id=?", new String[]{idEliminar});
        bd.close();
        //Verifico que el registro se elimine
        if (respuesta == 0) { // No se eliminaron filas
            Toast.makeText(this, "Dato No Eliminado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Dato Eliminado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();


    }

    public void regreso(View view) {
        finish();
    }

}