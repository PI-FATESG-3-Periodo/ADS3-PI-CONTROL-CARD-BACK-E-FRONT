/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Mov_Conta;
import br.com.controlCard.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WsmGyn
 */
public class MovimentacaoDao {

    Connection connection;

    public MovimentacaoDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean inserir_mov(Mov_Conta mov) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO movimentacao(tipo_mov, data_mov, valor_mov, fk_id_conta ) VALUES(?,?,?,?) RETURNING id_mov");
            // Parameters start with 1
            preparedStatement.setString(1, mov.getTipo_mov());
            preparedStatement.setDate(2, new java.sql.Date(mov.getData_mov().getTime()));
            preparedStatement.setDouble(3, mov.getValor_mov());
            preparedStatement.setInt(4, mov.getConta().getId_conta());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                mov.setId_mov_conta(rs.getInt("id_mov"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Mov_Conta> ConsultarTodasMovimentacao() {
        List<Mov_Conta> listaDeMOv = new ArrayList<>();
        ContaDao contaDao = new ContaDao();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM movimentacao ORDER BY id_mov");
            while (rs.next()) {
                Mov_Conta mov = new Mov_Conta();
                mov.setId_mov_conta(rs.getInt("id_mov"));
                mov.setTipo_mov(rs.getString("tipo_mov"));
                mov.setData_mov(rs.getDate("data_mov"));
                mov.setValor_mov(rs.getDouble("valor_mov"));
                mov.setConta(contaDao.ConsultarConta(rs.getInt("fk_id_conta")));
                listaDeMOv.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeMOv;
    }
}
