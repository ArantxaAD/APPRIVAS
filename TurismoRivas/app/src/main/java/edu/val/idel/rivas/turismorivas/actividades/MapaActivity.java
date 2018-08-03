package edu.val.idel.rivas.turismorivas.actividades;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import edu.val.idel.rivas.turismorivas.R;
import edu.val.idel.rivas.turismorivas.modelo.AuditorioMiguelRios;
import edu.val.idel.rivas.turismorivas.modelo.AytoRivas;
import edu.val.idel.rivas.turismorivas.modelo.CastilloDeRivas;
import edu.val.idel.rivas.turismorivas.modelo.CuevasDeLaGuerraCivil;
import edu.val.idel.rivas.turismorivas.modelo.ErmitaDelCristo;
import edu.val.idel.rivas.turismorivas.modelo.FincaElPorcal;
import edu.val.idel.rivas.turismorivas.modelo.LagunaDelCampillo;
import edu.val.idel.rivas.turismorivas.modelo.MonumentoMartiresVaciamadrid;
import edu.val.idel.rivas.turismorivas.modelo.ParqueLineal;
import edu.val.idel.rivas.turismorivas.modelo.ParroquiaSanMarcos;
import edu.val.idel.rivas.turismorivas.modelo.ParroquiaSantaMonica;
import edu.val.idel.rivas.turismorivas.modelo.Piruli;
import edu.val.idel.rivas.turismorivas.modelo.PuenteDelTrenDeArganda;
import edu.val.idel.rivas.turismorivas.modelo.PuentedeArganda;
import edu.val.idel.rivas.turismorivas.modelo.PuntoDeInteres;
import edu.val.idel.rivas.turismorivas.modelo.Trincheras;
import edu.val.idel.rivas.turismorivas.modelo.YacimientoArqueologicoMiralrio;
import edu.val.idel.rivas.turismorivas.util.Constantes;

/**
 *
 * @version 1.0.0
 * @autor: Gonzalo Cuadrado
 *
 * Clase utilizada utilizada para mostrar el mapa y dibujar los marcadores de cada punto de interés
 */
public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback{
    protected GoogleMap mMap;
    //Se declara como constante las coordenadas que usará Google Maps para centrar el mapa al ejecutar la app
    protected final static LatLng POSICION_INICIO =new LatLng(40.351906,-3.535733);
    //Se declara como float el número que representa al nivel de zoom que se aplicará a las coordenadas prefijadas
    protected final static float NIVEL_ZOOM=13;

    private static List<PuntoDeInteres> lpi;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mapa);

            //Encuentra el Fragment correspondiente al mapa en el XML asociado a esta clase
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        catch (Throwable t){
            Log.e("MIAPP","Error en maps", t);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_item=item.getItemId();
        switch (id_item){
            case R.id.creditos:
                startActivity(new Intent(this,CreditosActivity.class));
                break;

            case R.id.ajustes:
                startActivity(new Intent(this,AjustesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public static PuntoDeInteres getPuntoDeInteres (int i)
    {
        PuntoDeInteres pi = null;

            pi = lpi.get(i);

        return pi;
    }
    //Método para dibujar los marcadores con los puntos de interés en el mapa. Recibe de entrada una lista de
    //Puntos de interés y un objeto GoogleMap que representa a nuestro mapa
    public static void marcarpuntos (List<PuntoDeInteres> puntoDeInteres, GoogleMap googleMap) {
        //Se utiliza un bucle for para recorrer el ArrayList que recibe de entrada
        for (int i = 0; i < puntoDeInteres.size(); i++) {
            //Se crea un objeto LatLng con las coordenadas declaradas en cada una de las clases (puntos de interés)
            LatLng posicion = new LatLng(puntoDeInteres.get(i).getLatitud(), puntoDeInteres.get(i).getLongitud());
            //Se establece que en cada iteración dibuje un marcador en el mapa con los datos correspondientes
            // a cada posición del ArrayList


            Marker m = googleMap.addMarker(new MarkerOptions()
                    //El título del marcador
                    .title(puntoDeInteres.get(i).getNombre())
                    //Las coordenadas en las que se dibujará
                    .position(posicion));
            m.setTag(i);

                    //Una pequeña descripción del marcador
                    //.snippet(puntoDeInteres.get(i).getInfo_basica()));
        }
    }

    //Método con las instrucciones para fijar el mapa en Rivas-Vaciamadrid al abrir la app
    public static void centrar_mapa (GoogleMap googleMap){
        //Cambia la vista del mapa a satélite
        googleMap.setMapType(googleMap.MAP_TYPE_SATELLITE);
        //Mueve el mapa a las coordenadas de Rivas, con el nivel de zoom deseado
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(POSICION_INICIO, NIVEL_ZOOM));

    }



    //Este método es el que permite llevar a cabo todas las interacciones con el mapa creado
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Se llama al método que centra el mapa en Rivas-Vaciamadrid
        centrar_mapa(googleMap);
        //Se crea un ArrayList de objetos PuntoDeInteres
        lpi = new ArrayList<PuntoDeInteres>();
        //Se crea un objeto LagunaDelCampillo (hereda de PuntoDeInteres, por lo que también es un objeto de esta clase)
        //Se añaden ambos objetos al ArrayList de PuntoDeInteres

        lpi.add(new AuditorioMiguelRios());
        lpi.add(new AytoRivas());
        lpi.add(new CastilloDeRivas());
        lpi.add(new CuevasDeLaGuerraCivil());
        lpi.add(new ErmitaDelCristo());
        lpi.add(new FincaElPorcal());
        lpi.add(new LagunaDelCampillo());
        lpi.add(new MonumentoMartiresVaciamadrid());
        lpi.add(new ParqueLineal());
        lpi.add(new ParroquiaSanMarcos());
        lpi.add(new ParroquiaSantaMonica());
        lpi.add(new Piruli());
        lpi.add(new PuentedeArganda());
        lpi.add(new PuenteDelTrenDeArganda());
        lpi.add(new Trincheras());
        lpi.add(new YacimientoArqueologicoMiralrio());
        //Se llama al método que dibuja los marcadores y se le pasa el ArrayList
        // y el objeto GoogleMap que representa a nuestro mapa)
        marcarpuntos(lpi,googleMap);

        //Se configura la acción a realizar cuando se pulsar un marker
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
            int npi = (int) marker.getTag();
            Log.d(Constantes.TAG_APP, "NPI seleccionado = " + npi);
            Intent i = new Intent(getBaseContext(), PuntoDeInteresActivity.class);
            i.putExtra(Constantes.CLAVE_INTENT_PI, npi);
            startActivity(i);


            }
        });

    }
}