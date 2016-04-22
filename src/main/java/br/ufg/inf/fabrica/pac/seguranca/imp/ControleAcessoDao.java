/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.imp;

import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author murilo
 */
public class ControleAcessoDao {
    private static Connection connection;
    private static ControleAcessoDao cad = null;

    private static final String SQL_CRIA_TABELAS = "CREATE TABLE Papel(nome VARCHAR(255) PRIMARY KEY);"
                + " CREATE TABLE Papel_Recurso(nome_papel VARCHAR(255) REFERENCES Papel(nome), "
                + " recurso VARCHAR(255), PRIMARY KEY (nome_papel, recurso));";
    private static final String SQL_DROP_TABELAS = "DROP TABLE Papel_recurso; DROP TABLE Papel;";
    
    
    private static final String SQL_INSERT_PAPEL = "INSERT INTO Papel(nome) values (?)";
    private static final String SQL_INSERT_PAPEL_RECURSO = "INSERT INTO Papel_recurso(nome_papel, recurso) values (?, ?)";

    private static final String SQL_REMOVE_PAPEL_RECURSO = "DELETE FROM Papel_recurso WHERE nome_papel = ? AND recurso = ?";
    
    private static final String SQL_SELECT_PAPEL_RECURSO = "SELECT p.recurso FROM Papel_recurso AS p WHERE p.nome_papel = ?";
    private static final String SQL_SELECT_AUTORIZACAO = "SELECT COUNT(*) FROM Papel_recurso AS p WHERE p.nome_papel = ? AND p.recurso = ?";

    
    private ControleAcessoDao() throws SQLException{
        Connection conn = getConnection();
        
        // Cria tabelas
        for(String s:SQL_CRIA_TABELAS.split(";")) {
            try {
            conn.prepareCall(s).execute();
            } catch(SQLException ex) {
                System.out.println(ex + ": Tabela já existe");
            }
        }
        
        /*for(Papell papel : Papel.values()) {
            System.out.println("Papel: " + papel.name());
            inserePapel(papel);
        }*/
        
    }
    
    public static ControleAcessoDao getInstance() throws SQLException{
        if(cad == null) {
            cad = new ControleAcessoDao();
        }
        
        return cad;
    }
    
    public Connection getConnection() throws SQLException{
        if(connection == null) {
            getConnection("pac", "pac", "controleAcesso");
        }
        return connection;
        
    }
    
    public Boolean inserePapel(Papel papel) throws SQLException{
        Connection conn = getConnection();
        
        PreparedStatement pstmnt = conn.prepareStatement(SQL_INSERT_PAPEL);
        pstmnt.setString(1, papel.getNome());
        try {
            pstmnt.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.out.println("Papel " + papel + " já existe");
            return false;
        }
        return true;
    }
    
    public Boolean insereAutorizacao(Papel papel, String recurso) throws SQLException{
        Connection conn = getConnection();
        
        PreparedStatement pstmnt = conn.prepareStatement(SQL_INSERT_PAPEL_RECURSO);
        pstmnt.setString(1, papel.getNome());
        pstmnt.setString(2, recurso);
        
        try {
            pstmnt.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException ex) {
            System.out.println("Autorizacao  de acesso de " + papel.toString() + " para " + recurso + " já existe");
            return false;
        }
        
        return true;
    }
    
    public Boolean removeAutorizacao(Papel papel, String recurso) throws SQLException {
        Connection conn = getConnection();
        
        PreparedStatement p = conn.prepareStatement(SQL_REMOVE_PAPEL_RECURSO);
        p.setString(1, papel.getNome());
        p.setString(2, recurso);
        
        try {
            p.executeUpdate();
        } catch(SQLException x) {
            System.out.println("Não exite autorização de " + papel + " para " + recurso);
            return false;
        }
        
        return true;
    }
    
    public Set<String> getRecursosAutorizados(Papel papel) throws SQLException {
        Connection conn = getConnection();
        
        PreparedStatement p = conn.prepareStatement(SQL_SELECT_PAPEL_RECURSO);
        p.setString(1, papel.getNome());
        
        Set<String> lista = new HashSet<String>();
        
        try {
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch(SQLException ex) {
            System.out.println("nao conseguiu pegar autorizacoes");
        }
        
        return lista;
    }
    
    
    public Boolean getAutorizado(Papel papel, String recurso) throws SQLException {
        Connection conn = getConnection();
        
        PreparedStatement p = conn.prepareStatement(SQL_SELECT_AUTORIZACAO);
        p.setString(1, papel.getNome());
        p.setString(2, recurso);
        
        try {
            ResultSet rs = p.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            return false;
        }
            
        return true;
    }
    
    private Connection getConnection(String username, String password, String dbName) throws SQLException {

        if(connection == null) {
            
            //DriverManager.registerDriver(new org.apache.derby.jdbc.());
            
        
            Properties connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
                String connString = 
                           "jdbc:derby:" +
                           dbName+";create=true";
                System.out.println(connString);

                connection = DriverManager.getConnection(connString,
                           connectionProps);

            System.out.println("Connected to database");
        }
        return connection;
    }
 
    public static void main(String[] args) throws  SQLException {
        ControleAcessoDao cad = new ControleAcessoDao();
        
        
    }
    
    
}
