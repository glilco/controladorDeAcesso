/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IPapelDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
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
public class PapelDaoTest {
    private static DaoUtils daoUtils;
    
    public PapelDaoTest() {
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
     * Test of salvar method, of class PapelDao.
     */
    @Test
    public void test1Salvar() throws Exception {
        System.out.println("salvar");
       
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IPapelDao pd = new PapelDao(conexao);
        
        Papel p1 = new Papel("papel 1", "Descricao do papel 1");
        Papel p2 = new Papel("papel 2", "");
        Papel p3 = new Papel("papel 3", null);
        
        assertTrue(pd.salvar(p1));
        assertTrue(pd.salvar(p2));
        assertTrue(pd.salvar(p3));
        assertFalse(pd.salvar(p1));
    }

    /**
     * Test of obter method, of class PapelDao.
     */
    @Test
    public void test2Obter() throws Exception {
        System.out.println("obter");

        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        IPapelDao pd = new PapelDao(conexao);
        
        // Testa obtenção de papel não existente no banco
        assertNull(pd.obter("papelInexistente"));
        
        Papel p1 = pd.obter("papel 1");
        Papel p2 = pd.obter("papel 2");
        Papel p3 = pd.obter("papel 3");
        
        // Testa se obteve os papels
        assertNotNull(p1);
        assertNotNull(p2);
        assertNotNull(p3);
        
        // Testa se os papels obtidos são diferentes
        assertNotEquals(p1, p2);
        assertNotEquals(p2, p3);
        
        // Testa se obteve papels com os nomes e descrições esperados
        assertEquals(p1.getNome(), "papel 1");
        assertEquals(p2.getNome(), "papel 2");
        assertEquals(p3.getNome(), "papel 3");
        assertEquals(p1.getDescricao(), "Descricao do papel 1");
        assertEquals(p2.getDescricao(), "");
        assertNull(p3.getDescricao());
    }

    /**
     * Test of excluir method, of class PapelDao.
     */
    @Test
    public void test3Excluir() throws Exception {
        System.out.println("excluir");

        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IPapelDao pd = new PapelDao(conexao);
        
        Papel p1 = new Papel("papel 1", "Descricao do papel 1");
        Papel p2 = new Papel("papel 2", "Descricao do papel 2");
        Papel p3 = new Papel("papel 3", "Descricao do papel 3");
        
        assertNotNull(pd.excluir(p1));
        assertNotNull(pd.excluir(p2));
        assertNotNull(pd.excluir(p3));
        
        Papel c4 = new Papel("papel inexistente", "");
        assertNull(pd.excluir(c4));
    }
    
}
