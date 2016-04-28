/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IRecursoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
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
public class RecursoDaoTest {
    private static DaoUtils daoUtils;
    
    public RecursoDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        daoUtils = new DaoUtils(conexao);
        
        daoUtils.limpaBanco();
        daoUtils.criaTabelas();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException{
        daoUtils.limpaBanco();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class RecursoDao.
     */
    @Test
    public void test1Salvar() throws Exception {
        System.out.println("salvar");
       
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IRecursoDao rd = new RecursoDao(conexao);
        
        Recurso r1 = new Recurso("papel 1", "Descricao do papel 1");
        Recurso r2 = new Recurso("papel 2", "");
        Recurso r3 = new Recurso("papel 3", null);
        
        assertTrue(rd.salvar(r1));
        assertTrue(rd.salvar(r2));
        assertTrue(rd.salvar(r3));
        assertFalse(rd.salvar(r1));
    }

    /**
     * Test of obter method, of class RecursoDao.
     */
    @Test
    public void test2Obter() throws Exception {
        System.out.println("obter");

        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        IRecursoDao rd = new RecursoDao(conexao);
        
        // Testa obtenção de papel não existente no banco
        assertNull(rd.obter("papelInexistente"));
        
        Recurso r1 = rd.obter("papel 1");
        Recurso r2 = rd.obter("papel 2");
        Recurso r3 = rd.obter("papel 3");
        
        // Testa se obteve os papels
        assertNotNull(r1);
        assertNotNull(r2);
        assertNotNull(r3);
        
        // Testa se os papels obtidos são diferentes
        assertNotEquals(r1, r2);
        assertNotEquals(r2, r3);
        
        // Testa se obteve papels com os nomes e descrições esperados
        assertEquals(r1.getNome(), "papel 1");
        assertEquals(r2.getNome(), "papel 2");
        assertEquals(r3.getNome(), "papel 3");
        assertEquals(r1.getDescricao(), "Descricao do papel 1");
        assertEquals(r2.getDescricao(), "");
        assertNull(r3.getDescricao());
    }

    /**
     * Test of excluir method, of class RecursoDao.
     */
    @Test
    public void test3Excluir() throws Exception {
        System.out.println("excluir");

        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IRecursoDao rd = new RecursoDao(conexao);
        
        Recurso r1 = new Recurso("papel 1", "Descricao do papel 1");
        Recurso r2 = new Recurso("papel 2", "Descricao do papel 2");
        Recurso r3 = new Recurso("papel 3", "Descricao do papel 3");
        
        assertNotNull(rd.excluir(r1));
        assertNotNull(rd.excluir(r2));
        assertNotNull(rd.excluir(r3));
        
        Recurso r4 = new Recurso("papel inexistente", "");
        assertNull(rd.excluir(r4));
    }
    
}
