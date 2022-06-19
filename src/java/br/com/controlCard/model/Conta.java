/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.model;

import java.util.Date;

public class Conta {

    private int id_conta;
    private int numero_conta;
    private Double limite;
    private Double saldo_conta;
    private Date data_criacao;
    private Carteira carteira;
    private Banco banco;
    private Mov_Conta mov_conta;

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public int getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(int numero_conta) {
        this.numero_conta = numero_conta;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getSaldo_conta() {
        return saldo_conta;
    }

    public void setSaldo_conta(Double saldo_conta) {
        this.saldo_conta = saldo_conta;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Mov_Conta getMov_conta() {
        return mov_conta;
    }

    public void setMov_conta(Mov_Conta mov_conta) {
        this.mov_conta = mov_conta;
    }
    
}
