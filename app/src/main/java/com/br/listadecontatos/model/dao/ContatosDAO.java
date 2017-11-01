package com.br.listadecontatos.model.dao;

import com.br.listadecontatos.model.bean.Contatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO de controle dos dados
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017.
 */

public class ContatosDAO {
    public static ContatosDAO manager = new ContatosDAO();
    private List<Contatos> lista;
    private int id = 0;

    /**
     * Constructor
     */
    private ContatosDAO() {
        lista = new ArrayList<>();
    }

    public int size() { return lista.size(); }

    public void add(Contatos obj) throws DaoException {
        Contatos pesquisa = null;
        for(Contatos item : lista) {
            if (item.getNome().equals(obj.getNome())) {
                pesquisa = item;
                break;
            }
        }

        if(pesquisa != null) {
            throw new DaoException("Contato duplicado para o nome: " + obj.getNome());
        }

        // incrementa o ID
        id++;

        obj.setId(id);
        lista.add(obj);
    }

    public Contatos get(int id) throws DaoException {
        Contatos pesquisa = null;
        for(Contatos item : lista) {
            if (item.getId() == id) {
                pesquisa = item;
                break;
            }
        }
        if(pesquisa == null) {
            throw new DaoException("Contato não encontrado para o id: " + id);
        }

        // retorna os dados achados
        return  pesquisa;
    }



}
