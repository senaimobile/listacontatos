package com.br.listadecontatos.view;

// imports ANDROID API
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

// imports Local API
import com.br.listadecontatos.R;
import com.br.listadecontatos.view.adapter.ListaContatosAdapter;

/**
 * Classe de Tela Principal
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017
 */
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListaContatosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carrega o layout padrão
        setContentView(R.layout.activity_main);

        // instancia o adapter
        adapter = new ListaContatosAdapter(getBaseContext());

        // pega o ListView
        listView = (ListView) findViewById(R.id.listaContato);
        listView.setAdapter(adapter);
    }

    /**
     * Metodo executado no botão Contatos
     *
     * @param view
     */
    public void onContatosClick(View view) {
        Intent it = new Intent(getBaseContext(), ContatoActivity.class);
        it.putExtra("idContato", 0);
        startActivityForResult(it, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }
}
