/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IPermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
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
    
    private static final String SQL_SALVA_PERMISSAO = "INSERT INTO permissao VALUES(?,?,?)";
    private static final String SQL_SELECT_PERMISSAO_COMPLETA = "SELECT p.nome_contexto, p.nome_recurso, p.nome_papel FROM permissao AS p WHERE p.nome_contexto = ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_SELECT_PERMISSAO_SEM_CONTEXTO = "SELECT pa.nome AS nome_papel, pa.descricao AS desc_papel, r.nome AS nome_recurso, r.descricao as desc_recurso,"
            + " c.nome AS nome_contexto, c.descricao AS desc_contexto FROM permissao AS p INNER JOIN recurso AS r ON p.nome_recurso=r.nome"
            + " INNER JOIN papel AS pa ON p.nome_papel=pa.nome INNER JOIN contexto AS c ON p.nome_contexto=c.nome WHERE p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";
    private static final String SQL_SELECT_PERMISSOES_PAPEL = "SELECT pa.nome AS nome_papel, pa.descricao AS desc_papel, r.nome AS nome_recurso, r.descricao as desc_recurso,"
            + " c.nome AS nome_contexto, c.descricao AS desc_contexto FROM permissao AS p INNER JOIN recurso AS r ON p.nome_recurso=r.nome"
            + " INNER JOIN papel AS pa ON p.nome_papel=pa.nome INNER JOIN contexto AS c ON p.nome_contexto=c.nome WHERE p.nome_papel = ? ";
    private static final String SQL_EXCLUI_PERMISSAO = "DELETE FROM permissao AS p WHERE p.nome_contexto= ?"
            + " AND p.nome_recurso = ?"
            + " AND p.nome_papel = ? ";

    public PermissaoDao(Conexao conexaoGerador) throws SQLException {
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
        } finally {
            p.close();
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
        int i = p.executeUpdate();
        p.close();
        if(i > 0) {
            return objeto;
        }
        return null;
    }

    @Override
    public Permissao obter(Contexto contexto, Recurso recurso, Papel papel) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_COMPLETA);
        p.setString(1, contexto.getNome());
        p.setString(2, recurso.getNome());
        p.setString(3, papel.getNome());
        
        ResultSet r = p.executeQuery();
        
        Permissao permissao = null;
        if(r.next()) {
            permissao = new Permissao(contexto, recurso, papel);
        }
        r.close();
        p.close();
        return permissao;
    }

    @Override
    public Set<Permissao> obter(Recurso recurso, Papel papel) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSAO_SEM_CONTEXTO);
        p.setString(1,recurso.getNome());
        p.setString(2,papel.getNome());
        
        ResultSet r = p.executeQuery();
        
        Set<Permissao> permissoes = new HashSet<>();
        
        while(r.next()) {
            permissoes.add(new Permissao(new Contexto(r.getString("nome_contexto"), r.getString("desc_contexto")), recurso, papel));
        }
        
        r.close();
        p.close();
        
        return permissoes;
    }
    
    @Override
    public Set<Permissao> obter(Papel papel) throws SQLException {
        PreparedStatement p = conexao.prepareStatement(SQL_SELECT_PERMISSOES_PAPEL);
        p.setString(1,papel.getNome());
        
        ResultSet r = p.executeQuery();
        
        Set<Permissao> permissoes = new HashSet<>();
        
        while(r.next()) {
            permissoes.add(new Permissao(new Contexto(r.getString("nome_contexto"), r.getString("desc_contexto")), 
                    new Recurso(r.getString("nome_recurso"), r.getString("desc_recurso")), papel));
        }
        
        r.close();
        p.close();
        
        return permissoes;
    }
    
    
    public static void main(String args[]) throws SQLException{
        Conexao conexao = new Conexao();
        
        PermissaoDao pd = new PermissaoDao(conexao);
        
        Recurso r1 = new Recurso("recurso1", "recurso 1");
        Papel p1 = new Papel("papel1","papel 1");
        
        Contexto c1 = new Contexto("c1", "c1");
        Contexto c2 = new Contexto("c2", "c2");
        Contexto c3 = new Contexto("c3", "c3");
        
        Permissao p = new Permissao(c1, r1, p1);
        
        
        RecursoDao rd = new RecursoDao(conexao);
        PapelDao pad = new PapelDao(conexao);
        ContextoDao cd = new ContextoDao(conexao);
        
        rd.salvar(r1);
        pad.salvar(p1);
        cd.salvar(c1);
        cd.salvar(c2);
        cd.salvar(c3);
        
        pd.salvar(p);
        
        p.setContexto(c2);
        pd.salvar(p);
        
        p.setContexto(c3);
        pd.salvar(p);
        
        for(Permissao permissao: pd.obter(r1,p1)) {
            System.out.println(permissao);
        }
    }


}
