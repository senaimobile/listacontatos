package com.br.listadecontatos.view;

// imports ANDROID API
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// imports Local API
import com.br.listadecontatos.R;
import com.br.listadecontatos.model.bean.Contatos;
import com.br.listadecontatos.model.dao.ContatosDAO;
import com.br.listadecontatos.model.dao.ExceptionDAO;
import com.br.listadecontatos.utils.UtilsHelper;

/**
 * Classe de Tela de Contato (Inclusão/Edição)
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017
 */
public class ContatoActivity extends AppCompatActivity {
    private EditText txtNome, txtEmail, txtTelefone;
    private long idContato = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carrega o Layout padrão
        setContentView(R.layout.activity_contato);

        // pega o ID passado
        idContato = getIntent().getLongExtra("idContato", 0);

        // pega a ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // pega o titulo
            String title = (idContato == 0) ? "Incluir" : "Editar";

            // seta informacoes no ActionBar
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Cadastro de Contato");
            actionBar.setSubtitle(title);
        }

        // pega os campos da tela
        txtNome     = (EditText) findViewById(R.id.txtNome);
        txtEmail    = (EditText) findViewById(R.id.txtEmail);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);

        if (idContato > 0) {
            try {
                // pega o contato selecionado na Lista de Contatos
                Contatos oldContatos = ContatosDAO.manager.get(idContato);

                // seta os dados do contato nos campos
                txtNome.setText(oldContatos.getNome());
                txtEmail.setText(oldContatos.getEmail());
                txtTelefone.setText(oldContatos.getTelefone());
            }
            catch (ExceptionDAO e) {
                UtilsHelper.msg(getBaseContext(), e.getMessage());
                idContato = 0;
            }
        }
    }

    /**
     * Metodo executado no botão Gravar
     */
    public void onSaveClick() {
        // pega os dados digitados
        String nome     = txtNome.getEditableText().toString();
        String email    = txtEmail.getEditableText().toString();
        String telefone = txtTelefone.getEditableText().toString();

        // monta os dados do Contato
        Contatos contatos = new Contatos();
        contatos.setId(idContato);
        contatos.setNome(nome);
        contatos.setEmail(email);
        contatos.setTelefone(telefone);

        try {
            // verifica
            if (idContato == 0) { ContatosDAO.manager.add(contatos); }
            else                { ContatosDAO.manager.put(contatos); }

            // mostra a mernsagem de sucesso
            UtilsHelper.msg(getBaseContext(), "Contato gravado com sucesso!");

            setResult(Activity.RESULT_OK);
        }
        catch (ExceptionDAO e) {
            UtilsHelper.msg(getBaseContext(), e.getMessage());
            setResult(Activity.RESULT_CANCELED);
        }

        // finaliza
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contato_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            case R.id.action_save:
                onSaveClick();
                break;
        }

        return true;
    }
}