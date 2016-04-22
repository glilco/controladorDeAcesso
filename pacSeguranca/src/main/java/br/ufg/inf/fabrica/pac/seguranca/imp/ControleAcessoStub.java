package br.ufg.inf.fabrica.pac.seguranca.imp;

import br.ufg.inf.fabrica.pac.dominio.enums.Papel;
import java.util.ArrayList;
import java.util.List;
import br.ufg.inf.fabrica.pac.seguranca.ControleAcesso;

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
        papeis.add(Papel.GPR);
        
        System.out.println(cas.autorizar("Acesso1", papeis));
        System.out.println(cas.autorizar("Acesso2", papeis));
        System.out.println(cas.autorizar("Acesso3", papeis));
        
        cas.autorizaPapel(Papel.GPR, "Acesso1");
        cas.autorizaPapel(Papel.GPR, "Acesso2");
        cas.autorizaPapel(Papel.GPR, "Acesso3");
        
        System.out.println(cas.autorizar("Acesso1", papeis));
        System.out.println(cas.autorizar("Acesso2", papeis));
        System.out.println(cas.autorizar("Acesso3", papeis));
        
        cas.desautorizaPapel(Papel.GPR, "Acesso1");
        cas.desautorizaPapel(Papel.GPR, "Acesso2");
        cas.desautorizaPapel(Papel.GPR, "Acesso3");
        
        System.out.println(cas.autorizar("Acesso1", papeis));
        System.out.println(cas.autorizar("Acesso2", papeis));
        System.out.println(cas.autorizar("Acesso3", papeis));
        
    }

}
