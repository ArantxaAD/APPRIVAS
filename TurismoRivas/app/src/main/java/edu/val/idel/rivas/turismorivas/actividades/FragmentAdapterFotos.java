package edu.val.idel.rivas.turismorivas.actividades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.val.idel.rivas.turismorivas.R;
import edu.val.idel.rivas.turismorivas.modelo.PuntoDeInteres;

/**
 * Created by vale on 2/08/18.
 */

public class FragmentAdapterFotos extends FragmentStatePagerAdapter {

    public static int[] COLECCION_SICILIA = {R.drawable.casaconsistorial_2, R.drawable.casaconsistorial_3, R.drawable.casaconsistorial_4};

    private int [] array_fotos_actual ;
    private int n_foto_actual;

    public FragmentAdapterFotos (FragmentManager fm)
    {

        super (fm);
    }

    public void setPuntoDeInteres(PuntoDeInteres pi)
    {
        array_fotos_actual = pi.getFotos();
        n_foto_actual = 0;

    }
    @Override
    public Fragment getItem(int i) {
        FotoDetailFragment fragment = null;
        Bundle bundle = null;

        fragment = new FotoDetailFragment();
        bundle = new Bundle();

        bundle.putInt("N_FOTO", array_fotos_actual[i]);//al fragment le damos un tag, que valdrá a la postre para la id del la foto en curso
        fragment.setArguments(bundle);


        return  fragment;
    }

    @Override
    public int getCount() {
        int tamanio = 0;

        tamanio = array_fotos_actual.length;

        return tamanio;
    }
}
