package br.com.controlCard.model;

import java.util.Date;

public class Mov_Conta {

    private int id_mov_conta;
    private String tipo_mov;
    private double valor_mov;
    private Date data_mov;
    private Conta conta;

    public int getId_mov_conta() {
        return id_mov_conta;
    }

    public void setId_mov_conta(int id_mov_conta) {
        this.id_mov_conta = id_mov_conta;
    }

    public String getTipo_mov() {
        return tipo_mov;
    }

    public void setTipo_mov(String tipo_mov) {
        this.tipo_mov = tipo_mov;
    }

    public Date getData_mov() {
        return data_mov;
    }

    public void setData_mov(Date data_mov) {
        this.data_mov = data_mov;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public double getValor_mov() {
        return valor_mov;
    }

    public void setValor_mov(double valor_mov) {
        this.valor_mov = valor_mov;
    }

}
