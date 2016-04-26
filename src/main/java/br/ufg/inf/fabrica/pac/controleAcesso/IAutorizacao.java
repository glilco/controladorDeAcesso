/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso;

import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Contexto;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Papel;
import br.ufg.inf.fabrica.pac.controleAcesso.modelo.Recurso;
import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IAutorizacao {
    public boolean verificaAutorizacao(Set<Papel> papeis, Recurso recurso);
    public boolean verificaAutorizacao(Set<Papel> papeis, Recurso recurso, Contexto contexto);
}
