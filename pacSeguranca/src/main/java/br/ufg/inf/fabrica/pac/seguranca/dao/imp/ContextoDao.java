/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.dao.imp;

import br.ufg.inf.fabrica.pac.seguranca.dao.IContextoDao;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Contexto;
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

    public ContextoDao() throws SQLException {
        Conexao conexaoGerador = new Conexao();
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
        
        return contexto;
        
    }

    @Override
    public Contexto excluir(Contexto objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_CONTEXTO);
        p.setString(1, objeto.getNome());
        p.executeUpdate();
        
        return objeto;
    }
    
    public static void main(String args[]) throws SQLException {
        ContextoDao cd = new ContextoDao();
        
        Contexto c1 = new Contexto("contexto 1", "Descricao do contexto 1");
        Contexto c2 = new Contexto("contexto 2", "Descricao do contexto 2");
        Contexto c3 = new Contexto("contexto 3", "Descricao do contexto 3");
        
        cd.salvar(c1);
        cd.salvar(c2);
        cd.salvar(c3);
        
        System.out.println(cd.obter("contexto 1").getNome() + " descricao: " + cd.obter("contexto 1").getDescricao());
        System.out.println(cd.obter("contexto 2").getNome() + " descricao: " + cd.obter("contexto 2").getDescricao());
        System.out.println(cd.obter("contexto 3").getNome() + " descricao: " + cd.obter("contexto 3").getDescricao());
        
        cd.salvar(c1);
        
        cd.excluir(c1);
        cd.excluir(c2);
        cd.excluir(c3);

    }
    
}
