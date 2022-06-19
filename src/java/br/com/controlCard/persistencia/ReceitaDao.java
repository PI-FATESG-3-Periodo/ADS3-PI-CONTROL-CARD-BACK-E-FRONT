/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Receita;
import br.com.controlCard.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author WsmGyn
 */
public abstract class ReceitaDao {

    private Connection connection;

    public abstract boolean ePrimeiro(Receita r1, Receita r2);

    public ReceitaDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean Inserir(Receita receita) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO receita (valor,data,descricao,fk_id_carteira,fk_id_categoria) values (?, ?, ?, ? ,?) RETURNING id_receita");
            // Parameters start with 1
            preparedStatement.setDouble(1, receita.getValor());
            preparedStatement.setDate(2, new java.sql.Date(receita.getData().getTime()));
            preparedStatement.setString(3, receita.getDescricao());
            preparedStatement.setInt(4, receita.getCarteira().getId_carteira());
            preparedStatement.setInt(5, receita.getCategoria().getId_categoria());

            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                receita.setId_receita(rs.getInt("id_receita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Alterar(Receita receita) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE receita SET valor=?, data=?, descricao=?, fk_id_carteira=?, fk_id_categoria=?"
                            + "where id_receita=?");
            preparedStatement.setDouble(1, receita.getValor());
            preparedStatement.setDate(2, new java.sql.Date(receita.getData().getTime()));
            preparedStatement.setString(3, receita.getDescricao());
            preparedStatement.setInt(4, receita.getCarteira().getId_carteira());
            preparedStatement.setInt(5, receita.getCategoria().getId_categoria());
            preparedStatement.setInt(6, receita.getId_receita());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Remover(int id_receita) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM receita WHERE id_receita=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_receita);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Receita ConsultarReceita(int id_receita) {
        Receita receita = new Receita();
        CarteiraDao carteriaDao = new CarteiraDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        String sql = "SELECT * FROM receita WHERE receita.id_receita = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_receita);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receita.setId_receita(rs.getInt("id_receita"));
                receita.setValor(rs.getDouble("valor"));
                receita.setData(rs.getDate("data"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setCarteira(carteriaDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                receita.setCategoria(categoriaDao.ConsultarCategoria(rs.getInt("fk_id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receita;
    }

    public List<Receita> ConsultarTodasReceitaUsuario(Receita receita) {
        List<Receita> receitaList = new ArrayList<>();
        CarteiraDao carteira = new CarteiraDao();
        CategoriaDao categoria = new CategoriaDao();
        try {
            String sql = ("SELECT receita.id_receita, receita.valor, receita.data, receita.descricao , receita.fk_id_carteira, receita.fk_id_categoria\n"
                    + "FROM receita INNER JOIN carteira ON  (carteira.id_carteira = receita.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
                    + "ORDER BY receita.id_receita ASC");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, receita.getCarteira().getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receita rec = new Receita();
                rec.setId_receita(rs.getInt("id_receita"));
                rec.setValor(rs.getDouble("valor"));
                rec.setData(rs.getDate("data"));
                rec.setDescricao(rs.getString("descricao"));
                rec.setCarteira(carteira.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                rec.setCategoria(categoria.ConsultarCategoria(rs.getInt("fk_id_categoria")));
                receitaList.add(rec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receitaList;
    }

    public Iterator ConsultarReceitaPorData(Receita receita, Date dataInicio, Date dataFim) {
        ArrayList<Receita> receitaList = new ArrayList<>();
        String sql = ("SELECT receita.id_receita, receita.valor, receita.data, receita.descricao , receita.fk_id_carteira, receita.fk_id_categoria\n"
                + "FROM receita INNER JOIN carteira ON  (carteira.id_carteira = receita.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
                + "WHERE receita.data BETWEEN ? AND ? \n"
                + "ORDER BY receita.data ASC");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, receita.getCarteira().getUsuario().getId_usuario());
            ps.setDate(2, new java.sql.Date(dataInicio.getTime()));
            ps.setDate(3, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Receita rec = new Receita();
                rec.setId_receita(rs.getInt("id_receita"));
                rec.setValor(rs.getDouble("valor"));
                rec.setData(rs.getDate("data"));
                rec.setDescricao(rs.getString("descricao"));
                //rec.getCarteira().setId_carteira((rs.getInt("fk_id_carteira")));
                //rec.getCategoria().setId_categoria(rs.getInt("fk_id_categoria"));
                receitaList.add(rec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receitaList.iterator();
    }

    public List<Receita> ConsultarReceitaPorDataArrayList(Receita receita, Date dataInicio, Date dataFim) {
        List<Receita> receitaList = new ArrayList<>();
        CarteiraDao carteiraDao = new CarteiraDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        try {
            String sql = ("SELECT receita.id_receita, receita.valor, receita.data, receita.descricao , receita.fk_id_carteira, receita.fk_id_categoria\n"
                    + "FROM receita INNER JOIN carteira ON  (carteira.id_carteira = receita.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
                    + "WHERE receita.data BETWEEN ? AND ? \n"
                    + "ORDER BY receita.data ASC");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, receita.getCarteira().getUsuario().getId_usuario());
            ps.setDate(2, new java.sql.Date(dataInicio.getTime()));
            ps.setDate(3, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receita rec = new Receita();
                rec.setId_receita(rs.getInt("id_receita"));
                rec.setValor(rs.getDouble("valor"));
                rec.setData(rs.getDate("data"));
                rec.setDescricao(rs.getString("descricao"));
                rec.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                rec.setCategoria(categoriaDao.ConsultarCategoria(rs.getInt("fk_id_categoria")));
                receitaList.add(rec);
            }
            for (int i = 0; i < receitaList.size(); i++) {
                for (int j = i; j < receitaList.size(); j++) {
                    if (!ePrimeiro(receitaList.get(i), receitaList.get(j))) {
                        Receita temp = receitaList.get(j);
                        receitaList.set(j, receitaList.get(i));
                        receitaList.set(i, temp);
                    }
                }
            }
            // return receitaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receitaList;
    }

}
