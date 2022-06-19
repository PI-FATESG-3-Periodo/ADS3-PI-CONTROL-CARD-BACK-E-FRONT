/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Banco;
import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.BancoDao;
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

@WebServlet(name = "ControllerBanco", urlPatterns = {"/ControllerBanco"})
public class ControllerBanco extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;
    private BancoDao bancoDao;

    public ControllerBanco() {
        super();
        carteiraDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        bancoDao = new BancoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Banco banco = new Banco();
        if (request.getParameter("botao").equalsIgnoreCase("conta")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
            carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
            carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
            request.setAttribute("bancos2", bancoDao.ConsultarTodos());
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("Salvar")) {
            String nome = request.getParameter("nome_banco");
            String codigo = request.getParameter("codigo_banco");
            String agencia = request.getParameter("agencia");
            if (ValidaCPFeString.validarCampoString(nome) && ValidaCPFeString.validarCampoString(codigo) && ValidaCPFeString.validarCampoString(agencia)) {
                banco.setNome(nome);
                banco.setCodigo_banco(codigo);
                banco.setAgencia(agencia);
                if (bancoDao.Inserir(banco)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("bancos2", bancoDao.ListaBancoHashMap());
                    request.setAttribute("confirmaCadastro", "BANCO CADASTRADO COM SUCESSO!");
                    RequestDispatcher view = request.getRequestDispatcher("./banco.jsp");
                    view.forward(request, response);

                } else {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("bancos2", bancoDao.ListaBancoHashMap());
                    request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO DO BANCO !");
                    RequestDispatcher view = request.getRequestDispatcher("./banco.jsp");
                    view.forward(request, response);
                }
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                Carteira carteira = new Carteira();
                carteira.setUsuario(usuario);
                carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
                carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
                request.setAttribute("carteira", carteira);
                request.setAttribute("bancos2", bancoDao.ListaBancoHashMap());
                request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO DO BANCO TODOS OS CAMPOS SAO OBRIGATORIO !");
                RequestDispatcher view = request.getRequestDispatcher("./banco.jsp");
                view.forward(request, response);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
