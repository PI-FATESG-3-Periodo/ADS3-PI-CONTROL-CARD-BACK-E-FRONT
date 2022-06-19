/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Categoria;
import br.com.controlCard.model.Despesa;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.CategoriaDao;
import br.com.controlCard.persistencia.DespesaDao;
import br.com.controlCard.persistencia.FabricaAbstrata;
import br.com.controlCard.persistencia.UsuarioDao;
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
@WebServlet(name = "ControllerDespesa", urlPatterns = {"/ControllerDespesa"})
public class ControllerDespesa extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteriaDao;
    private CategoriaDao categoriaDao;
    private DespesaDao despesaDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerDespesa() {
        super();
        carteriaDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        categoriaDao = new CategoriaDao();
        despesaDao = new DespesaDao() {
            @Override
            public boolean ePrimeiro(Despesa d1, Despesa d2) {
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
        Despesa despesa = new Despesa();
        Carteira carteira = new Carteira();
        if (action.equalsIgnoreCase("consulta")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario2"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            despesa.setCarteira(carteira);
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_despesa", despesaDao.ConsultarTodasDespesaUsuario(despesa));
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
            despesa.setCarteira(carteira);
             despesaDao = FabricaAbstrata.ORDENADESPESA.ObterFabricaDesepesa("DATA").ObterOrdenacao();
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_despesa", despesaDao.ConsultarDespesaPorDataArrayList(despesa, data_inicio, data_fim));
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
            despesa.setCarteira(carteira);
            despesaDao = FabricaAbstrata.ORDENADESPESA.ObterFabricaDesepesa("ID").ObterOrdenacao();
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_despesa", despesaDao.ConsultarDespesaPorDataArrayList(despesa, data_inicio, data_fim));
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
            despesa.setCarteira(carteira);
            despesaDao = FabricaAbstrata.ORDENADESPESA.ObterFabricaDesepesa("VALOR").ObterOrdenacao();
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_despesa", despesaDao.ConsultarDespesaPorDataArrayList(despesa, data_inicio, data_fim));
        }
        if (action.equalsIgnoreCase("remover")) {
            int codigo = Integer.parseInt(request.getParameter("id_despesa"));
            despesa = despesaDao.ConsultarDespesa(codigo);
            carteira = carteriaDao.ConsultarCarteira(despesa.getCarteira().getId_carteira());
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            despesa.setCarteira(carteira);
            if (despesaDao.Remover(codigo)) {
                request.setAttribute("carteira", carteira);
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                request.setAttribute("lista_despesa", despesaDao.ConsultarTodasDespesaUsuario(despesa));
            } else {
                request.setAttribute("carteira", carteira);
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                request.setAttribute("lista_despesa", despesaDao.ConsultarTodasDespesaUsuario(despesa));
                request.setAttribute("confirmaCadastroDespesa", "ERRO REMOVER DESPESA!");
            }

        }
        if (action.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("id_despesa"));
            despesa = despesaDao.ConsultarDespesa(codigo);
            carteira = carteriaDao.ConsultarCarteira(despesa.getCarteira().getId_carteira());
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            despesa.setCarteira(carteira);
            request.setAttribute("categoria", categoriaDao.ConsultarCategoria(despesa.getCategoria().getId_categoria()));
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            request.setAttribute("lista_despesa", despesaDao.ConsultarTodasDespesaUsuario(despesa));
            request.setAttribute("despesa", despesaDao.ConsultarDespesa(codigo));
        }

        RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Despesa despesa = new Despesa();
        despesa.setCarteira(new Carteira());
        despesa.setCategoria(new Categoria());
        Date vencimento = new Date();
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
            String codigo = request.getParameter("id_despesa");
            despesa.setDescricao(request.getParameter("descricao_despesa"));
            try {
                despesa.setData(dateFormat.parse(request.getParameter("data_despesa")));
                vencimento = dateFormat.parse(request.getParameter("vencimento"));
            } catch (ParseException e) {
                throw new ServletException(e + "Parse de data Falho!");
            }
            despesa.setQtd_parcelas(Integer.parseInt(request.getParameter("qtd_parcelas")));
            despesa.setValor(Double.parseDouble(request.getParameter("valor")));
            despesa.getCarteira().setId_carteira(Integer.parseInt(request.getParameter("id_carteira")));
            despesa.getCategoria().setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
            int qtd = Integer.parseInt(request.getParameter("qtd_parcelas"));
            if (codigo == null || codigo.isEmpty()) {
                for (int i = 0; i < qtd; i++) {
                    despesa.setVencimento(vencimento);
                    if (despesaDao.Inserir(despesa)) {
                        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                        Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                        Carteira carteira = new Carteira();
                        carteira.setUsuario(usuario);
                        carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                        carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                        carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                        request.setAttribute("carteira", carteira);
                        request.setAttribute("categorias", categoriaDao.ConsultarTodos());
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
                        request.setAttribute("confirmaCadastroDespesa", "ERRO NO CADASTRO DA DESPESA!");
                        RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
                        view.forward(request, response);
                    }
                    vencimento.setMonth(vencimento.getMonth() + 1);
                }
                request.setAttribute("confirmaCadastroDespesa", "DESPESA CADASTRADA COM SUCESSO!");
                RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
                view.forward(request, response);
            } else {
                despesa.setId_despesa(Integer.parseInt(codigo));
                if (despesaDao.Alterar(despesa)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                    request.setAttribute("confirmaCadastroDespesa", "DESPESA ALTERADA COM SUCESSO!");
                    RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
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
                    request.setAttribute("confirmaCadastroDespesa", "ERRO NA ALTERACAO DA DESPESA!");
                    RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
                    view.forward(request, response);
                }
            }
        }
    }
}
