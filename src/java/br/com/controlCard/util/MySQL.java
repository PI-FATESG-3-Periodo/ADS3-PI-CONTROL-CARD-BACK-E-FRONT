/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WsmGyn
 */
public class MySQL extends Conexao {

    public static Connection connection = null;

    @Override
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                // Informamos qual o Driver que está sendo utilizado
                String DRIVER = "com.mysql.cj.jdbc.Driver";
                String URL = "jdbc:mysql://localhost:3306/pet?useTimezone=true&serverTimezone=UTC";
                String USER = "root";
                String PASSWORD = "senha";
                // O método forName carrega e inicia o driver passado por parâmetro

                Class.forName(DRIVER);
                // Estabelecendo a conexão

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) { // Tratamento de Exceções
                System.out.println(ex);

            }
        }
        return connection;
    }

}
