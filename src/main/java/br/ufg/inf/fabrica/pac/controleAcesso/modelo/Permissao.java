/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.controleAcesso.modelo;

import java.util.Objects;

/**
 *
 * @author murilo
 */
public class Permissao {
    private String contexto;
    private String recurso;
    private String papel;
    private int id;

    public Permissao(String contexto, String recurso, String papel) {
        this.contexto = contexto;
        this.recurso = recurso;
        this.papel = papel;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }


    public String getContexto() {
        return contexto;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getPapel() {
        return papel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "("+ contexto + ", " + recurso + ", " + papel +")";
    }
    
}
