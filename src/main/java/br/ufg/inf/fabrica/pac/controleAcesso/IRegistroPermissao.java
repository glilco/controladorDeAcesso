/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Permissao;
import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IRegistroPermissao {
    public boolean registraPermissao(String papel, String recurso, String contexto);
    public boolean removePermissao(String papel, String recurso, String contexto);
    public Set<Permissao> getPermissoes(String papel);
    public Set<Permissao> getPermissoes(String papel, String recurso);
    public Permissao getPermissao(String papel, String recurso, String contexto);
    
    
}
