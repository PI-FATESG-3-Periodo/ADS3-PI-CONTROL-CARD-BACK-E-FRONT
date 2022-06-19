/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

/**
 *
 * @author WsmGyn
 */
public enum FabricaDaoOrdenacaoDespesa{

    ID {
        @Override
        public DespesaDao ObterOrdenacao() {
            return new DespesaDaoOrdenaID();
        }


    },
    VALOR {
        @Override
        public DespesaDao ObterOrdenacao() {
            return new DespesaDaoOrdenaValor();
        }

    }, 
    DATA {
        @Override
        public DespesaDao ObterOrdenacao() {
            return new DespesaDaoOrdenaData();
        }

    };

    public abstract DespesaDao ObterOrdenacao();
}
