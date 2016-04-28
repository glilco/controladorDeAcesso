/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IContextoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class ContextoDao implements IContextoDao{
    private final Connection conexao;
    
    private static final String SQL_SALVA_CONTEXTO = "INSERT INTO contexto VALUES(?,?)";
    private static final String SQL_SELECT_CONTEXTO = "SELECT c.nome, c.descricao FROM contexto AS c WHERE c.nome = ?";
    private static final String SQL_EXCLUI_CONTEXTO = "DELETE FROM contexto AS c WHERE c.nome = ?";

    public ContextoDao(Conexao conexaoGerador) throws SQLException {
        //Conexao conexaoGerador = new Conexao();
        conexao = conexaoGerador.getConexao();
    }

    @Override
    public Boolean salvar(Contexto objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SALVA_CONTEXTO);
        p.setString(1, objeto.getNome());
        p.setString(2, objeto.getDescricao());
        try {
            p.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.err.println("Contexto já existe no banco: " + objeto.getNome());
            return false;
        } finally {
            p.close();
        }
        
        return true;
    }

    @Override
    public Contexto obter(String nome) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_CONTEXTO);
        p.setString(1, nome);
        
        ResultSet r = p.executeQuery();
        
        Contexto contexto = null;
        if(r.next()) {
            contexto = new Contexto(r.getString(1), r.getString(2));
            
        }
        r.close();
        p.close();
        return contexto;
        
    }

    @Override
    public Contexto excluir(Contexto objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_CONTEXTO);
        p.setString(1, objeto.getNome());
        int r = p.executeUpdate();        
        p.close();
        if(r > 0) {
            return objeto;
        }
        
        return null;
    }
    
}
