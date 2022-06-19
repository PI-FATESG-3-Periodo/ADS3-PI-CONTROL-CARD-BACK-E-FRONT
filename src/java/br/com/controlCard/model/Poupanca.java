package br.com.controlCard.model;

import java.util.Date;

public class Poupanca {

    private int id_poupanca;
    private double saldo;
    private Date data;
    private Conta conta;

    public int getId_poupanca() {
        return id_poupanca;
    }

    public void setId_poupanca(int id_poupanca) {
        this.id_poupanca = id_poupanca;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
