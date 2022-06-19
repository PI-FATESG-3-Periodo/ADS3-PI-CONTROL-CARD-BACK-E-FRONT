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
@WebServlet(name = "ControllerAdministracao", urlPatterns = {"/ControllerAdministracao"})
public class ControllerAdministracao extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;

    public ControllerAdministracao() {
        usuarioDao = new UsuarioDao();
        carteiraDao = new CarteiraDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("consultaUsuario")) {
            request.setAttribute("lista_usuarios", usuarioDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("consultaCarteira")) {
            request.setAttribute("lista_carteiras", carteiraDao.ConsultarTodos());
        }

        if (action.equalsIgnoreCase("removerUsuario")) {
            int codigo = Integer.parseInt(request.getParameter("id_usuario"));
            if (usuarioDao.Remover(codigo)) {
                request.setAttribute("lista_usuarios", usuarioDao.ConsultarTodos());
                request.setAttribute("confirmaCadastro", "Usuario Removido!");
            } else {
                request.setAttribute("lista_usuarios", usuarioDao.ConsultarTodos());
                request.setAttribute("confirmaCadastro", "Erro ao Remover Usuario | Remova a Carteira Primeiro!");
            }
        }
        if (action.equalsIgnoreCase("removerCarteira")) {
            int codigo = Integer.parseInt(request.getParameter("id_carteira"));
            if (carteiraDao.Remover(codigo)) {
                request.setAttribute("lista_carteiras", carteiraDao.ConsultarTodos());
                request.setAttribute("confirmaCadastro", "Carteira Removida!");
            } else {
                request.setAttribute("lista_carteiras", carteiraDao.ConsultarTodos());
                request.setAttribute("confirmaCadastro", "Erro ao Remover a Carteira!");
            }

        }
        if (action.equalsIgnoreCase("alterarUsuario")) {
            int codigo = Integer.parseInt(request.getParameter("id_usuario"));
            request.setAttribute("lista_usuarios", usuarioDao.ConsultarTodos());
            Usuario usuario = usuarioDao.ConsultarUsuario(codigo);
            request.setAttribute("usuario", usuario);
        }

        RequestDispatcher view = request.getRequestDispatcher("./administracao.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        String codigo = request.getParameter("id_usuario");
        String nome = request.getParameter("nome").trim();
        String email = request.getParameter("email").trim();
        String cpf = request.getParameter("cpf").trim();
        String password = request.getParameter("password").trim();
        if (ValidaCPFeString.validarCampoString(codigo)) {
            usuario.setNome(nome);
            usuario.setEmail(email);  
            if (ValidaCPFeString.isCPF(cpf)) {
                cpf = cpf.replace(".", "");
                cpf = cpf.replace("-", "");
                usuario.setCpf(cpf);
                usuario.setSenha(password);
                usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                if (usuarioDao.Alterar(usuario)) {
                    request.setAttribute("confirmaCadastro", "CADASTRO ALTERADO COM SUCESSO!");
                    RequestDispatcher view = request.getRequestDispatcher("./administracao.jsp");
                    view.forward(request, response);
                } else {
                    request.setAttribute("confirmaCadastro", "ERRO NA ALTERAÇAO!");
                    RequestDispatcher view = request.getRequestDispatcher("./administracao.jsp");
                    view.forward(request, response);
                }
            } else {
                request.setAttribute("confirmaCadastro", "CPF INVALIDO!");
                RequestDispatcher view = request.getRequestDispatcher("./administracao.jsp");
                view.forward(request, response);
            }
        } else {
            System.out.println(" nao passou codigo");
            request.setAttribute("confirmaCadastro", "CADASTRE UM NOVO usuario!");
            RequestDispatcher view = request.getRequestDispatcher("./cadastro.jsp");
            view.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
