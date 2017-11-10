package com.br.listadecontatos.view;

// imports ANDROID API
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

// imports Local API
import com.br.listadecontatos.R;
import com.br.listadecontatos.model.dao.ContatosDAO;
import com.br.listadecontatos.model.dao.ExceptionDAO;
import com.br.listadecontatos.utils.UtilsHelper;
import com.br.listadecontatos.view.adapter.ListaContatosAdapter;

/**
 * Classe de Tela Principal
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
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
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    /**
     * Metodo que redireciona para a tela de Cadastro de Contato
     *
     * @param id
     */
    private void goContatos(long id) {
        Intent it = new Intent(getBaseContext(), ContatoActivity.class);
               it.putExtra("idContato", id);
        startActivityForResult(it, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // redireciona a tela de contatos passando o ID selecionado na lista
        goContatos(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_item1:
                goContatos(0);
                break;
            case R.id.menu_item2:
                ordernar();
                break;
        }

        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        dialogo.setTitle(R.string.d_titulo);
        dialogo.setMessage(R.string.d_mensagem);
        dialogo.setPositiveButton(R.string.d_btnExcluir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ContatosDAO.manager.del(id);
                    adapter.notifyDataSetChanged();
                    UtilsHelper.msg(getBaseContext(), "ID " + id + " foi excluído com sucesso!");
                }
                catch (ExceptionDAO exceptionDAO) {}
            }
        });
        dialogo.setNegativeButton(R.string.d_btnCancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogo.create();
        dialogo.show();

        // retorna ok
        return true;
    }

    /**
     * Metodo para ordernar o ListView
     */
    private void ordernar() {
        try {
            ContatosDAO.manager.sort();
            adapter.notifyDataSetChanged();
        }
        catch (ExceptionDAO ex) {
            ex.printStackTrace();
        }

    }
}
