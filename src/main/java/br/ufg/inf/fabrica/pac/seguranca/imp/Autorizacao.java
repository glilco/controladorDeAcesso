/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.imp;

import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author murilo
 */
public class Autorizacao {
    private Map<Papel, Set<String>> papelRecurso;
    
    private static Autorizacao autorizacao;
    private ControleAcessoDao cad;

    private Autorizacao() {
        papelRecurso = new HashMap<Papel, Set<String>>();
        
        try {
            cad = ControleAcessoDao.getInstance();
                        
            /*for(Papel papel:Papel.values()) {
                papelRecurso.put(papel, cad.getRecursosAutorizados(papel));
            }          */

            
        } catch(SQLException e) {
            throw new RuntimeException("Não foi possível criar DAO de controle de acesso", e);
        }
    }
    
    public static Autorizacao getInstance() {
        if(autorizacao == null) {
            autorizacao = new Autorizacao();
        }
        
        return autorizacao;
    }
    
    
    public boolean autoriza(Papel papel, String recurso) {
        Set<String> recursosPapel = null;
        if(papelRecurso.containsKey(papel)) {
            recursosPapel = papelRecurso.get(papel);
        } else {
            recursosPapel = new HashSet<String>();
            papelRecurso.put(papel, recursosPapel);
        }
        
        try {
            cad.insereAutorizacao(papel, recurso);
            return recursosPapel.add(recurso);
        } catch(SQLException ex) {
            throw new RuntimeException("Não foi possível inserir autorização no banco", ex);
        }
    }
    
    public boolean desautoriza(Papel papel, String recurso) {
        
        if(papelRecurso.containsKey(papel)) {
            Set<String> recursosPapel = papelRecurso.get(papel);
            Boolean resposta = false;
            try {
                cad.removeAutorizacao(papel, recurso);
                resposta = recursosPapel.remove(recurso);
                return resposta;
            } catch(SQLException ex) {
                throw new RuntimeException("Não foi possível remover autorização do banco", ex);
            }
        }
        
        return false;
    }
    
    public boolean verificaAutorizacao(Papel papel, String recurso) {
        if(papelRecurso.containsKey(papel)) {
            return papelRecurso.get(papel).contains(recurso);
        }
        return false;
    }
}
