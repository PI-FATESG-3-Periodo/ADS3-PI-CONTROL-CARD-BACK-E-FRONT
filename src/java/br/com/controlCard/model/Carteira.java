/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.model;

/**
 *
 * @author WsmGyn
 */
public class Carteira {

    private int id_carteira;
    private double saldo_receitas = 0;
    private double saldo_despesas = 0;
    private double saldo_conta = 0;
    private double saldo_conta_deposito = 0;
    private double saldo_conta_saque = 0;
    private double saldo_poupanca = 0;
    private double saldo_cartao = 0;
    private Usuario usuario;
    private double saldo_atual = 0;

    public double getSaldo_conta_deposito() {
        return saldo_conta_deposito;
    }

    public void setSaldo_conta_deposito(double saldo_conta_deposito) {
        this.saldo_conta_deposito = saldo_conta_deposito;
    }

    public double getSaldo_conta_saque() {
        return saldo_conta_saque;
    }

    public void setSaldo_conta_saque(double saldo_conta_saque) {
        this.saldo_conta_saque = saldo_conta_saque;
    }

    
    public int getId_carteira() {
        return id_carteira;
    }

    public double getSaldo_conta() {
        return saldo_atual = saldo_conta + saldo_conta_deposito - saldo_conta_saque;
    }

    public void setSaldo_conta(double saldo_conta) {
        this.saldo_conta = saldo_conta;
    }
    
    

    public void setId_carteira(int id_carteira) {
        this.id_carteira = id_carteira;
    }

    public double getSaldo_atual() {
        return  saldo_receitas - saldo_despesas;
    }

    public void setSaldo_atual(double saldo_atual) {
        this.saldo_atual = saldo_atual;
    }

    public double getSaldo_receitas() {
        return saldo_receitas;
    }

    public void setSaldo_receitas(double saldo_receitas) {
        this.saldo_receitas = saldo_receitas;
    }

    public double getSaldo_despesas() {
        return saldo_despesas;
    }

    public void setSaldo_despesas(double saldo_despesas) {
        this.saldo_despesas = saldo_despesas;
    }

    public double getSaldo_poupanca() {
        return saldo_poupanca;
    }

    public void setSaldo_poupanca(double saldo_poupanca) {
        this.saldo_poupanca = saldo_poupanca;
    }

    public double getSaldo_cartao() {
        return saldo_cartao;
    }

    public void setSaldo_cartao(double saldo_cartao) {
        this.saldo_cartao = saldo_cartao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
