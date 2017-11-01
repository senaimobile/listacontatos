package com.br.listadecontatos.model.dao;

/**
 * Classe de Exception para DAO
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 31/10/2017.
 */

public class ExceptionDAO extends Exception {
    public ExceptionDAO(String detailMessage) {
        super(detailMessage);
    }
}
