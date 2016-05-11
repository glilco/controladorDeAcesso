/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao;

import java.sql.SQLException;

/**
 *
 * @author murilo
 */
public interface IDao<T> {
    public Boolean salvar(T objeto) throws SQLException;
    public T obter(int id) throws SQLException;
    public T excluir(T objeto) throws SQLException;
}
