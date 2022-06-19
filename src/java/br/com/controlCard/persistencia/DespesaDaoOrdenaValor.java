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
public class DespesaDaoOrdenaValor extends DespesaDao {

    @Override
    public boolean ePrimeiro(Despesa d1, Despesa d2) {
        return d1.getValor() < d2.getValor();
    }
}
