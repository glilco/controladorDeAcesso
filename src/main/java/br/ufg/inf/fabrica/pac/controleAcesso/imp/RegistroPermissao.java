/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.imp;

import br.ufg.inf.fabrica.pac.controleAcesso.IRegistroPermissao;
import br.ufg.inf.fabrica.pac.controleAcesso.dao.IPermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.dao.imp.Conexao;
import br.ufg.inf.fabrica.pac.controleAcesso.dao.imp.PermissaoDao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author murilo
 */
public class RegistroPermissao implements IRegistroPermissao {
    private IPermissaoDao permissaoDao;
    
    public RegistroPermissao() {
        try {
            permissaoDao = new PermissaoDao(new Conexao());
        } catch(SQLException ex) {
            throw new RuntimeException("Não foi possível criar Permissao Dao", ex);
        }
    }

    @Override
    public boolean registraPermissao(String papel, String recurso, String contexto) {
        try {
            return permissaoDao.salvar(new Permissao(contexto, recurso, papel));
        } catch(SQLException e) {
            return false;
        }
    }

    @Override
    public boolean removePermissao(String papel, String recurso, String contexto) {
        try {
            return permissaoDao.excluir(new Permissao(contexto, recurso, papel)) != null;
        } catch(SQLException e) {
            return false;
        }
    }

    @Override
    public Set<Permissao> getPermissoes(String papel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Permissao> getPermissoes(String papel, String recurso) {
        try {
            return permissaoDao.obter(recurso, papel);
        } catch(SQLException e) {
            return null;
        }
    }

    @Override
    public Permissao getPermissao(String papel, String recurso, String contexto) {
        try {
            return permissaoDao.obter(contexto, recurso, papel);
        } catch(SQLException e) {
            return null;
        }
    }
}
