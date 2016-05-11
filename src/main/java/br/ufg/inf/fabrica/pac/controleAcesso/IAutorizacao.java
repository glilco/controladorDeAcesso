/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso;

import java.util.Set;

/**
 *
 * @author murilo
 */
public interface IAutorizacao {
    public boolean verificaAutorizacao(Set<String> papeis, String recurso);
    public boolean verificaAutorizacao(Set<String> papeis, String recurso, String contexto);
}
