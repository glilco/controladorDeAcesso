/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import java.sql.SQLException;
import java.util.Set;
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
public class PermissaoDaoTest {
    private static PermissaoDao permissaoDao;
    private static DaoUtils daoUtils;
    private static Conexao conexao;
    
    public PermissaoDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        conexao = new Conexao("teste", "teste", "controleAcessoTeste");
        daoUtils = new DaoUtils(conexao);
        permissaoDao = new PermissaoDao(conexao);
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
     * Test of salvar method, of class PermissaoDao.
     */
    @Test
    public void test1Salvar() throws Exception {
        System.out.println("salvar");
        /*Permissao objeto = null;
        PermissaoDao instance = null;
        Boolean expResult = null;
        Boolean result = instance.salvar(objeto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
        
        Permissao p1 = new Permissao("c1", "r1", "p1");
        Permissao p2 = new Permissao("c2", "r2", "p2");
        Permissao p3 = new Permissao("c3", "r3", "p3");
        Permissao p4 = new Permissao("c1", "r1", "p2");
        Permissao p5 = new Permissao("c1", "r1", "p1");
        
        assertTrue(permissaoDao.salvar(p1));
        assertTrue(permissaoDao.salvar(p2));
        assertTrue(permissaoDao.salvar(p3));
        assertTrue(permissaoDao.salvar(p4));
        assertFalse(permissaoDao.salvar(p5));
        assertFalse(permissaoDao.salvar(p1));
        
    }

    /**
     * Test of obter method, of class PermissaoDao.
     */
    @Test
    public void test2Obter_int() throws Exception {
        System.out.println("obter");
        assertNull(permissaoDao.obter(0));
        Permissao p1 = permissaoDao.obter(1);
        assertEquals(new Permissao("c1", "r1", "p1"), p1);
        Permissao p2 = permissaoDao.obter(2);
        assertEquals(new Permissao("c2", "r2", "p2"), p2);
        Permissao p3 = permissaoDao.obter(3);
        assertEquals(new Permissao("c3", "r3", "p3"), p3);
        Permissao p4 = permissaoDao.obter(4);
        assertEquals(new Permissao("c1", "r1", "p2"), p4);
    }

    /**
     * Test of excluir method, of class PermissaoDao.
     */
    @Test
    public void test3Excluir() throws Exception {
        System.out.println("excluir");
        
        assertNull(permissaoDao.excluir(new Permissao("inexistente", "inexistente", "inexistente")));
        assertNull(permissaoDao.excluir(new Permissao("c1", "inexistente", "inexistente")));
        assertNull(permissaoDao.excluir(new Permissao("inexistente", "r1", "p1")));
        assertNull(permissaoDao.excluir(new Permissao("c1", "inexistente", "p1")));
        assertNull(permissaoDao.excluir(new Permissao("", "", "p1")));
        assertNull(permissaoDao.excluir(new Permissao(null, null, null)));
        
        Permissao p1 = new Permissao("c1", "r1", "p1");
        Permissao p2 = new Permissao("c2", "r2", "p2");
        Permissao p3 = new Permissao("c3", "r3", "p3");
        Permissao p4 = new Permissao("c1", "r1", "p2");
        
        assertEquals(p1, permissaoDao.excluir(p1));
        assertEquals(p2, permissaoDao.excluir(p2));
        assertEquals(p3, permissaoDao.excluir(p3));
        assertEquals(p4, permissaoDao.excluir(p4));
    }

    /**
     * Test of obter method, of class PermissaoDao.
     */
    @Test
    public void test2Obter_Completo() throws Exception {
        System.out.println("obter");
        
        Permissao p1 = new Permissao("c1", "r1", "p1");
        Permissao p2 = new Permissao("c2", "r2", "p2");
        Permissao p3 = new Permissao("c3", "r3", "p3");
        Permissao p4 = new Permissao("c1", "r1", "p2");
        
        assertEquals(p1, permissaoDao.obter("c1", "r1", "p1"));
        assertEquals(p2, permissaoDao.obter("c2", "r2", "p2"));
        assertEquals(p3, permissaoDao.obter("c3", "r3", "p3"));
        assertEquals(p4, permissaoDao.obter("c1", "r1", "p2"));
        
        assertNull(permissaoDao.obter("inexistente", "inexistente", "inexistente"));
        assertNull(permissaoDao.obter("c1", "inexistente", "inexistente"));
        assertNull(permissaoDao.obter("inexistente", "r1", "p1"));
        assertNull(permissaoDao.obter("", "r1", "p1"));
        assertNull(permissaoDao.obter("", "", ""));
        assertNull(permissaoDao.obter(null, null, null));
    }

    /**
     * Test of obter method, of class PermissaoDao.
     */
    @Test
    public void test2Obter_Recurso_Papel() throws Exception {
        System.out.println("obter");
       
        Permissao p1 = new Permissao("c1", "r1", "p1");
        Permissao p2 = new Permissao("c2", "r2", "p2");
        Permissao p3 = new Permissao("c3", "r3", "p3");
        Permissao p4 = new Permissao("c1", "r1", "p2");
        
        Set<Permissao> permissoes = permissaoDao.obter("r1", "p1");
        
        assertEquals(1, permissoes.size());
        assertTrue(permissoes.contains(p1));
        assertFalse(permissoes.contains(p4));
        assertFalse(permissoes.contains(p2));
        
        permissoes = permissaoDao.obter("r2", "p2");
        
        assertEquals(1, permissoes.size());
        assertTrue(permissoes.contains(p2));
        assertFalse(permissoes.contains(p1));
        
        permissoes = permissaoDao.obter("r3", "p3");
        
        assertEquals(1, permissoes.size());
        assertTrue(permissoes.contains(p3));
        assertFalse(permissoes.contains(p1));
          
       
        permissoes.clear();
        assertEquals(permissoes, permissaoDao.obter("inexistente", "inexistente"));
        assertEquals(permissoes, permissaoDao.obter("c1", "inexistente"));
        assertEquals(permissoes, permissaoDao.obter("inexistente", "r1"));
        assertEquals(permissoes, permissaoDao.obter("", "r1"));
        assertEquals(permissoes, permissaoDao.obter("", ""));
        assertEquals(permissoes, permissaoDao.obter(null, null));
    }

    /**
     * Test of obter method, of class PermissaoDao.
     */
    @Test
    public void test2Obter_Papel() throws Exception {
        System.out.println("obter");
        Permissao p1 = new Permissao("c1", "r1", "p1");
        Permissao p2 = new Permissao("c2", "r2", "p2");
        Permissao p3 = new Permissao("c3", "r3", "p3");
        Permissao p4 = new Permissao("c1", "r1", "p2");
        
        Set<Permissao> permissoes = permissaoDao.obter("p1");
        
        assertEquals(1, permissoes.size());
        assertTrue(permissoes.contains(p1));
        assertFalse(permissoes.contains(p4));
        assertFalse(permissoes.contains(p2));
        
        permissoes = permissaoDao.obter("p2");
        
        assertEquals(2, permissoes.size());
        assertTrue(permissoes.contains(p2));
        assertTrue(permissoes.contains(p4));
        assertFalse(permissoes.contains(p1));
        assertFalse(permissoes.contains(p3));
        
        permissoes = permissaoDao.obter("p3");
        
        assertEquals(1, permissoes.size());
        assertTrue(permissoes.contains(p3));
        assertFalse(permissoes.contains(p1));
          
       
        permissoes.clear();
        assertEquals(permissoes, permissaoDao.obter("inexistente"));
        assertEquals(permissoes, permissaoDao.obter(""));
        assertEquals(permissoes, permissaoDao.obter("c1"));
        assertEquals(permissoes, permissaoDao.obter(null));
    }
}
