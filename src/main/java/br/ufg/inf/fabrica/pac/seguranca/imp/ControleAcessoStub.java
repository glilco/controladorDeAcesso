package br.ufg.inf.fabrica.pac.seguranca.imp;

import java.util.ArrayList;
import java.util.List;
import br.ufg.inf.fabrica.pac.seguranca.ControleAcesso;
import br.ufg.inf.fabrica.pac.seguranca.modelo.Papel;

public class ControleAcessoStub implements ControleAcesso {

    private static ControleAcesso controleAcesso;
    private Autorizacao autorizacao;

    private ControleAcessoStub() {
        autorizacao = Autorizacao.getInstance();

    }
    

    public static ControleAcesso getInstance() {
        if (controleAcesso == null) {
            controleAcesso = new ControleAcessoStub();
        }
        return controleAcesso;
    }

    @Override
    public boolean autorizar(String recurso, List<Papel> papeis) {
        for(Papel papel : papeis) {
            if(autorizacao.verificaAutorizacao(papel, recurso)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean autorizaPapel(Papel papel, String recurso) {
        return autorizacao.autoriza(papel, recurso);
    }

    @Override
    public boolean desautorizaPapel(Papel papel, String recurso) {
        return autorizacao.desautoriza(papel, recurso);
    }
    
    
    
    public static void main(String[] args) {
        ControleAcesso cas = ControleAcessoStub.getInstance();
        List<Papel> papeis = new ArrayList<Papel>();
        
    }

}
