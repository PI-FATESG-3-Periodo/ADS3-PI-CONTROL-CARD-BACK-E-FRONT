/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Conta;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.BancoDao;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.CategoriaDao;
import br.com.controlCard.persistencia.ContaDao;
import br.com.controlCard.persistencia.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author WsmGyn
 */
@WebServlet(name = "ControllerCarteira", urlPatterns = {"/ControllerCarteira"})
public class ControllerCarteira extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;
    private CategoriaDao categoriaDao;
    private BancoDao bancoDao;
    private ContaDao contaDao;

    public ControllerCarteira() {
        super();
        carteiraDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        categoriaDao = new CategoriaDao();
        bancoDao = new BancoDao();
        contaDao = new ContaDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("botao").equalsIgnoreCase("receita")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("despesa")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("conta")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
            carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
            carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
            Conta conta = new Conta();
            conta.setCarteira(carteira);
            conta = contaDao.ConsultarContaPorCarteira(conta);
            request.setAttribute("conta2", conta);
            request.setAttribute("bancos2", bancoDao.ConsultarTodos());
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
            view.forward(request, response);
        }
    }

}
