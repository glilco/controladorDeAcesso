/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IPermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class PermissaoDao implements IPermissaoDao {
    private static Connection conexao;
    
    private static final String SQL_SALVA_PERMISSAO = "INSERT INTO permissao (nome_contexto, nome_recurso, nome_papel) VALUES(?,?,?)";
    private static final String SQL_SELECT_PERMISSAO_COMPLETA = "SELECT p.nome_contexto, p.nome_recurso, p.nome_papel FROM permissao AS p WHERE p.nome_contexto = ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_SELECT_PERMISSAO_SEM_CONTEXTO = "SELECT * FROM permissao AS p WHERE p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_SELECT_PERMISSOES_PAPEL = "SELECT * FROM permissao AS p WHERE p.nome_papel = ? ";
    private static final String SQL_EXCLUI_PERMISSAO = "DELETE FROM permissao AS p WHERE p.nome_contexto= ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    
    private static final String SQL_SELECT_PERMISSAO_ID = "SELECT * FROM permissao AS p WHERE p.id = ?";

    public PermissaoDao(Conexao conexaoGerador) throws SQLException {
        conexao = conexaoGerador.getConexao();
    }

    @Override
    public Boolean salvar(Permissao objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SALVA_PERMISSAO);
        p.setString(1, objeto.getContexto());
        p.setString(2, objeto.getRecurso());
        p.setString(3, objeto.getPapel());
        try {
            p.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.err.println("Permissao já existe no banco: " + objeto.getContexto()
                    + " " + objeto.getRecurso()
                    + " " + objeto.getPapel());
            return false;
        } finally {
            p.close();
        }
        
        return true;
    }

    @Override
    public Permissao obter(int id) throws SQLException {
        Permissao permissao;
        try (PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_ID)) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                permissao = null;
                if(r.next()) {
                    permissao = new Permissao(r.getString("NOME_CONTEXTO"), r.getString("NOME_RECURSO") , r.getString("NOME_PAPEL"));
                }
            }
        }
        return permissao;
    }

    @Override
    public Permissao excluir(Permissao objeto) throws SQLException {
        int i;
        try (PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_PERMISSAO)) {
            p.setString(1, objeto.getContexto());
            p.setString(2, objeto.getRecurso());
            p.setString(3, objeto.getPapel());
            i = p.executeUpdate();
        }
        if(i > 0) {
            return objeto;
        }
        return null;
    }

    @Override
    public Permissao obter(String contexto, String recurso, String papel) throws SQLException {
        Permissao permissao;
        try (PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_COMPLETA)) {
            p.setString(1, contexto);
            p.setString(2, recurso);
            p.setString(3, papel);
            try (ResultSet r = p.executeQuery()) {
                permissao = null;
                if(r.next()) {
                    permissao = new Permissao(contexto, recurso, papel);
                }
            }
        }
        return permissao;
    }

    @Override
    public Set<Permissao> obter(String recurso, String papel) throws SQLException {
        Set<Permissao> permissoes;
        try (PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_SEM_CONTEXTO)) {
            p.setString(1,recurso);
            p.setString(2,papel);
            try (ResultSet r = p.executeQuery()) {
                permissoes = new HashSet<>();
                while(r.next()) {
                    permissoes.add(new Permissao(r.getString("nome_contexto"), recurso, papel));
                }
            }
        }
        
        return permissoes;
    }
    
    @Override
    public Set<Permissao> obter(String papel) throws SQLException {
        Set<Permissao> permissoes;
        try (PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSOES_PAPEL)) {
            p.setString(1,papel);
            try (ResultSet r = p.executeQuery()) {
                permissoes = new HashSet<>();
                while(r.next()) {
                    permissoes.add(new Permissao(r.getString("nome_contexto"),
                        r.getString("nome_recurso"), papel));
                }
            }
        }
        
        return permissoes;
    }
}
