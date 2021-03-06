package com.br.listadecontatos.model.dao;

// imports ANDROID API
import android.os.Build;

// imports JAVA API
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// imports Local API
import com.br.listadecontatos.model.bean.Contatos;

/**
 * Classe DAO de controle dos dados
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017.
 */

public class ContatosDAO {
    public static ContatosDAO manager = new ContatosDAO();
    private List<Contatos> lista;
    private long id = 0;

    /**
     * Constructor
     */
    private ContatosDAO() {
        lista = new ArrayList<>();
    }

    /**
     * Metodo que retorna o total de linhas da lista de contatos
     *
     * @return
     */
    public int size() { return lista.size(); }

    /**
     * Metodo que retorna a lista de contatos
     *
     * @return
     */
    public List<Contatos> all() { return lista; }

    /**
     * Metodo que adiciona um contato na lista
     *
     * @param obj
     * @throws ExceptionDAO
     */
    public void add(Contatos obj) throws ExceptionDAO {
        Contatos pesquisa = null;
        for(Contatos item : lista) {
            if (item.getNome().equals(obj.getNome())) {
                pesquisa = item;
                break;
            }
        }

        if (pesquisa != null) {
            throw new ExceptionDAO("Contato duplicado para o nome: " + obj.getNome());
        }

        // incrementa o ID
        id++;

        obj.setId(id);
        lista.add(obj);
    }

    /**
     * Metodo que pega um contato da lista
     *
     * @param id
     * @return
     * @throws ExceptionDAO
     */
    public Contatos get(long id) throws ExceptionDAO {
        Contatos pesquisa = null;
        for(Contatos item : lista) {
            if (item.getId() == id) {
                pesquisa = item;
                break;
            }
        }
        if (pesquisa == null) {
            throw new ExceptionDAO("Contato não encontrado para o id: " + id);
        }

        // retorna os dados achados
        return  pesquisa;
    }

    /**
     * Metodo que atualiza um contato na lista
     *
     * @param obj
     * @throws ExceptionDAO
     */
    public void put(Contatos obj) throws ExceptionDAO {
        Contatos pesquisa = get(obj.getId());
                 pesquisa.setNome(obj.getNome());
                 pesquisa.setEmail(obj.getEmail());
                 pesquisa.setTelefone(obj.getTelefone());
                 pesquisa.setDel(obj.isDel());
    }

    /**
     * Metodo para remover um Contato
     *
     * @param id
     * @throws ExceptionDAO
     */
    public void del(long id) throws ExceptionDAO {
        Contatos pesquisa = get(id);
        lista.remove(pesquisa);
    }

    /**
     * Metodo que ordena a lista
     *
     * @throws ExceptionDAO
     */
    public void sort() throws ExceptionDAO {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(lista, new Comparator<Contatos>() {
                        @Override
                        public int compare(Contatos o1, Contatos o2) {
                            return o1.getNome().compareToIgnoreCase(o2.getNome());
                        }
                    });
        }
        else {
            throw new ExceptionDAO("Erro ao ordernar a lista!");
        }
    }
}