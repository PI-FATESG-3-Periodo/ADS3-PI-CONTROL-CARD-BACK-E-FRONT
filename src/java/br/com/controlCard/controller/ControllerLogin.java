/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.UsuarioDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Elisabete
 */
@WebServlet("/ControllerLogin")
public class ControllerLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;

    public ControllerLogin() {
        super();
        usuarioDao = new UsuarioDao();
        carteiraDao = new CarteiraDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagina = "index.jsp";
        // Validando se o usuário é igual a "admin" e a senha é igual a "senha"

        boolean validaLogin = false;
        Carteira consulta = new Carteira();
        String e_mail = request.getParameter("email");
        String senha = request.getParameter("senha");

        for (Usuario consultaUsuario : usuarioDao.ConsultarTodos()) {
            if ((consultaUsuario.getEmail().equals(e_mail) && (consultaUsuario.getSenha().equals(senha)))) {
                validaLogin = true;
                consulta.setUsuario(consultaUsuario);
            }
        }
        if (request.getParameter("acao").equals("login")) {

            if (e_mail.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("email", request.getParameter("email"));
                pagina = "administracao.jsp";
                sessao.setAttribute("confirmaValidacao", "BEM VINDO AO SISTEMA!");
            } else {
                if (validaLogin) {
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("email", consulta.getUsuario().getNome());
                    sessao.setAttribute("confirmaValidacao", "BEM VINDO AO SISTEMA!");
                    consulta = carteiraDao.ConsultarCarteiraPorUsuario(consulta);
                    consulta = carteiraDao.ConsultarReceitaPorCarteira(consulta);
                    consulta = carteiraDao.ConsultarDespesaPorCarteira(consulta);
                    consulta = carteiraDao.ConsultarSaquePorCarteira(consulta);
                    consulta = carteiraDao.ConsultarDepositoPorCarteira(consulta);
                    consulta = carteiraDao.ConsultarLimiteContaPorCarteira(consulta);
                    sessao.setAttribute("carteira", consulta);
                    pagina = "dashboard.jsp";
                } else {
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("confirmaValidacao", "USUARIO NÃO CADASTRADO!");
                    pagina = "login.jsp";
                }
            }

        } else if (request.getParameter("acao").equals("logout")) {
            // no logout invalido a sessao
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            // chamo novamente a pagina principal, que deve chamar a página index
            // que ira mostrar o formulario para o usuário logar
            pagina = "dashboard.jsp";
        }
        response.sendRedirect(pagina);
    }
}
