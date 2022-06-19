package br.com.controlCard.persistencia;

import br.com.controlCard.model.Conta;
import br.com.controlCard.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {

    Connection connection;

    public ContaDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean InserirConta(Conta conta) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO conta (numero_conta, limite, saldo_conta, data_criacao, fk_id_carteira, fk_id_banco) values (?, ?, ?, ? ,?,?)"
                            + " RETURNING id_conta");
            // Parameters start with 1
            preparedStatement.setInt(1, conta.getNumero_conta());
            preparedStatement.setDouble(2, conta.getLimite());
            preparedStatement.setDouble(3, conta.getSaldo_conta());
            preparedStatement.setDate(4, new java.sql.Date(conta.getData_criacao().getTime()));
            preparedStatement.setInt(5, conta.getCarteira().getId_carteira());
            preparedStatement.setInt(6, conta.getBanco().getId_banco());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                conta.setId_conta(rs.getInt("id_conta"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void Remover(int id_conta) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM conta WHERE id_conta=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_conta);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conta ConsultarConta(int id_conta) {
        Conta conta = new Conta();
        CarteiraDao carteiraDao = new CarteiraDao();
        BancoDao bancoDao = new BancoDao();
        String sql = "SELECT * FROM conta WHERE conta.id_conta = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_conta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conta.setId_conta(rs.getInt("id_conta"));
                conta.setNumero_conta(rs.getInt("numero_conta"));
                conta.setLimite(rs.getDouble("limite"));
                conta.setSaldo_conta(rs.getDouble("saldo_conta"));
                conta.setData_criacao(rs.getDate("data_criacao"));
                conta.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                conta.setBanco(bancoDao.ConsultarBanco(rs.getInt("fk_id_banco")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conta;
    }

    public Conta ConsultarContaPorCarteira(Conta conta) {
        CarteiraDao carteiraDao = new CarteiraDao();
        BancoDao bancoDao = new BancoDao();
        String sql = "SELECT conta.id_conta, conta.numero_conta, conta.limite,conta.saldo_conta,conta.data_criacao, conta.fk_id_carteira, conta.fk_id_banco\n"
                + "FROM conta INNER JOIN carteira ON  (carteira.id_carteira = conta.fk_id_carteira and carteira.fk_id_usuario =?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, conta.getCarteira().getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conta.setId_conta(rs.getInt("id_conta"));
                conta.setNumero_conta(rs.getInt("numero_conta"));
                conta.setLimite(rs.getDouble("limite"));
                conta.setSaldo_conta(rs.getDouble("saldo_conta"));
                conta.setData_criacao(rs.getDate("data_criacao"));
                conta.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                conta.setBanco(bancoDao.ConsultarBanco(rs.getInt("fk_id_banco")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conta;
    }

    public List<Conta> ConsultarTodos() {
        List<Conta> listaDeContas = new ArrayList<>();
        CarteiraDao carteiraDao = new CarteiraDao();
        BancoDao bancoDao = new BancoDao();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM conta ORDER BY id_conta");
            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId_conta(rs.getInt("id_conta"));
                conta.setNumero_conta(rs.getInt("numero_conta"));
                conta.setLimite(rs.getDouble("limite"));
                conta.setSaldo_conta(rs.getDouble("saldo_conta"));
                conta.setData_criacao(rs.getDate("data_criacao"));
                conta.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                conta.setBanco(bancoDao.ConsultarBanco(rs.getInt("fk_id_banco")));
                listaDeContas.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeContas;
    }

    public void AlterarSaldo(Conta conta) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE conta SET saldo=? WHERE id_conta=?");
            preparedStatement.setDouble(1, conta.getSaldo_conta());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
