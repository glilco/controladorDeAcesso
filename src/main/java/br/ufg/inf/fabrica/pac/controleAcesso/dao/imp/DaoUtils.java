/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author murilo
 */
public class DaoUtils {
    private final Connection conexao;
    private static final String SQL_CRIA_TABELAS = "CREATE TABLE permissao(id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + " nome_contexto VARCHAR(255), nome_recurso VARCHAR(255), nome_papel VARCHAR(255), UNIQUE (nome_contexto, nome_recurso, nome_papel));";
                
    
    private static final String SQL_DROP_TABELAS = "DROP TABLE permissao;"
            + " DROP TABLE papel;"
            + " DROP TABLE contexto;"
            + " DROP TABLE recurso;"
            + " DROP TABLE papel_recurso; DROP TABLE papel;";
    
    public DaoUtils(Conexao conexaoCriador) throws SQLException {
        //Conexao conexaoCriador = new Conexao();
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
        Conexao conexao = new Conexao();
        DaoUtils du  = new DaoUtils(conexao);
        du.limpaBanco();
        du.criaTabelas();
    }
    
}
