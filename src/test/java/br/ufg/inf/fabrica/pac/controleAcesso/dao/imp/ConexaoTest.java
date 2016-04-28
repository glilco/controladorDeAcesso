/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author murilo
 */
public class ConexaoTest {
    
    public ConexaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of getConexao method, of class Conexao.
     */
    @Test
    public void testGetConexao() throws Exception {
        System.out.println("getConexao");
        Conexao instance = new Conexao("teste", "teste", "controleAcessoTeste");
        Connection result = instance.getConexao();
        
        // Teste se retornou uma conexão
        assertNotEquals(null, result);
        
        Connection outraConexao = instance.getConexao();
        
        // Como nao ha pool de conexoes todas tem que ser diferentes
        assertNotEquals(result, outraConexao);
        
        instance = new Conexao("teste", "teste", "controleAcessoTeste");
        
        result = instance.getConexao();
        
        // Teste se retornou uma conexão
        assertNotEquals(null, result);
        
        outraConexao = instance.getConexao();
        
        // Como nao ha pool de conexoes todas tem que ser diferentes
        assertNotEquals(result, outraConexao);
        
        outraConexao = instance.getConexao();
        
        // Como nao ha pool de conexoes todas tem que ser diferentes
        assertNotEquals(result, outraConexao);
        
    }
    
}
