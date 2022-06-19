/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controlCard.controller;

import br.com.controlCard.model.Carteira;
import br.com.controlCard.model.Categoria;
import br.com.controlCard.model.Usuario;
import br.com.controlCard.persistencia.CarteiraDao;
import br.com.controlCard.persistencia.CategoriaDao;
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
@WebServlet(name = "ControllerCategoria", urlPatterns = {"/ControllerCategoria"})
public class ControllerCategoria extends HttpServlet {

    private UsuarioDao usuarioDao;
    private CarteiraDao carteriaDao;
    private CategoriaDao categoriaDao;

    public ControllerCategoria() {
        super();
        carteriaDao = new CarteiraDao();
        usuarioDao = new UsuarioDao();
        categoriaDao = new CategoriaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("consulta")) {
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
        }
        if (action.equalsIgnoreCase("remover")) {
            int id = Integer.parseInt(request.getParameter("id_categoria"));
            if (categoriaDao.Remover(id)) {
                int codigo = Integer.parseInt(request.getParameter("id_categoria"));
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                Categoria categoria = categoriaDao.ConsultarCategoria(codigo);
                request.setAttribute("categoria", categoria);
            } else {
                int codigo = Integer.parseInt(request.getParameter("id_categoria"));
                request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                Categoria categoria = categoriaDao.ConsultarCategoria(codigo);
                request.setAttribute("categoria", categoria);
                request.setAttribute("confirmaCadastro", "ERRO REMOVER CHAVE ESTA CONTIDA EM UMA RECEITA OU DESPESA!");
            }

        }
        if (action.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("id_categoria"));
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            Categoria categoria = categoriaDao.ConsultarCategoria(codigo);
            request.setAttribute("categoria", categoria);
        }
        RequestDispatcher view = request.getRequestDispatcher("./categoria.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("botao").equalsIgnoreCase("receita")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            RequestDispatcher view = request.getRequestDispatcher("./receita.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("despesa")) {
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
            Carteira carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
            carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
            carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
            request.setAttribute("carteira", carteira);
            request.setAttribute("categorias", categoriaDao.ConsultarTodos());
            RequestDispatcher view = request.getRequestDispatcher("./despesa.jsp");
            view.forward(request, response);
        } else if (request.getParameter("botao").equalsIgnoreCase("Salvar")) {
            String codigo = request.getParameter("id_categoria");
            Categoria categoria = new Categoria();
            categoria.setDescricao(request.getParameter("descricao_categoria"));
            if (codigo == null || codigo.isEmpty()) {
                if (categoriaDao.Inserir(categoria)) {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("categoria", categoria);
                    request.setAttribute("confirmaCadastro", "CATEGORIA CADASTRADA COM SUCESSO!");
                } else {
                    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    Usuario usuario = usuarioDao.ConsultarUsuario(id_usuario);
                    Carteira carteira = new Carteira();
                    carteira.setUsuario(usuario);
                    carteira = carteriaDao.ConsultarCarteiraPorUsuario(carteira);
                    carteira = carteriaDao.ConsultarReceitaPorCarteira(carteira);
                    carteira = carteriaDao.ConsultarDespesaPorCarteira(carteira);
                    request.setAttribute("carteira", carteira);
                    request.setAttribute("categoria", categoria);
                    request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO!");
                }
            } else {
                categoria.setId_categoria(Integer.parseInt(codigo));
                if (categoriaDao.Alterar(categoria)) {
                    request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                    request.setAttribute("confirmaCadastro", "CATEGORIA ALTERADA COM SUCESSO!");
                } else {
                    request.setAttribute("categorias", categoriaDao.ConsultarTodos());
                    request.setAttribute("confirmaCadastro", "ERRO NO CADASTRO!");
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("./categoria.jsp");
            view.forward(request, response);
        }
    }
}
