/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Conta;
import br.com.controlCard.model.Poupanca;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.BancoDao;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.ContaDao;
import br.com.controlCard.persistencia.PoupancaDao;
import br.com.controlCard.persistencia.UsuarioDao;
import br.com.controlCard.validation.ValidaCPFeString;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ControllerPoupanca", urlPatterns = {"/ControllerPoupanca"})
public class ControllerPoupanca extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;
    private PoupancaDao poupancaDao;
    private BancoDao bancoDao;
    private ContaDao contaDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerPoupanca() {
        super();
        carteiraDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        poupancaDao = new PoupancaDao();
        bancoDao = new BancoDao();
        contaDao = new ContaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("consulta")) {
            request.setAttribute("contas3", contaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("id_conta3"));
            Conta conta = contaDao.ConsultarConta(codigo);
            conta = contaDao.ConsultarContaPorCarteira(conta);
            request.setAttribute("conta", conta);
        }
        RequestDispatcher view = request.getRequestDispatcher("./poupanca.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Poupanca poupanca = new Poupanca();
        Date data2 = null;
        if (request.getParameter("botao").equalsIgnoreCase("conta")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
            carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
            carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
            request.setAttribute("bancos2", bancoDao.ConsultarTodos());
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
            view.forward(request, response);

        } else if (request.getParameter("botao").equalsIgnoreCase("Salvar")) {
            String saldo = request.getParameter("saldo_poupanca");
            String data = request.getParameter("data_poupanca");
            String id_conta = request.getParameter("id_conta");
            System.out.println(id_conta + " dfdd");

            if (ValidaCPFeString.validarCampoString(saldo) && ValidaCPFeString.validarCampoString(data)) {
                try {
                    data2 = dateFormat.parse(data);

                } catch (ParseException ex) {
                    Logger.getLogger(ControllerPoupanca.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                poupanca.setData(data2);
                poupanca.setConta(contaDao.ConsultarConta(Integer.parseInt(id_conta)));
                if (poupancaDao.Inserir(poupanca)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("poupanca", poupanca);
                    request.setAttribute("confirmaCadastro", "CONTA POUPANÇA CADASTRADA COM SUCESSO!");
                    RequestDispatcher view = request.getRequestDispatcher("./poupanca.jsp");
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
                    request.setAttribute("poupanca", poupanca);
                    request.setAttribute("confirmaCadastro", "ERRO CADASTRO");
                    RequestDispatcher view = request.getRequestDispatcher("./poupanca.jsp");
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
                request.setAttribute("poupanca", poupanca);
                request.setAttribute("confirmaCadastro", "ERRO CADASTRO OS CAMPOS SAO OBRIGATORIOS");
                RequestDispatcher view = request.getRequestDispatcher("./poupanca.jsp");
                view.forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
