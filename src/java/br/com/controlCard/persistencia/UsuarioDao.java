/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Usuario;
import br.com.controlCard.util.FabricaConexao;
import br.com.controlCard.validation.ValidaCPFeString;
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
public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public boolean Inserir(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO usuario(nome,email,cpf,senha) values (?, ?, ?, ? ) RETURNING id_usuario");
            // Parameters start with 1
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCpf());
            preparedStatement.setString(4, usuario.getSenha());
            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                usuario.setId_usuario(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Alterar(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE usuario SET nome=?, email=?, cpf=?, senha=?"
                            + " WHERE id_usuario=?");
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCpf());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setInt(5, usuario.getId_usuario());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Remover(int id_usuario) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM usuario WHERE id_usuario=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_usuario);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Usuario ConsultarUsuario(int id_usuario) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE usuario.id_usuario = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                String cpf = rs.getString("cpf");
                usuario.setCpf(ValidaCPFeString.imprimeCPF(cpf));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> ConsultarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usuario ORDER BY id_usuario ASC");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                String cpf = rs.getString("cpf");
                usuario.setCpf(ValidaCPFeString.imprimeCPF(cpf));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
