/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.modelo;

import java.util.Objects;

/**
 *
 * @author murilo
 */
public class Permissao {
    private Contexto contexto;
    private Recurso recurso;
    private Papel papel;

    public Permissao(Contexto contexto, Recurso recurso, Papel papel) {
        this.contexto = contexto;
        this.recurso = recurso;
        this.papel = papel;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }


    public Contexto getContexto() {
        return contexto;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public Papel getPapel() {
        return papel;
    }
    
   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.contexto);
        hash = 53 * hash + Objects.hashCode(this.recurso);
        hash = 53 * hash + Objects.hashCode(this.papel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permissao other = (Permissao) obj;
        if (!Objects.equals(this.contexto, other.contexto)) {
            return false;
        }
        if (!Objects.equals(this.recurso, other.recurso)) {
            return false;
        }
        if (!Objects.equals(this.papel, other.papel)) {
            return false;
        }
        return true;
    }
    
}
