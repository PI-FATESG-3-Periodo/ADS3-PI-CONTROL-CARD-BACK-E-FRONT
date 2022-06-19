<%-- 
    Document   : categoria
    Created on : 15 de jun. de 2022, 15:45:07
    Author     : anapriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Categoria</title>
        <%
            if (request.getAttribute("confirmaCadastro") != null) {
        %>
        <script type="text/javascript" > confirm("<%=request.getAttribute("confirmaCadastro")%>");</script>
        <%
                request.setAttribute("confirmaCadastro", null);
            }
        %>
        <link rel="shortcut icon" type="imagem/x-icon" href="css/imgs/favicon.svg" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <div class="dashboard">
            <div class="dashboard-nav">
                <header>
                    <a href="#!" class="menu-toggle">
                        <i class="fas fa-bars"></i>
                    </a><a href="dashboard.jsp" class="brand-logo">
                        <img src="css/imgs/logoPI.webp" alt="logo">
                    </a>
                </header>
                <nav class="dashboard-nav-list">
                    <a href="dashboard.jsp" class="dashboard-nav-item active">
                        <i class="fas fa-wallet"></i> Carteira
                    </a>
 
                    <div class="dashboard-nav-dropdown">
                        <a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
                            <i class="fas fa-pen"></i> Cadastrar
                        </a>
                        <div class="dashboard-nav-dropdown-menu">
                            <a href="conta.jsp" class="dashboard-nav-dropdown-item">Conta</a>
                            <a href="receita.jsp" class="dashboard-nav-dropdown-item">Receita</a>
                            <a href="despesa.jsp" class="dashboard-nav-dropdown-item">Despesa</a>
                            <a href="#" class="dashboard-nav-dropdown-item">Categoria</a>  
                        </div>
                    </div>
                   
                  <div class="exit">
                        <a href="ControllerLogin?acao=logout" class="dashboard-nav-item"><i class="fas fa-sign-out-alt"></i> Sair </a>
                  </div>
                  
                </nav>
            </div>
    
            <div class="dashboard-app">
                <header class="dashboard-toolbar">
                    <a href="#!" class="menu-toggle">
                        <i class="fas fa-bars"></i>
                    </a>
                </header>
                <div class="dashboard-content">
                    <section class="categoria">
                            <div class="container-categoria">
                                <div class="section-categoria">
                                    <div class="formularios-categoria">
                                        <form method="POST" action="ControllerCategoria" name="frmCarteira" id="frmCarteira">
                                            <div class="title-categoria divisor-align">
                                                <h1>Categoria</h1>
                                                <div class="botoes">
                                                    <button name="botao" value="receita"><i class="fas fa-comment-dollar"></i> Receita</button>
                                                    <button name="botao" value="despesa"><i class="fas fa-credit-card"></i>  Despesa</button>
                                                </div>
                                            </div>
                                                <div class="c-usuario">
                                                    <div>
                                                        <!--<td><label for="id_usuario">ID_Usuario: </label></td>-->
                                                        <input type="hidden" type="text"  readonly="readonly" name="id_usuario" value="<c:out value="${carteira.usuario.id_usuario}" />" />
                                                    </div>
                    
                                                    <div>
                                                        <label for="nome">Nome Usuario: </label>
                                                        <input type="text" readonly="readonly"  name="nome" value="<c:out value="${carteira.usuario.nome}" />" />
                                                    </div>
                                                </div>
                                               
                                                <div class="c-saldos">
                                                    <div>
                                                        <!--<label for="id_carteira">ID_Carteira: </label><br>-->
                                                        <input type="hidden" readonly="readonly"  name="id_carteira" value="<c:out value="${carteira.id_carteira}" />" />
                                                    </div>
                                                    
                                                    <div class="box-1-categorias">
                                                        <div>
                                                            <label for="saldo_atual">Saldo Atual: </label>
                                                            <input type="text" readonly="readonly"  name="saldo_atual" value="<c:out value="${carteira.saldo_atual}" />" />
                                                        </div>
                        
                                                        <div class="c-saldo-receitas">
                                                            <label for="saldo_receitas">Saldo Receitas: </label>
                                                            <input type="text" readonly="readonly"  name="saldo_receitas" value="<c:out value="${carteira.saldo_receitas}" />" />
                                                        </div>
                        
                                                        <div>
                                                            <label for="saldo_despesas">Saldo Despesas: </label>
                                                            <input type="text" readonly="readonly"  name="saldo_despesas" value="<c:out value="${carteira.saldo_despesas}" />" />
                                                        </div>
                                                    </div>
                                                </div>
                                          
    
                                            <div class="c-descricao">
                                                <div>
                                                    <input type="hidden" readonly="readonly"  name="id_categoria" value="<c:out value="${categoria.id_categoria}" />" />
                                                </div>
                
                                                <div>
                                                    <label for="saldo_receitas">Descrição Categoria: </label>
                                                    <input type="text" name="descricao_categoria" value="<c:out value="${categoria.descricao}" />" />
                                                </div>
                                            </div>
                
                                            <div class="btn-salvar">
                                                <input type="submit" name="botao" value="Salvar" />
                                            </div>
                                        </form>
    
                                    </div>
                                    
                                    <div class="consultar-categorias">
                                        <form method="GET" action="ControllerCategoria" name="frmTabelaCliente">  
                                            <div class="btn-consultar">
                                                <button name="action" value="consulta"><i class="fas fa-book-open"></i> Consultar todos</button>
                                            </div>   
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th>Id Categoria</th>
                                                        <th>Descrição</th>
                                                        <th colspan=2>Ação</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${categorias}" var="categoria">
                                                        <tr>
                                                            <td><c:out value="${categoria.id_categoria}" /></td>
                                                            <td><c:out value="${categoria.descricao}" /></td>
                                                            <td><a href="ControllerCategoria?action=alterar&id_categoria=<c:out value="${categoria.id_categoria}"/>"> <i class="fas fa-pen-fancy"></i> alterar</a></td>
                                                            <td><a href="ControllerCategoria?action=remover&id_categoria=<c:out value="${categoria.id_categoria}"/>"> <i class="fas fa-trash"></i> remover </a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </form>  
                                    </div>
                                </div>
                            </div>
                                                
                        <div class="footer-receita">
                            <div class="copyright">
                                <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
                                                
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/scripts.js"></script>                                        
    </body>
</html>
