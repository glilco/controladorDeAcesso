package br.ufg.inf.fabrica.pac.seguranca;

import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;
import java.util.List;

/**
 *
 * @author Danillo
 */
public interface ControleAcesso {
    
    public boolean autorizar(String recurso, List<Papel> papeis);
    public boolean autorizaPapel(Papel papel, String recurso);
    public boolean desautorizaPapel(Papel papel, String recurso);
}
