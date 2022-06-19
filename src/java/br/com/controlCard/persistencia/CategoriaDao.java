/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Categoria;
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
public class CategoriaDao {

    private Connection connection;

    public CategoriaDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean Inserir(Categoria categoria) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO categoria(descricao) values (? ) RETURNING id_categoria");
            // Parameters start with 1
            preparedStatement.setString(1, categoria.getDescricao());

            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ConsultarCategoria(rs.getInt("id_categoria"));
                //id=rs.getInt("id_usuario");
                categoria.setId_categoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Alterar(Categoria categoria) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE categoria SET descricao=? where id_categoria=?");
            preparedStatement.setString(1, categoria.getDescricao());
            preparedStatement.setInt(2, categoria.getId_categoria());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Remover(int id_categoria) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM categoria WHERE id_categoria=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_categoria);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Categoria ConsultarCategoria(int id_categoria) {
        Categoria categoria = new Categoria();
        String sql = "SELECT * FROM categoria WHERE categoria.id_categoria = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public List<Categoria> ConsultarTodos() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from categoria ORDER BY id_categoria");
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

}
