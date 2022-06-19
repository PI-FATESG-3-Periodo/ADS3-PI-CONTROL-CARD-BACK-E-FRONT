/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.util;

/**
 *
 * @author WsmGyn
 */
public enum FabricaConexao {

    POSTEGRES {
        @Override
        public Conexao ObterConexao() {
            return new Postgres();
        }

    }, MYSQL {
        @Override
        public Conexao ObterConexao() {
            return new MySQL();
        }

    };

    public abstract Conexao ObterConexao();
}
