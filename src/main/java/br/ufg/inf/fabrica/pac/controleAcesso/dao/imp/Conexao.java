/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 *
 * @author murilo
 */
public class Conexao {
    private String usuario;
    private String senha;
    private String banco;
    
    //private int totalConexoes = 3;
    //private static Queue<Connection> conexoes;

    public Conexao(String usuario, String senha, String banco) {
        /*if(totalConexoes <=0) {
            throw new RuntimeException("Total de conexões deve ser maior do que zero");
        }*/
        
        this.usuario = usuario;
        this.senha = senha;
        this.banco = banco;
        //this.totalConexoes = totalConexoes;
        
        /*if(conexoes == null) {
            conexoes = new LinkedList<>();
        }*/
    }

    public Conexao() {
        this.usuario = "pac";
        this.senha = "pac";
        this.banco = "controleAcessoData";
        /*if(conexoes == null) {
            conexoes = new LinkedList<>();
        }*/
    }
    
    public Connection getConexao() throws SQLException {
        Connection conexao = null;
        //if( conexoes.size() < totalConexoes) {
            
            Properties connectionProps = new Properties();
            connectionProps.put("user", usuario);
            connectionProps.put("password", senha);
                String connString = 
                           "jdbc:derby:" +
                           banco+";create=true";
                System.out.println(connString);

                conexao = DriverManager.getConnection(connString,
                           connectionProps);
                //conexoes.add(conexao);

            //System.out.println("Connected to database: " + conexoes.size());
        /*} else {
            conexao = conexoes.poll();
            conexoes.add(conexao);
        
        }*/
        return conexao;
    }
}
