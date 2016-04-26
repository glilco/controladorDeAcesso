/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.dao;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IPermissaoDao extends IDao<Permissao>{
    public Permissao obter(Contexto contexto, Recurso recurso, Papel papel) throws SQLException;
    public Set<Permissao> obter(Recurso recurso, Papel papel) throws SQLException;
    public Set<Permissao> obter(Papel papel) throws SQLException;
}
