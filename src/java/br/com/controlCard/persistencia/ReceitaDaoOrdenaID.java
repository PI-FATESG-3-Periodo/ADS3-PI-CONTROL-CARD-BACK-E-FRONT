package br.com.controlCard.persistencia;

import br.com.controlCard.model.Receita;



/**
 *
 * @author WsmGyn
 */
public class ReceitaDaoOrdenaID extends ReceitaDao{

    @Override
    public boolean ePrimeiro(Receita r1, Receita r2) {
        return r1.getId_receita()<r2.getId_receita();
    }

}
