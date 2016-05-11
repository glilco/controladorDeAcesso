/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author murilo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoUtilsTest {
    private static Conexao conexao;
    private static DaoUtils daoUtils;
    
    private static final String SQL_SELECT_TABELAS = "SHOW TABLES";
    
    public DaoUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        daoUtils = new DaoUtils(conexao);
        daoUtils.limpaBanco();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of criaTabelas method, of class DaoUtils.
     */
    @Test
    public void test1CriaTabelas() throws Exception {
        System.out.println("criaTabelas");
        daoUtils.criaTabelas();
        
        Connection conn =  conexao.getConexao();
        
        // Testa se criou a tabela Permissao
        DatabaseMetaData dbmd = conn.getMetaData();
        try (ResultSet resultSet = dbmd.getTables("controleAcessoTeste", null, null, new String[] {"TABLE"})) {
            while (resultSet.next()) {
                String strTableName = resultSet.getString("TABLE_NAME");
                assertEquals("PERMISSAO", strTableName);
            }
        }
        
        // Testa se foram criadas as colunas
        StringBuilder sb = new StringBuilder();
        try(ResultSet resultSet = dbmd.getColumns("controleAcesso", null, "PERMISSAO", null)) {
            while (resultSet.next()) {
                String colName = resultSet.getString("COLUMN_NAME");
                String tableName = resultSet.getString("TABLE_NAME");
                sb.append(colName);
                sb.append(" ");
                assertEquals("PERMISSAO", tableName);
            }
        }
        assertEquals("ID NOME_CONTEXTO NOME_RECURSO NOME_PAPEL ", sb.toString());
        
    }

    /**
     * Test of limpaBanco method, of class DaoUtils.
     */
    @Test
    public void test2LimpaBanco() throws Exception {
        System.out.println("limpaBanco");
        daoUtils.limpaBanco();
        
        Connection conn =  conexao.getConexao();
        
        // Testa se criou a tabela Permissao
        DatabaseMetaData dbmd = conn.getMetaData();
        try (ResultSet resultSet = dbmd.getTables("controleAcessoTeste", null, null, new String[] {"TABLE"})) {
            if (resultSet.next()) {
                fail("Banco deveria estar vazio");
            }
        }
    }
}
