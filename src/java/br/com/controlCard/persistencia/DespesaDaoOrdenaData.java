/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Despesa;

/**
 *
 * @author WsmGyn
 */
public class DespesaDaoOrdenaData extends DespesaDao {

    @Override
    public boolean ePrimeiro(Despesa d1, Despesa d2) {
        return d1.getData().before(d2.getData());
    }
}
