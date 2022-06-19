package br.com.controlCard.persistencia;

import br.com.controlCard.model.Despesa;

/**
 *
 * @author WsmGyn
 */
public class DespesaDaoOrdenaID extends DespesaDao {

    @Override
    public boolean ePrimeiro(Despesa d1, Despesa d2) {
        return d1.getId_despesa() < d2.getId_despesa();
    }
}
