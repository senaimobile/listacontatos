package com.br.listadecontatos.utils;

// imports ANDROID API
import android.content.Context;
import android.widget.Toast;

/**
 * Classe Helper de métodos diversos
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 01/11/2017.
 */

public class UtilsHelper {
    /**
     * Metodo que mostra erro na tela
     *
     * @param msg
     */
    public static void msg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}