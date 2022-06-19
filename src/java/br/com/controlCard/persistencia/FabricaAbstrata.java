/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

/**
 *
 * @author WsmGyn
 */
public enum FabricaAbstrata implements FabricaOrdenacao {

    ORDENARECEITA {
        @Override
        public FabricaDaoOrdenacaoReceita ObterFabricaReceita(String nome) {
            return FabricaDaoOrdenacaoReceita.valueOf(nome);
        }

        @Override
        public FabricaDaoOrdenacaoDespesa ObterFabricaDesepesa(String nome) {
            return null;
        }
    },
    ORDENADESPESA {
        @Override
        public FabricaDaoOrdenacaoDespesa ObterFabricaDesepesa(String nome) {
            return FabricaDaoOrdenacaoDespesa.valueOf(nome);
        }

        @Override
        public FabricaDaoOrdenacaoReceita ObterFabricaReceita(String nome) {
            return null;
        }
    };
}
