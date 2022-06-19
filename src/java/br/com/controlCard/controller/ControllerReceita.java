/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Categoria;
import br.com.controlCard.model.Receita;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.CategoriaDao;
import br.com.controlCard.persistencia.FabricaAbstrata;
import br.com.controlCard.persistencia.ReceitaDao;
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

/**
 *
 * @author WsmGyn
 */
@WebServlet(name = "ControllerReceita", urlPatterns = {"/ControllerReceita"})
public class ControllerReceita extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteriaDao;
    private CategoriaDao categoriaDao;
    private ReceitaDao receitaDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerReceita() {
        super();
        carteriaDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        categoriaDao = new CategoriaDao();
        receitaDao = new ReceitaDao() {
            @Override
            public boolean ePrimeiro(Receita r1, Receita r2) {
                return false;
            }
        };
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Receita receita = new Receita();
        Carteira carteira = new Carteira();
        if (action.equalsIgnoreCase("consulta")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario2"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_receita", receitaDao.ConsultarTodasReceitaUsuario(receita));
        }
        if (action.equalsIgnoreCase("data")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario2"));
            Date data_inicio = null, data_fim = null;
            try {
                data_inicio = dateFormat.parse(request.getParameter("data_inicio"));
                data_fim = dateFormat.parse(request.getParameter("data_fim"));
            } catch (ParseException ex) {
                Logger.getLogger(ControllerReceita.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            receitaDao = FabricaAbstrata.ORDENARECEITA.ObterFabricaReceita("DATA").ObterOrdenacao();
            request.setAttribute("lista_receita", receitaDao.ConsultarReceitaPorDataArrayList(receita, data_inicio, data_fim));
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("id")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario2"));
            Date data_inicio = null, data_fim = null;
            try {
                data_inicio = dateFormat.parse(request.getParameter("data_inicio"));
                data_fim = dateFormat.parse(request.getParameter("data_fim"));
            } catch (ParseException ex) {
                Logger.getLogger(ControllerReceita.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            receitaDao = FabricaAbstrata.ORDENARECEITA.ObterFabricaReceita("ID").ObterOrdenacao();
            request.setAttribute("lista_receita", receitaDao.ConsultarReceitaPorDataArrayList(receita, data_inicio, data_fim));
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("valor")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario2"));
            Date data_inicio = null, data_fim = null;
            try {
                data_inicio = dateFormat.parse(request.getParameter("data_inicio"));
                data_fim = dateFormat.parse(request.getParameter("data_fim"));
            } catch (ParseException ex) {
                Logger.getLogger(ControllerReceita.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            receitaDao = FabricaAbstrata.ORDENARECEITA.ObterFabricaReceita("VALOR").ObterOrdenacao();
            request.setAttribute("lista_receita", receitaDao.ConsultarReceitaPorDataArrayList(receita, data_inicio, data_fim));
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("remover")) {
            int codigo = Integer.parseInt(request.getParameter("id_receita"));
            receita = receitaDao.ConsultarReceita(codigo);
            carteira = carteriaDao.ConsultarCarteira(receita.getCarteira().getId_carteira());
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            if (receitaDao.Remover(codigo)) {
                request.setAttribute("carteira", carteira);
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                request.setAttribute("lista_receita", receitaDao.ConsultarTodasReceitaUsuario(receita));
            } else {
                request.setAttribute("carteira", carteira);
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                request.setAttribute("lista_receita", receitaDao.ConsultarTodasReceitaUsuario(receita));
                request.setAttribute("confirmaCadastroReceita", "ERRO AO REMOVER");
            }
        }
        if (action.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("id_receita"));
            receita = receitaDao.ConsultarReceita(codigo);
            carteira = carteriaDao.ConsultarCarteira(receita.getCarteira().getId_carteira());
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            receita.setCarteira(carteira);
            request.setAttribute("categoria", categoriaDao.ConsultarCategoria(receita.getCategoria().getId_categoria()));
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_receita", receitaDao.ConsultarTodasReceitaUsuario(receita));
            request.setAttribute("receita", receitaDao.ConsultarReceita(codigo));
        }
        RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Receita receita = new Receita();
        receita.setCategoria(new Categoria());
        receita.setCarteira(new Carteira());

        if (request.getParameter("botao").equalsIgnoreCase("carteira")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./dashboard.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("categoria")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            RequestDispatcher view = request.getRequestDispatcher("./categoria.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("Salvar")) {
            String codigo = request.getParameter("id_receita");
            String valor = request.getParameter("valor");
            String data = request.getParameter("data_receita");
            String descricao = request.getParameter("descricao_receita");
            String cartei = request.getParameter("id_carteira");
            String categ = request.getParameter("id_categoria");

            if (ValidaCPFeString.validarCampoString(valor) && ValidaCPFeString.validarCampoString(data) && ValidaCPFeString.validarCampoString(descricao)
                    && ValidaCPFeString.validarCampoString(cartei) && ValidaCPFeString.validarCampoString(categ)) {
                receita.setValor(Double.parseDouble(valor));
                try {
                    receita.setData(dateFormat.parse(data));
                } catch (ParseException e) {
                    throw new ServletException(e + "Parse de data Falho!");
                }
                receita.setDescricao(descricao);
                receita.setCarteira(carteriaDao.ConsultarCarteira(Integer.parseInt(cartei)));
                receita.setCategoria(categoriaDao.ConsultarCategoria(Integer.parseInt(categ)));
                if (codigo == null || codigo.isEmpty()) {
                    if (receitaDao.Inserir(receita)) {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        Carteira carteira = new Carteira();
                        carteira.setUsuario(usuario);
                        carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                        carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                        request.setAttribute("confirmaCadastroReceita", "RECEITA CADASTRADA COM SUCESSO!");
                        RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
                        view.forward(request, response);
                    } else {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        Carteira carteira = new Carteira();
                        carteira.setUsuario(usuario);
                        carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                        carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                        request.setAttribute("confirmaCadastroReceita", "ERRO NO CADASTRO DA RECEITA!");
                        RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
                        view.forward(request, response);
                    }
                } else {
                    receita.setId_receita(Integer.parseInt(codigo));
                    if (receitaDao.Alterar(receita)) {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        Carteira carteira = new Carteira();
                        carteira.setUsuario(usuario);
                        carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                        carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                        request.setAttribute("confirmaCadastroReceita", "RECEITA ALTERADA COM SUCESSO!");
                        RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
                        view.forward(request, response);
                    } else {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        Carteira carteira = new Carteira();
                        carteira.setUsuario(usuario);
                        carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                        carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                        request.setAttribute("confirmaCadastroReceita", "ERRO NA ALTERACAO DA RECEITA!");
                        RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
                        view.forward(request, response);
                    }
                }
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                Carteira carteira = new Carteira();
                carteira.setUsuario(usuario);
                carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                request.setAttribute("carteira", carteira);
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                request.setAttribute("confirmaCadastroReceita", "TODOS OS CAMPOS SAO OBRIGATORIOS!");
                RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
                view.forward(request, response);
            }
        }
    }
}
