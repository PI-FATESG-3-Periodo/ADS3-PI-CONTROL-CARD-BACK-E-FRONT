/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Banco;
import br.com.controlCard.model.BancoHashMap;
import br.com.controlCard.util.FabricaConexao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BancoDao {
    
    Connection connection;
    
    public BancoDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }
    
    public boolean Inserir(Banco banco) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO banco(nome, codigo_banco, agencia) VALUES(?,?,?) RETURNING id_banco");
            // Parameters start with 1
            preparedStatement.setString(1, banco.getNome());
            preparedStatement.setString(2, banco.getCodigo_banco());
            preparedStatement.setString(3, banco.getAgencia());

            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                banco.setId_banco(rs.getInt("id_banco"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean Alterar(Banco banco) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE categoria SET nome=?, codigo_banco=?, agencia=? WHERE id_banco=?");
            preparedStatement.setString(1, banco.getNome());
            preparedStatement.setString(2, banco.getCodigo_banco());
            preparedStatement.setString(3, banco.getAgencia());
            preparedStatement.setInt(4, banco.getId_banco());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean Remover(int id_banco) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM banco WHERE id_banco=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_banco);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Banco ConsultarBanco(int id_banco) {
        Banco banco = new Banco();
        String sql = "SELECT * FROM banco WHERE banco.id_banco = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_banco);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                banco.setId_banco(rs.getInt("id_banco"));
                banco.setCodigo_banco(rs.getString("codigo_banco"));
                banco.setNome(rs.getString("nome"));
                banco.setAgencia(rs.getString("agencia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return banco;
    }
    
    public List<Banco> ConsultarTodos() {
        List<Banco> listaBancos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM banco ORDER BY id_banco");
            while (rs.next()) {
                Banco banco = new Banco();
                banco.setId_banco(rs.getInt("id_banco"));
                banco.setNome(rs.getString("nome"));
                banco.setCodigo_banco(rs.getString("codigo_banco"));
                banco.setAgencia(rs.getString("agencia"));
                listaBancos.add(banco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaBancos;
    }
    
    public List<BancoHashMap> ListaBancoHashMap() {
        String line;
        HashMap<String, BancoHashMap> bancos = new HashMap<>();
        
        try {
            FileReader fr = new FileReader("C:\\TempTxt\\codigosBancos.txt");
            //FileReader fr = new FileReader("./codigosBancos");
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                
                String[] dados = line.split(";");
                String codigo = dados[0];
                String nome = dados[1];
                
                BancoHashMap bancoHas = new BancoHashMap();
                bancoHas.setCodigo_banco(codigo);
                bancoHas.setNome(nome);
                bancos.put(codigo, bancoHas);
            }
            br.close();
        } catch (IOException e) {
            
        }
        
        ArrayList<BancoHashMap> listaBancos = new ArrayList<>();
        for (String key : bancos.keySet()) {
            listaBancos.add(bancos.get(key));
        }
        
        for (int i = 0; i < listaBancos.size(); i++) {
            for (int j = i; j < listaBancos.size(); j++) {
                if (listaBancos.get(i).getCodigo_banco().compareTo(listaBancos.get(j).getCodigo_banco()) > 1) {
                    BancoHashMap temp = listaBancos.get(j);
                    listaBancos.set(j, listaBancos.get(i));
                    listaBancos.set(i, temp);
                }
            }
        }
        
        return listaBancos;
    }
}
