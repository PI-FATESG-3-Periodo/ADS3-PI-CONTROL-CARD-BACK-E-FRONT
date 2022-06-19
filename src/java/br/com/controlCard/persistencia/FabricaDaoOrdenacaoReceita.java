/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

/**
 *
 * @author WsmGyn
 */
public enum FabricaDaoOrdenacaoReceita {

    ID {
        @Override
        public ReceitaDao ObterOrdenacao() {
            return new ReceitaDaoOrdenaID();
        }

    },
    VALOR {
        @Override
        public ReceitaDao ObterOrdenacao() {
            return new ReceitaDaoOrdenaValor();
        }

    }, 
    DATA {
        @Override
        public ReceitaDao ObterOrdenacao() {
            return new ReceitaDaoOrdenaData();
        }

    };

    public abstract ReceitaDao ObterOrdenacao();
}
