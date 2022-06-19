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
public class Postgres extends Conexao {

    public static Connection connection = null;

    @Override
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/ads3_pi";
                String user = "postgres";
                String password = "123456";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

}
