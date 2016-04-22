/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author murilo
 */
public class DaoUtils {
    private final Connection conexao;
    private static final String SQL_CRIA_TABELAS = "CREATE TABLE recurso(nome VARCHAR(255) PRIMARY KEY, descricao VARCHAR(255));"
                + " CREATE TABLE contexto(nome VARCHAR(255) PRIMARY KEY, descricao VARCHAR(255));"
                + " CREATE TABLE papel(nome VARCHAR(255) PRIMARY KEY, descricao VARCHAR(255));"
                + " CREATE TABLE permissao(nome_contexto VARCHAR(255) REFERENCES contexto(nome),"
                    + " nome_recurso VARCHAR(255) REFERENCES recurso(nome), nome_papel VARCHAR(255) REFERENCES papel(nome),"
                    + " PRIMARY KEY(nome_contexto, nome_recurso, nome_papel));";
                
    
    private static final String SQL_DROP_TABELAS = "DROP TABLE permissao;"
            + " DROP TABLE papel;"
            + " DROP TABLE contexto;"
            + " DROP TABLE recurso;"
            + " DROP TABLE papel_recurso; DROP TABLE papel;";
    
    public DaoUtils() throws SQLException {
        Conexao conexaoCriador = new Conexao();
        conexao = conexaoCriador.getConexao();
    }
    
    public void criaTabelas() throws SQLException{
        
        for(String s:SQL_CRIA_TABELAS.split(";")) {
            try {
                conexao.prepareCall(s).execute();
            } catch(SQLException ex) {
                System.out.println(ex + ": Tabela já existe");
            }
        }
    }
    
    public void limpaBanco() throws SQLException {
        for(String s:SQL_DROP_TABELAS.split(";")) {
            try {
                conexao.prepareCall(s).execute();
            } catch(SQLException ex) {
                System.out.println(ex + " Nao foi possivel fazer drop");
            }
        }
    }
    
    
    public static void main(String[] args) throws SQLException {
        DaoUtils du  = new DaoUtils();
        du.limpaBanco();
        //du.limpaBanco();
        //du.limpaBanco();
        du.criaTabelas();
    }
    
}
