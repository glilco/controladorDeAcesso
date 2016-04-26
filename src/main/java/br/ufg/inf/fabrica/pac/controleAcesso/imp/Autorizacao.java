/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.IAutorizacao;
import br.ufg.inf.fabrica.pac.controleAcesso.dao.IPermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.dao.imp.PermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author murilo
 */
public class Autorizacao implements IAutorizacao {
    private IPermissaoDao permissaoDao;

    public Autorizacao() {
        try {
        permissaoDao = new PermissaoDao();
        } catch(SQLException ex) {
            throw  new RuntimeException("Não foi possível criar Permissao Dao ", ex);
        }
    }

    @Override
    public boolean verificaAutorizacao(Set<Papel> papeis, Recurso recurso) {
        try {
            for(Papel papel: papeis) {
                if(!permissaoDao.obter(recurso, papel).isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch(Exception ex) {
            throw new RuntimeException("Erro ao acessar autorizacoes", ex);
        }
    }

    @Override
    public boolean verificaAutorizacao(Set<Papel> papeis, Recurso recurso, Contexto contexto) {
        try {
            for(Papel papel: papeis) {
                if(permissaoDao.obter(contexto, recurso, papel) != null) {
                    return true;
                }
            }
            return false;
        
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao acessar autorizacoes", e);
        }
    }
}
