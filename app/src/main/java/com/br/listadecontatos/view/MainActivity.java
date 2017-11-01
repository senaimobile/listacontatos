package com.br.listadecontatos.view;

// imports ANDROID API
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// imports Local API
import com.br.listadecontatos.R;

/**
 * Classe de Tela Principal
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carrega o layout padrão
        setContentView(R.layout.activity_main);
    }

    /**
     * Metodo executado no botão Contatos
     *
     * @param view
     */
    public void onContatosClick(View view) {
        Intent it = new Intent(getBaseContext(), ContatoActivity.class);
        startActivity(it);

    }
}
