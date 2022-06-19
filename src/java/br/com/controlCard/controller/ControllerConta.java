/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Conta;
import br.com.controlCard.model.Mov_Conta;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.BancoDao;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.CategoriaDao;
import br.com.controlCard.persistencia.ContaDao;
import br.com.controlCard.persistencia.MovimentacaoDao;
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

@WebServlet(name = "ControllerConta", urlPatterns = {"/ControllerConta"})
public class ControllerConta extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteiraDao;
    private CategoriaDao categoriaDao;
    private BancoDao bancoDao;
    private ContaDao contaDao;
    private MovimentacaoDao movimetacaoDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerConta() {
        super();
        carteiraDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        categoriaDao = new CategoriaDao();
        bancoDao = new BancoDao();
        contaDao = new ContaDao();
        movimetacaoDao = new MovimentacaoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("consulta")) {
            request.setAttribute("bancos2", bancoDao.ConsultarTodos());
            request.setAttribute("contas3", contaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("id_conta3"));
            Conta conta = contaDao.ConsultarConta(codigo);
            conta = contaDao.ConsultarContaPorCarteira(conta);
            request.setAttribute("bancos2", bancoDao.ConsultarTodos());
            request.setAttribute("conta2", conta);
        }
        RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conta conta = new Conta();
        Carteira carteira = new Carteira();
        Mov_Conta mov = new Mov_Conta();
        Date dataMov = new Date();
        if (request.getParameter("botao").equalsIgnoreCase("carteira")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteiraDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarDespesaPorCarteira(carteira);
            carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
            carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
            carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./dashboard.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("banco")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            request.setAttribute("bancos2", bancoDao.ListaBancoHashMap());
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./banco.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("poupanca")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./poupanca.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("Salvar")) {
            String numero = request.getParameter("numero_conta");
            String limite = request.getParameter("limite_conta");
            String data = request.getParameter("data_criacao");
            String saldo_conta = request.getParameter("saldo_conta");
            String id_cart = request.getParameter("id_carteira");
            String id_banco = request.getParameter("id_banco");
            if (ValidaCPFeString.validarCampoString(numero) && ValidaCPFeString.validarCampoString(limite) && ValidaCPFeString.validarCampoString(data)
                    && ValidaCPFeString.validarCampoString(saldo_conta) && ValidaCPFeString.validarCampoString(id_cart) && ValidaCPFeString.validarCampoString(id_banco)) {
                conta.setNumero_conta(Integer.parseInt(numero));
                conta.setLimite(Double.parseDouble(limite));
                conta.setSaldo_conta(Double.parseDouble(data));
                try {
                    conta.setData_criacao(dateFormat.parse(saldo_conta));
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerConta.class.getName()).log(Level.SEVERE, null, ex);
                }
                conta.setCarteira(carteiraDao.ConsultarCarteira(Integer.parseInt(id_cart)));
                conta.setBanco(bancoDao.ConsultarBanco(Integer.parseInt(id_banco)));
                if (contaDao.InserirConta(conta)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                    request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("conta", conta);
                    request.setAttribute("confirmaCadastro", "CONTA CADASTRADA COM SUCESSO");
                    RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                    view.forward(request, response);
                } else {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                    request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("conta", conta);
                    request.setAttribute("confirmaCadastro", "ERRO CADASTRAR CONTA");
                    RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                    view.forward(request, response);
                }
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                carteira.setUsuario(usuario);
                carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                request.setAttribute("carteira", carteira);
                request.setAttribute("conta", conta);
                request.setAttribute("confirmaCadastro", "ERRO CADASTRAR CONTA TODOS OS CAMPOS SAO OBRIGATORIO");
                RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                view.forward(request, response);
            }

        } else if (request.getParameter("botao").equalsIgnoreCase("Sacar")) {
            String valor_mov = request.getParameter("valor_mov");
            String data_mov = request.getParameter("data_mov_conta");
            String tipo_mov = request.getParameter("tipo_mov");
            String id_conta = request.getParameter("id_conta2");
            String saldo_conta = request.getParameter("saldo_conta");

            if (ValidaCPFeString.validarCampoString(valor_mov) && ValidaCPFeString.validarCampoString(data_mov)
                    && ValidaCPFeString.validarCampoString(tipo_mov) && ValidaCPFeString.validarCampoString(id_conta)) {
                mov.setValor_mov(Double.parseDouble(valor_mov));
                try {
                    dataMov = dateFormat.parse(data_mov);

                } catch (ParseException ex) {
                    Logger.getLogger(ControllerConta.class.getName()).log(Level.SEVERE, null, ex);
                }
                mov.setData_mov(dataMov);
                mov.setTipo_mov(tipo_mov);
                mov.setConta(contaDao.ConsultarConta(Integer.parseInt(id_conta)));
                double valor = mov.getValor_mov();
                double saldo_c = Double.parseDouble(saldo_conta);
                if (valor < saldo_c) {
                    if (movimetacaoDao.inserir_mov(mov)) {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        carteira.setUsuario(usuario);
                        carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                        carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                        carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                        conta = mov.getConta();
                        request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("conta2", conta);
                        request.setAttribute("confirmaCadastro", "VALOR SACADO COM SUCESSO");
                        RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                        view.forward(request, response);
                    } else {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        carteira.setUsuario(usuario);
                        carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                        carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                        carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                        conta = mov.getConta();
                        request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("conta2", conta);
                        request.setAttribute("confirmaCadastro", "ERRO CADASTRAR MOVIMENTACAO");
                        RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                        view.forward(request, response);
                    }

                } else {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                    conta = mov.getConta();
                    request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("conta2", conta);
                    request.setAttribute("confirmaCadastro", "SALDO INSUFICIENTE");
                    RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                    view.forward(request, response);
                }
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                carteira.setUsuario(usuario);
                carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                conta = mov.getConta();
                request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                request.setAttribute("carteira", carteira);
                request.setAttribute("conta2", conta);
                request.setAttribute("confirmaCadastro", "ERRO CADASTRAR MOVIMENTACAO TODOS OS CAMPOS SAO OBRIGATORIOS");
                RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                view.forward(request, response);
            }
        } else if (request.getParameter("botao").equalsIgnoreCase("Depositar")) {
            String valor_mov = request.getParameter("valor_mov");
            String data_mov = request.getParameter("data_mov_conta");
            String tipo_mov = request.getParameter("tipo_mov");
            String id_conta = request.getParameter("id_conta2");
            if (ValidaCPFeString.validarCampoString(valor_mov) && ValidaCPFeString.validarCampoString(data_mov)
                    && ValidaCPFeString.validarCampoString(tipo_mov) && ValidaCPFeString.validarCampoString(id_conta)) {
                mov.setValor_mov(Double.parseDouble(valor_mov));
                try {
                    dataMov = dateFormat.parse(data_mov);

                } catch (ParseException ex) {
                    Logger.getLogger(ControllerConta.class.getName()).log(Level.SEVERE, null, ex);
                }
                mov.setData_mov(dataMov);
                mov.setTipo_mov(tipo_mov);
                mov.setConta(contaDao.ConsultarConta(Integer.parseInt(id_conta)));
                if (movimetacaoDao.inserir_mov(mov)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                    conta = mov.getConta();
                    request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("conta2", conta);
                    request.setAttribute("confirmaCadastro", "VALOR DEPOSITADO COM SUCESSO");
                    RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                    view.forward(request, response);
                } else {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    carteira.setUsuario(usuario);
                    carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                    carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                    carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                    conta = mov.getConta();
                    request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("conta2", conta);
                    request.setAttribute("confirmaCadastro", "ERRO CADASTRAR MOVIMENTACAO");
                    RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                    view.forward(request, response);
                }
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                carteira.setUsuario(usuario);
                System.out.println(carteira.getId_carteira() + " 3333");
                carteira = carteiraDao.ConsultarCarteiraPorUsuario(carteira);
                System.out.println(carteira.getId_carteira() + " 4444");
                carteira = carteiraDao.ConsultarSaquePorCarteira(carteira);
                carteira = carteiraDao.ConsultarDepositoPorCarteira(carteira);
                carteira = carteiraDao.ConsultarLimiteContaPorCarteira(carteira);
                conta = mov.getConta();
                request.setAttribute("bancos2", bancoDao.ConsultarTodos());
                request.setAttribute("carteira", carteira);
                request.setAttribute("conta2", conta);
                request.setAttribute("confirmaCadastro", "ERRO CADASTRAR MOVIMENTACAO TODOS OS CAMPOS SAO OBRIGATORIOS");
                RequestDispatcher view = request.getRequestDispatcher("./conta.jsp");
                view.forward(request, response);
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
