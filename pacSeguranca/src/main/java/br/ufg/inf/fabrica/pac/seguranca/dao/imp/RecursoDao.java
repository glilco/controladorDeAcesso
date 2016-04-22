/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.dao.imp;

import br.ufg.inf.fabrica.pac.seguranca.dao.IRecursoDao;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class RecursoDao implements IRecursoDao {
    private static Connection conexao;
    
    private static final String SQL_SALVA_RECURSO = "INSERT INTO recurso VALUES(?,?)";
    private static final String SQL_SELECT_RECURSO = "SELECT r.nome, r.descricao FROM recurso AS r WHERE r.nome = ?";
    private static final String SQL_EXCLUI_RECURSO = "DELETE FROM recurso AS r WHERE r.nome = ?";

    public RecursoDao() throws SQLException {
        Conexao conexaoGerador = new Conexao();
        conexao = conexaoGerador.getConexao();
    }

    @Override
    public Boolean salvar(Recurso objeto) throws SQLException {
        
        PreparedStatement p = conexao.prepareStatement(SQL_SALVA_RECURSO);
        p.setString(1, objeto.getNome());
        p.setString(2, objeto.getDescricao());
        try {
            p.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.err.println("Recurso já existe no banco: " + objeto.getNome());
            return false;
        }
        
        return true;
    }

    @Override
    public Recurso obter(String nome) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_RECURSO);
        p.setString(1, nome);
        
        ResultSet r = p.executeQuery();
        
        Recurso recurso = null;
        if(r.next()) {
            recurso = new Recurso(r.getString(1), r.getString(2));
            
        }
        
        return recurso;
    }

    @Override
    public Recurso excluir(Recurso objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_RECURSO);
        p.setString(1, objeto.getNome());
        p.executeUpdate();
        
        return objeto;
    }
}
