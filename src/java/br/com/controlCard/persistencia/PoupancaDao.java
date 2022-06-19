package br.com.controlCard.persistencia;

import br.com.controlCard.model.Poupanca;
import br.com.controlCard.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoupancaDao {

    Connection connection;

    public PoupancaDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean Inserir(Poupanca poupanca) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO poupanca(saldo_poupanca, data_criacao_poupanca, fk_id_conta) VALUES(?,?,?) RETURNING id_poupanca");
            // Parameters start with 1
            preparedStatement.setDouble(1, poupanca.getSaldo());
            preparedStatement.setDate(2, new java.sql.Date(poupanca.getData().getTime()));
            preparedStatement.setInt(3, poupanca.getConta().getId_conta());
            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                poupanca.setId_poupanca(rs.getInt("id_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
