/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IPermissaoDao extends IDao<Permissao>{
    public Permissao obter(String contexto, String recurso, String papel) throws SQLException;
    public Set<Permissao> obter(String recurso, String papel) throws SQLException;
    public Set<Permissao> obter(String papel) throws SQLException;
}
