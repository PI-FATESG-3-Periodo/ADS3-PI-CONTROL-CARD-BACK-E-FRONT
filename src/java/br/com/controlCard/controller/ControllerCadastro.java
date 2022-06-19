/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.UsuarioDao;
import br.com.controlCard.validation.ValidaCPFeString;
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
@WebServlet(name = "ControllerCadastro", urlPatterns = {"/ControllerCadastro"})
public class ControllerCadastro extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteriaDao;

    public ControllerCadastro() {
        super();
        usuarioDao = new UsuarioDao();
        carteriaDao = new CarteiraDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        String nome = request.getParameter("nome").trim();
        String email = request.getParameter("email").trim();
        String cpf = request.getParameter("cpf").trim();
        String password = request.getParameter("password").trim();
        usuario.setNome(nome);
        usuario.setEmail(email);
        if (ValidaCPFeString.isCPF(cpf)) {
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            usuario.setCpf(cpf);
            usuario.setSenha(password);
            if (usuarioDao.Inserir(usuario)) {
                if (carteriaDao.Inserir(usuario.getId_usuario())) {
                    request.setAttribute("confirmaCadastro", "CADASTRO REALIZADO COM SUCESSO!");
                    RequestDispatcher view = request.getRequestDispatcher("./login.jsp");
                    view.forward(request, response);
                } else {
                    request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO!");
                    RequestDispatcher view = request.getRequestDispatcher("./cadastro.jsp");
                    view.forward(request, response);
                }
            } else {
                request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO!");
                RequestDispatcher view = request.getRequestDispatcher("./cadastro.jsp");
                view.forward(request, response);
            }
        } else {
            request.setAttribute("confirmaCadastro", "CPF INVALIDO!");
            RequestDispatcher view = request.getRequestDispatcher("./cadastro.jsp");
            view.forward(request, response);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pagina = "index.jsp";
//        
//        Usuario usuario = new Usuario();
//        usuario.setNome(request.getParameter("nome").trim());
//        usuario.setEmail(request.getParameter("email").trim());
//        usuario.setCpf(request.getParameter("cpf").trim());
//        usuario.setSenha(request.getParameter("password").trim());
//
//        if (usuarioDao.Inserir(usuario)) {
//            System.out.println(usuarioDao.Inserir(usuario));
//            if (carteriaDao.Inserir(usuario.getId_usario())) {
//                HttpSession sessao = request.getSession();
//                sessao.setAttribute("confirmaLogin", "CADASTRO REALIZADO COM SUCESSO!");
//                pagina = "login.jsp";
//            } else {
//                HttpSession sessao = request.getSession();
//                sessao.setAttribute("confirmaLogin", "ERRO NO CADASTRO!");
//                pagina = "cadastro.jsp";
//
//            }
//        } else {
//            HttpSession sessao = request.getSession();
//            sessao.setAttribute("confirmaLogin", "ERRO NO CADASTRO!");
//            pagina = "cadastro.jsp";
//        }
//        response.sendRedirect(pagina);
    }
}
