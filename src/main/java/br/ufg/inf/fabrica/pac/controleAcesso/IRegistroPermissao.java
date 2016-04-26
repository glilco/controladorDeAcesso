/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IRegistroPermissao {
    public boolean registraPermissao(Papel papel, Recurso recurso, Contexto contexto);
    public boolean removePermissao(Papel papel, Recurso recurso, Contexto contexto);
    public Set<Permissao> getPermissoes(Papel papel);
    public Set<Permissao> getPermissoes(Papel papel, Recurso recurso);
    public Permissao getPermissao(Papel papel, Recurso recurso, Contexto contexto);
    
}
