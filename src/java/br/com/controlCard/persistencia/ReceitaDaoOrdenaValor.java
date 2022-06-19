/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Receita;



/**
 *
 * @author WsmGyn
 */
public class ReceitaDaoOrdenaValor extends ReceitaDao {

    @Override
    public boolean ePrimeiro(Receita r1, Receita r2) {
        return r1.getValor()<r2.getValor();
    }


}
