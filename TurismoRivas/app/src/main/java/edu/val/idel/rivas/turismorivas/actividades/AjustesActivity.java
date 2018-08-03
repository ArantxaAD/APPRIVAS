package edu.val.idel.rivas.turismorivas.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import edu.val.idel.rivas.turismorivas.R;
import edu.val.idel.rivas.turismorivas.util.PreferencesUsuario;

public class AjustesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        marcar_casilla_inicio();
    }
    public void casilla_marcada (View v){
        CheckBox casilla=(CheckBox)v;
        PreferencesUsuario.guardarPrefSaltarIntro(casilla.isChecked(),this);
    }
    public void marcar_casilla_inicio (){
        CheckBox casilla=findViewById(R.id.mostrar_inicio);
        if(PreferencesUsuario.recogerPrefSaltarIntro(this)){
            casilla.setChecked(true);
        }
        else {
            casilla.setChecked(false);
        }
    }
}
