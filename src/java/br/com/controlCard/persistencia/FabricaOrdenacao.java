/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.controlCard.persistencia;

/**
 *
 * @author WsmGyn
 */
public interface FabricaOrdenacao {

    public abstract  FabricaDaoOrdenacaoReceita ObterFabricaReceita(String nome);

    public abstract  FabricaDaoOrdenacaoDespesa ObterFabricaDesepesa(String nome);
}
