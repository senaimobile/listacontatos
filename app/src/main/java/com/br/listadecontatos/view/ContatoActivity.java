package com.br.listadecontatos.view;

// imports ANDROID API
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// imports Local API
import com.br.listadecontatos.R;
import com.br.listadecontatos.model.bean.Contatos;
import com.br.listadecontatos.model.dao.ContatosDAO;
import com.br.listadecontatos.model.dao.ExceptionDAO;

/**
 * Classe de Tela de Contato (Inclusão/Edição)
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017
 */
public class ContatoActivity extends AppCompatActivity {
    private EditText txtNome, txtEmail, txtTelefone;
    private Contatos oldContatos;
    private int idContato = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carrega o Layout padrão
        setContentView(R.layout.activity_contato);

        // pega o ID passado
        idContato = getIntent().getIntExtra("idContato", 0);

        // pega os campos da tela
        txtNome     = (EditText) findViewById(R.id.txtNome);
        txtEmail    = (EditText) findViewById(R.id.txtEmail);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);

        try {
            // pega o contato selecionado na Lista de Contatos
            oldContatos = ContatosDAO.manager.get(idContato);

            // seta os dados do contato nos campos
            txtNome.setText(oldContatos.getNome());
            txtEmail.setText(oldContatos.getEmail());
            txtTelefone.setText(oldContatos.getTelefone());
        }
        catch (ExceptionDAO e) { msg(e.getMessage()); }
    }

    /**
     * Metodo executado no botão Gravar
     *
     * @param view
     */
    public void onSaveClick(View view) {
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
        }
        catch (ExceptionDAO e) { msg(e.getMessage()); }

        // mostra a mernsagem de sucesso
        msg("Contato gravado com sucesso!");

        // finaliza
        finish();
    }

    /**
     * Metodo que mostra erro na tela
     *
     * @param msg
     */
    private void msg(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }
}