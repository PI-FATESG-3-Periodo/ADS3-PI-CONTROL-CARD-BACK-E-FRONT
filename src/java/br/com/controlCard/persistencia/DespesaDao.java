/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controlCard.persistencia;

import br.com.controlCard.model.Despesa;
import br.com.controlCard.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author WsmGyn
 */
public abstract class DespesaDao {

    private Connection connection;

    public DespesaDao() {
        connection = FabricaConexao.POSTEGRES.ObterConexao().getConnection();
    }

    public abstract boolean ePrimeiro(Despesa d1, Despesa d2);

    public boolean Inserir(Despesa despesa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO despesa (valor,data,vencimento,qtd_parcelas,descricao,fk_id_carteira,fk_id_categoria) values (?, ?, ?, ? ,?,?,?) RETURNING id_despesa");
            // Parameters start with 1
            preparedStatement.setDouble(1, despesa.getValor());
            preparedStatement.setDate(2, new java.sql.Date(despesa.getData().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(despesa.getVencimento().getTime()));
            preparedStatement.setInt(4, despesa.getQtd_parcelas());
            preparedStatement.setString(5, despesa.getDescricao());
            preparedStatement.setInt(6, despesa.getCarteira().getId_carteira());
            preparedStatement.setInt(7, despesa.getCategoria().getId_categoria());
            //  preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //ConsultarUsuario(rs.getInt("id_usuario"));
                //id=rs.getInt("id_usuario");
                despesa.setId_despesa(rs.getInt("id_despesa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Alterar(Despesa despesa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE despesa SET valor=?, data=?, vencimento=? , qtd_parcelas=?, descricao=?, fk_id_carteira=?, fk_id_categoria=?"
                            + "where id_despesa=?");
            preparedStatement.setDouble(1, despesa.getValor());
            preparedStatement.setDate(2, new java.sql.Date(despesa.getData().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(despesa.getVencimento().getTime()));
            preparedStatement.setInt(4, despesa.getQtd_parcelas());
            preparedStatement.setString(5, despesa.getDescricao());
            preparedStatement.setInt(6, despesa.getCarteira().getId_carteira());
            preparedStatement.setInt(7, despesa.getCategoria().getId_categoria());
            preparedStatement.setInt(8, despesa.getId_despesa());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean Remover(int id_despesa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM despesa WHERE id_despesa=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id_despesa);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Despesa ConsultarDespesa(int id_despesa) {
        Despesa despesa = new Despesa();
        CarteiraDao carteriaDao = new CarteiraDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        String sql = "SELECT * FROM despesa WHERE despesa.id_despesa = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_despesa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                despesa.setId_despesa(rs.getInt("id_despesa"));
                despesa.setValor(rs.getDouble("valor"));
                despesa.setData(rs.getDate("data"));
                despesa.setVencimento(rs.getDate("vencimento"));
                despesa.setQtd_parcelas(rs.getInt("qtd_parcelas"));
                despesa.setDescricao(rs.getString("descricao"));
                despesa.setCarteira(carteriaDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                despesa.setCategoria(categoriaDao.ConsultarCategoria(rs.getInt("fk_id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesa;
    }

//    public Iterator<Despesa> ConsultarReceitaPorData(Despesa despesa, Date dataInicio, Date dataFim) {
//        ArrayList<Despesa> receitaList = new ArrayList<>();
//        String sql = ("SELECT despesa.id_despesa, despesa.valor, to_char(despesa.data, 'DD/MM/YYYY'), despesa.vencimento, despesa.qtd_parcelas , despesa.descricao , despesa.fk_id_carteira, despesa.fk_id_categoria\n"
//                + "FROM despesa INNER JOIN carteira ON  (carteira.id_carteira = despesa.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
//                + "WHERE despesa.data BETWEEN ? AND ? \n"
//                + "ORDER BY despesa.data ASC");
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, 3);
//            ps.setDate(2, new java.sql.Date(dataInicio.getTime()));
//            ps.setDate(3, new java.sql.Date(dataFim.getTime()));
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Despesa des = new Despesa();
//                des.setId_despesa(rs.getInt("id_despesa"));
//                des.setValor(rs.getDouble("valor"));
//                des.setData(rs.getDate("data"));
//                des.setVencimento(rs.getDate("vencimento"));
//                des.setQtd_parcelas(rs.getInt("qtd_parcelas"));
//                des.setDescricao(rs.getString("descricao"));
//                //rec.getCarteira().setId_carteira((rs.getInt("fk_id_carteira")));
//                //rec.getCategoria().setId_categoria(rs.getInt("fk_id_categoria"));
//                receitaList.add(des);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return receitaList.iterator();
//    }
    public List<Despesa> ConsultarTodasDespesaUsuario(Despesa despesa) {
        ArrayList<Despesa> despesaList = new ArrayList<>();
        CarteiraDao carteiraDao = new CarteiraDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        String sql = ("SELECT despesa.id_despesa, despesa.valor, despesa.data, despesa.vencimento, despesa.qtd_parcelas , despesa.descricao , despesa.fk_id_carteira, despesa.fk_id_categoria\n"
                + "FROM despesa INNER JOIN carteira ON  (carteira.id_carteira = despesa.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
                + "ORDER BY despesa.id_despesa ASC");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, despesa.getCarteira().getUsuario().getId_usuario());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Despesa des = new Despesa();
                des.setId_despesa(rs.getInt("id_despesa"));
                des.setValor(rs.getDouble("valor"));
                des.setData(rs.getDate("data"));
                des.setVencimento(rs.getDate("vencimento"));
                des.setQtd_parcelas(rs.getInt("qtd_parcelas"));
                des.setDescricao(rs.getString("descricao"));
                des.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                des.setCategoria(categoriaDao.ConsultarCategoria(rs.getInt("fk_id_categoria")));
                despesaList.add(des);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesaList;
    }

    public List<Despesa> ConsultarDespesaPorDataArrayList(Despesa despesa, Date dataInicio, Date dataFim) {
        ArrayList<Despesa> despesaList = new ArrayList<>();
        CarteiraDao carteiraDao = new CarteiraDao();
        CategoriaDao categoriaDao = new CategoriaDao();

        String sql = ("SELECT despesa.id_despesa, despesa.valor, despesa.data, despesa.vencimento, despesa.qtd_parcelas , despesa.descricao , despesa.fk_id_carteira, despesa.fk_id_categoria\n"
                + "FROM despesa INNER JOIN carteira ON  (carteira.id_carteira = despesa.fk_id_carteira AND carteira.fk_id_usuario =?)\n"
                + "WHERE despesa.data BETWEEN ? AND ? \n"
                + "ORDER BY despesa.data ASC");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, despesa.getCarteira().getUsuario().getId_usuario());
            ps.setDate(2, new java.sql.Date(dataInicio.getTime()));
            ps.setDate(3, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Despesa des = new Despesa();
                des.setId_despesa(rs.getInt("id_despesa"));
                des.setValor(rs.getDouble("valor"));
                des.setData(rs.getDate("data"));
                des.setVencimento(rs.getDate("vencimento"));
                des.setQtd_parcelas(rs.getInt("qtd_parcelas"));
                des.setDescricao(rs.getString("descricao"));
                des.setCarteira(carteiraDao.ConsultarCarteira(rs.getInt("fk_id_carteira")));
                des.setCategoria(categoriaDao.ConsultarCategoria(rs.getInt("fk_id_categoria")));
                despesaList.add(des);
            }
            for (int i = 0; i < despesaList.size(); i++) {
                for (int j = i; j < despesaList.size(); j++) {
                    if (!ePrimeiro(despesaList.get(i), despesaList.get(j))) {
                        Despesa temp = despesaList.get(j);
                        despesaList.set(j, despesaList.get(i));
                        despesaList.set(i, temp);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesaList;
    }
}
