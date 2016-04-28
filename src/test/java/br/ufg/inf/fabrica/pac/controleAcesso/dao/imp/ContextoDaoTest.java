/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.dao.IContextoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
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
public class ContextoDaoTest {
    private static DaoUtils daoUtils;
    
    public ContextoDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        daoUtils = new DaoUtils(conexao);
        
        daoUtils.limpaBanco();
        daoUtils.criaTabelas();
        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        daoUtils.limpaBanco();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class ContextoDao.
     */
    @Test
    public void test1Salvar() throws Exception {
        System.out.println("salvar");
        
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IContextoDao cd = new ContextoDao(conexao);
        
        Contexto c1 = new Contexto("contexto 1", "Descricao do contexto 1");
        Contexto c2 = new Contexto("contexto 2", "");
        Contexto c3 = new Contexto("contexto 3", null);
        
        assertTrue(cd.salvar(c1));
        assertTrue(cd.salvar(c2));
        assertTrue(cd.salvar(c3));
        assertFalse(cd.salvar(c1));
    }

    /**
     * Test of obter method, of class ContextoDao.
     */
    @Test
    public void test2Obter() throws Exception {
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        IContextoDao cd = new ContextoDao(conexao);
        
        /*System.out.println(cd.obter("contexto 1").getNome() + " descricao: " + cd.obter("contexto 1").getDescricao());
        System.out.println(cd.obter("contexto 2").getNome() + " descricao: " + cd.obter("contexto 2").getDescricao());
        System.out.println(cd.obter("contexto 3").getNome() + " descricao: " + cd.obter("contexto 3").getDescricao());*/
        
        // Testa obtenção de contexto não existente no banco
        assertNull(cd.obter("contextoInexistente"));
        
        Contexto c1 = cd.obter("contexto 1");
        Contexto c2 = cd.obter("contexto 2");
        Contexto c3 = cd.obter("contexto 3");
        
        // Testa se obteve os contextos
        assertNotNull(c1);
        assertNotNull(c2);
        assertNotNull(c3);
        
        // Testa se os contextos obtidos são diferentes
        assertNotEquals(c1, c2);
        assertNotEquals(c2, c3);
        
        // Testa se obteve contextos com os nomes e descrições esperados
        assertEquals(c1.getNome(), "contexto 1");
        assertEquals(c2.getNome(), "contexto 2");
        assertEquals(c3.getNome(), "contexto 3");
        assertEquals(c1.getDescricao(), "Descricao do contexto 1");
        assertEquals(c2.getDescricao(), "");
        assertNull(c3.getDescricao());
    }

    /**
     * Test of excluir method, of class ContextoDao.
     */
    @Test
    public void test3Excluir() throws Exception {
        Conexao conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        
        IContextoDao cd = new ContextoDao(conexao);
        
        Contexto c1 = new Contexto("contexto 1", "Descricao do contexto 1");
        Contexto c2 = new Contexto("contexto 2", "Descricao do contexto 2");
        Contexto c3 = new Contexto("contexto 3", "Descricao do contexto 3");
        
        assertNotNull(cd.excluir(c1));
        assertNotNull(cd.excluir(c2));
        assertNotNull(cd.excluir(c3));
        
        Contexto c4 = new Contexto("contexto inexistente", "");
        assertNull(cd.excluir(c4));

    }
    
}
