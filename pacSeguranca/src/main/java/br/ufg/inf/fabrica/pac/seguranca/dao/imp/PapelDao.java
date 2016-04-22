/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.dao.imp;

import br.ufg.inf.fabrica.pac.seguranca.dao.IPapelDao;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class PapelDao implements IPapelDao {
    private static Connection conexao;
    
    private static final String SQL_SALVA_PAPEL = "INSERT INTO papel VALUES(?,?)";
    private static final String SQL_SELECT_PAPEL = "SELECT p.nome, p.descricao FROM papel AS p WHERE p.nome = ?";
    private static final String SQL_EXCLUI_PAPEL = "DELETE FROM papel AS p WHERE p.nome = ?";

    public PapelDao() throws SQLException {
        Conexao conexaoGerador = new Conexao();
        conexao = conexaoGerador.getConexao();
    }

    @Override
    public Boolean salvar(Papel objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SALVA_PAPEL);
        p.setString(1, objeto.getNome());
        p.setString(2, objeto.getDescricao());
        try {
            p.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.err.println("Papel já existe no banco: " + objeto.getNome());
            return false;
        }
        
        return true;
    }

    @Override
    public Papel obter(String nome) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PAPEL);
        p.setString(1, nome);
        
        ResultSet r = p.executeQuery();
        
        Papel papel = null;
        if(r.next()) {
            papel = new Papel(r.getString(1), r.getString(2));
            
        }
        
        return papel;
    }

    @Override
    public Papel excluir(Papel objeto) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_EXCLUI_PAPEL);
        p.setString(1, objeto.getNome());
        p.executeUpdate();
        
        return objeto;
    }
    
}
