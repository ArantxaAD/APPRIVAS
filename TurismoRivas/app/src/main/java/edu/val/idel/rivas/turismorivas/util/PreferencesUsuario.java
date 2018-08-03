package edu.val.idel.rivas.turismorivas.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * @version 1.0.0
 * @autor: Victor Tribaldos
 *
 * Clase utilizada para guardar las preferencias del usuario
 * Por el momento solo guarda las preferencias del checkbox
 */
public class PreferencesUsuario {


    public static final String NOMBRE_FICHERO = "usuario_preferences";
    public static final String CLAVE_SALTAR_INTRO = "saltar_intro";


    //metodo que guarda las preferencias de la checkbox
    public static void guardarPrefSaltarIntro(boolean activo ,Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CLAVE_SALTAR_INTRO, activo);
        editor.commit();
    }

    //metodo que recupera las preferencias de la checkbox
    public static boolean recogerPrefSaltarIntro(Context context){

        boolean valor = false;

            SharedPreferences sharedPreferences = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
            valor = sharedPreferences.getBoolean(CLAVE_SALTAR_INTRO,false);

        return valor;
    }

}
