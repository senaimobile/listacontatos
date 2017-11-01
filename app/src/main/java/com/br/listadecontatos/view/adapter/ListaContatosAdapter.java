package com.br.listadecontatos.view.adapter;

// imports ANDROID API
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// imports Local API
import com.br.listadecontatos.model.bean.Contatos;
import com.br.listadecontatos.model.dao.ContatosDAO;
import com.br.listadecontatos.R;

// imports JAVA API
import java.util.List;

/**
 * Classe de adapter para carregar os contatos na ListView
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 01/11/2017.
 */
public class ListaContatosAdapter extends BaseAdapter {
    private List<Contatos> lista;
    private LayoutInflater layout;

    /**
     * Constructor
     */
    public ListaContatosAdapter(Context context) {
        lista  = ContatosDAO.manager.all();
        layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return lista.size(); }

    @Override
    public Object getItem(int position) {
        return (Contatos) lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // pega a posicao
        Contatos c = lista.get(position);

        // verifica a View
        if (convertView == null) {
            convertView = layout.inflate(R.layout.item_list, null, true);
        }

        // pega os campos
        TextView nome  = (TextView) convertView.findViewById(R.id.lNome);
        TextView dados = (TextView) convertView.findViewById(R.id.lDados);

        nome.setText(c.getNome());
        dados.setText(c.getEmail() + " - " + c.getTelefone());

        return convertView;
    }
    
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}