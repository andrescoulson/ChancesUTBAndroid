package com.example.laboratorio2.huaweichance;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListChanceActivity extends Activity {
    ListView List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chance);

        ArrayList<Lista_Entrada> datos = new ArrayList<Lista_Entrada>();

        datos.add(new Lista_Entrada(R.drawable.ic_launcher,"Lorem ipsum","Lorem ipsum,Lorem ipsum,Lorem ipsum,Lorem ipsum"));
        datos.add(new Lista_Entrada(R.drawable.ic_launcher,"Lorem ipsum2","Lorem ipsumLorem ipsumLorem ipsumLorem ipsum"));
        datos.add(new Lista_Entrada(R.drawable.ic_launcher,"Lorem ipsum","Lorem ipsumLorem ipsumLorem ipsum"));

        List = (ListView)this.findViewById(R.id.listView_listado);
            List.setAdapter(new List_Adapter(this,R.layout.entrada,datos) {
                @Override
                public void onEntrada(Object entrada, View view) {
                    TextView Text_Top_entrada = (TextView)view.findViewById(R.id.textView_superior);
                    Text_Top_entrada.setText(((Lista_Entrada)entrada).get_TextoEncima());

                    TextView Text_Below = (TextView)view.findViewById(R.id.textView_inferior);
                    Text_Below.setText(((Lista_Entrada)entrada).get_TextoDebajo());

                    ImageView Imagen = (ImageView)view.findViewById(R.id.imageView_image);
                    Imagen.setImageResource(((Lista_Entrada)entrada).get_IdImagen());
                }
            });

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_Entrada Seleccion = (Lista_Entrada)pariente.getItemAtPosition(posicion);

                CharSequence text = "Seleccionado: "+ Seleccion.get_TextoDebajo();
                Toast toast = Toast.makeText(ListChanceActivity.this,text,Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_chance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
