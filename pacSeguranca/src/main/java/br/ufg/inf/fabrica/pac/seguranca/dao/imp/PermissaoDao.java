/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.dao.imp;

import br.ufg.inf.fabrica.pac.seguranca.dao.IPermissaoDao;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Contexto;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Permissao;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class PermissaoDao implements IPermissaoDao {
    private static Connection conexao;
    
    private static final String SQL_SALVA_PERMISSAO = "INSERT INTO permissao VALUES(?,?,?)";
    private static final String SQL_SELECT_PERMISSAO_COMPLETA = "SELECT p.nome_contexto, p.nome_recurso, p.nome_papel FROM permissao AS p WHERE p.nome_contexto = ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_SELECT_PERMISSAO_SEM_CONTEXTO = "SELECT p.nome_contexto, p.nome_recurso, p.nome_papel FROM permissao AS p WHERE p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_EXCLUI_PERMISSAO = "DELETE FROM permissao AS p WHERE p.nome_contexto= ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";

    public PermissaoDao() throws SQLException {
        Conexao conexaoGerador = new Conexao();
        conexao = conexaoGerador.getConexao();
    }

    @Override
    public Boolean salvar(Permissao objeto) throws SQLException {
         PreparedStatement p = conexao.prepareStatement(SQL_SALVA_PERMISSAO);
        p.setString(1, objeto.getContexto().getNome());
        p.setString(2, objeto.getRecurso().getNome());
        p.setString(3, objeto.getPapel().getNome());
        try {
            p.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.err.println("Permissao já existe no banco: " + objeto.getContexto().getNome()
                    + " " + objeto.getRecurso().getNome()
                    + " " + objeto.getPapel().getNome());
            return false;
        }
        
        return true;
    }

    @Override
    public Permissao obter(String nome) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permissao excluir(Permissao objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_PERMISSAO);
        p.setString(1, objeto.getContexto().getNome());
        p.setString(2, objeto.getRecurso().getNome());
        p.setString(3, objeto.getPapel().getNome());
        p.executeUpdate();
        
        return objeto;
    }

    @Override
    public Permissao obter(Contexto contexto, Recurso recurso, Papel papel) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_COMPLETA);
        p.setString(1, contexto.getNome());
        p.setString(2, contexto.getNome());
        p.setString(3, contexto.getNome());
        
        ResultSet r = p.executeQuery();
        
        Permissao permissao = null;
        if(r.next()) {
            permissao = new Permissao(contexto, recurso, papel);
        }
        
        return permissao;
    }

    @Override
    public Set<Permissao> obter(Recurso recurso, Papel papel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
