/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Carteira;
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
public class CarteiraDao {

    private Connection connection;

    public CarteiraDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean Inserir(int id_usuario) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO carteira (fk_id_usuario) values (?)");
            // Parameters start with 1
            preparedStatement.setInt(1, id_usuario);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Remover(int id_carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM carteira WHERE id_carteira=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_carteira);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void AlterarSaldoAtual(Carteira carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE carteira SET saldo_atual=? WHERE id_carteira=?");
            preparedStatement.setDouble(1, carteira.getSaldo_atual());
            preparedStatement.setInt(2, carteira.getId_carteira());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AlterarSaldoReceita(Carteira carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE carteira SET saldo_receita=? WHERE id_carteira=?");
            preparedStatement.setDouble(1, carteira.getSaldo_receitas());
            preparedStatement.setInt(2, carteira.getId_carteira());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AlterarSaldoDespesa(Carteira carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE carteira SET saldo_despesa=? WHERE id_carteira=?");
            preparedStatement.setDouble(1, carteira.getSaldo_despesas());
            preparedStatement.setInt(2, carteira.getId_carteira());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AlterarSaldoCartao(Carteira carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE carteira SET saldo_cartao=? WHERE id_carteira=?");
            preparedStatement.setDouble(1, carteira.getSaldo_cartao());
            preparedStatement.setInt(2, carteira.getId_carteira());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AlterarSaldoPoupanca(Carteira carteira) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE carteira SET saldo_poupanca=? WHERE id_carteira=?");
            preparedStatement.setDouble(1, carteira.getSaldo_poupanca());
            preparedStatement.setInt(2, carteira.getId_carteira());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Carteira> ConsultarTodos() {
//        List<Carteira> carteiraList = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("select carteira.id_carteira, carteira.saldo_atual, carteira.saldo_receita, carteira.saldo_despesa, carteira.saldo_cartao, carteira.saldo_poupanca FROM carteira, usuario WHERE  usuario.id_usuario =carteira.fk_id_usuario");
//            while (rs.next()) {
//                Carteira carteira = new Carteira();
//                carteira.setId_carteira(rs.getInt("id_carteira"));
//                carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
//                carteira.setSaldo_receitas(rs.getDouble("saldo_receita"));
//                carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
//                carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
//                carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
//                carteiraList.add(carteira);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return carteiraList;
//    }
    public List<Carteira> ConsultarTodos() {
        List<Carteira> carteiraList = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM carteira ORDER BY id_carteira ASC");
            while (rs.next()) {
                Carteira carteira = new Carteira();
                carteira.setId_carteira(rs.getInt("id_carteira"));
                //carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_receitas(rs.getDouble("saldo_receita"));
                carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
                carteira.setUsuario(usuarioDao.ConsultarUsuario(rs.getInt("fk_id_usuario")));
                carteiraList.add(carteira);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteiraList;
    }

    public Carteira ConsultarCarteira(int id_carteira) {
        Carteira carteira = new Carteira();
        UsuarioDao usuarioDao = new UsuarioDao();
        String sql = "SELECT * FROM carteira WHERE carteira.id_carteira= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_carteira);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carteira.setId_carteira(rs.getInt("id_carteira"));
                //carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                // carteira.setSaldo_receitas(rs.getDouble("saldo_receita"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
                carteira.setUsuario(usuarioDao.ConsultarUsuario(rs.getInt("fk_id_usuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarCarteiraPorUsuario(Carteira carteira) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String sql = "SELECT carteira.id_carteira, carteira.saldo_atual, carteira.saldo_receita, carteira.saldo_despesa, carteira.saldo_cartao, carteira.saldo_poupanca, carteira.fk_id_usuario FROM carteira INNER JOIN usuario ON  (usuario.id_usuario =carteira.fk_id_usuario and usuario.id_usuario = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                // carteira.setSaldo_receitas(rs.getDouble("saldo_receita"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
                carteira.setUsuario(usuarioDao.ConsultarUsuario(rs.getInt("fk_id_usuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarReceitaPorCarteira(Carteira carteira) {
        //Carteira carteira = new Carteira();
        String sql = "SELECT sum(receita.valor) FROM receita INNER JOIN carteira ON  (carteira.id_carteira = receita.fk_id_carteira and carteira.id_carteira = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getId_carteira());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_receitas(rs.getDouble("sum"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarDespesaPorCarteira(Carteira carteira) {
        //Carteira carteira = new Carteira();
        String sql = "SELECT sum(despesa.valor) FROM despesa INNER JOIN carteira ON  (carteira.id_carteira = despesa.fk_id_carteira and carteira.id_carteira = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getId_carteira());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_despesas(rs.getDouble("sum"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarSaquePorCarteira(Carteira carteira) {
        //Carteira carteira = new Carteira();
        String sql = "SELECT sum(movimentacao.valor_mov) FROM movimentacao "
                + "INNER JOIN conta ON conta.id_conta = movimentacao.fk_id_conta "
                + "INNER JOIN carteira ON carteira.id_carteira = conta.fk_id_carteira and carteira.fk_id_usuario = ? "
                + "WHERE movimentacao.tipo_mov = ('Sacado')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_conta_saque(rs.getDouble("sum"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarDepositoPorCarteira(Carteira carteira) {
        //Carteira carteira = new Carteira();
        String sql = "SELECT sum(movimentacao.valor_mov) FROM movimentacao "
                + "INNER JOIN conta ON conta.id_conta = movimentacao.fk_id_conta "
                + "INNER JOIN carteira ON carteira.id_carteira = conta.fk_id_carteira and carteira.fk_id_usuario = ? "
                + "WHERE movimentacao.tipo_mov = ('Depositado')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_conta_deposito(rs.getDouble("sum"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }

    public Carteira ConsultarLimiteContaPorCarteira(Carteira carteira) {
        //Carteira carteira = new Carteira();
        String sql = "SELECT conta.limite FROM conta  INNER JOIN carteira ON carteira.id_carteira = conta.fk_id_carteira AND carteira.fk_id_usuario =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carteira.getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // carteira.setId_carteira(rs.getInt("id_carteira"));
                // carteira.setSaldo_atual(rs.getDouble("saldo_atual"));
                carteira.setSaldo_conta(rs.getDouble("limite"));
                // carteira.setSaldo_despesas(rs.getDouble("saldo_despesa"));
                // carteira.setSaldo_cartao(rs.getDouble("saldo_cartao"));
                // carteira.setSaldo_poupanca(rs.getDouble("saldo_poupanca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carteira;
    }
}
