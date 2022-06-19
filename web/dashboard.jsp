<%-- 
    Document   : dashboard
    Created on : 15 de jun. de 2022, 18:00:10
    Author     : anpriscilla
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<%
    // verificando se tem um atributo login na sessao
    // se tiver vai continuar e mostrar o menu
    if (session.getAttribute("email") != null) {
%>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Projeto Integrador Grupo 2">
    <meta name="description" content="Sistema Web de controle financeiro">
    <meta name="keywords" content="finanças, controle financeiro">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Dashboard - ControlCard - PI</title>

    <%
    if (session.getAttribute("confirmaValidacao") != null) {
    %>
    <script type="text/javascript" > confirm("<%=session.getAttribute("confirmaValidacao")%>")</script>

    <%
            session.setAttribute("confirmaValidacao", null);
        }
    %>

    <link rel="shortcut icon" type="imagem/x-icon" href="css/imgs/favicon.webp" />
    <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
</head> 
<body>
    <div class="dashboard">
        <div class="dashboard-nav">
            <header>
                <a href="#!" class="menu-toggle">
                    <i class="fas fa-bars"></i>
                </a><a href="#" class="brand-logo">
                    <img src="css/imgs/logoPI.webp" alt="logo">
                </a>
            </header>
            <nav class="dashboard-nav-list">
                
                <a href="#" class="dashboard-nav-item active">
                    <i class="fas fa-grip-horizontal"></i> Dashboard
                </a>
                
                <div class="dashboard-nav-dropdown">
                    <a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
                        <i class="fas fa-pen"></i> Cadastrar
                    </a>
                    <div class="dashboard-nav-dropdown-menu">
                        <a href="conta.jsp" class="dashboard-nav-dropdown-item">Conta</a>
                        <a href="receita.jsp" class="dashboard-nav-dropdown-item">Receita</a>
                        <a href="despesa.jsp" class="dashboard-nav-dropdown-item">Despesa</a>
                        <a href="categoria.jsp" class="dashboard-nav-dropdown-item">Categoria</a>
                    </div>
                </div>
               
              <div class="exit">
                    <a href="ControllerLogin?acao=logout" class="dashboard-nav-item"><i class="fas fa-sign-out-alt"></i> Sair </a>
              </div>
              
            </nav>
        </div>
        <div class="dashboard-app" id="up">
            <header class="dashboard-toolbar"><a href="#!" class="menu-toggle"><i class="fas fa-bars"></i></a></header>
            <div class="dashboard-content">
                <div class="container">
                    <div class="card">
                        <div class="card-header">

                            <div class="bord-bem-vindo">
                                <h1>Bem-Vindo, <% out.print(session.getAttribute("email")); %>!</h1>
                                <div class="msg-inicial">
                                    <div>
                                        <p>
                                           Ao seu sistema pessoal de controle financeiro. Acesse pelos botões principais
                                           que contam com acesso a suas contas, receitas e despesas. Logo abaixo você pode verificar seus saldos.
                                        </p>
                                    </div>
                                   
                                    <img src="css/imgs/bem-vindo.webp" alt="">
                                </div>
                            </div>

                            <div class="principais-func">
                                <h2>Dúvidas? </h2>
                                <div class="info-func">
                                    <h4> O que fazemos:</h4>
                                    <p>Cadastramos suas contas, receitas e despesas. Ajudamos na organização financeira familiar e ainda emitimos o relatório para acompanhamento. </p>
                                </div>
                            </div>   
                        </div>
                        <div class="card-body">
                            <form method="POST" action='ControllerCarteira' name="frmCarteira" id="frmCarteira">
                                <div class="buttons-principais">
                                    <h3>Principais</h3>
                                    <!--<td><label for="id_usuario">ID_Usuario: </label></td>-->
                                    <input type="hidden" readonly="readonly" name="id_usuario" value="<c:out value="${carteira.usuario.id_usuario}" />" />
                                    <!--<td><label for="nome">Nome Usuario: </label></td>-->
                                    <input type="hidden" readonly="readonly"  name="nome" value="<c:out value="${carteira.usuario.nome}" />" />
                                    <div class="box-btn">
                                        <div class="conta-dash">
                                            <button name="botao" value="conta"><i class="fas fa-coins"></i> Conta</button>
                                        </div>
                                        <div class="cadastrar">
                                            <button name="botao" value="receita"><i class="fas fa-comment-dollar"></i> Cadastrar Receita</button>
                                        </div>
                                        <div class="emitir">
                                            <button name="botao" value="despesa"><i class="fas fa-comments-dollar"></i> Cadastrar Despesa</button>
                                        </div>
                                    </div>
                                   
                                </div>
    
                                <div class="buttons-categorias">
                                    <h3>Saldos</h3>
                                    <div class="categorias">
                                            <div class="saldo-atual">
                                                <label for="saldo_atual">Saldo Atual:</label>
                                                <input type="text" readonly="readonly"   onchane ="formatarMoeda()" id="valor" name="saldo_atual" value="<c:out value="${carteira.saldo_atual}" />" />
                                            </div>
                                            <div class="saldo-receitas">
                                                <label for="saldo_receitas">Saldo Receitas:</label>
                                                <input type="text" readonly="readonly"  name="saldo_receitas" value="<c:out value="${carteira.saldo_receitas}" />" />
                                            </div>
                                            <div class="saldo-despesas">
                                                <label for="saldo_despesas">Saldo Despesas:</label>
                                                <input type="text" readonly="readonly"  name="saldo_despesas" value="<c:out value="${carteira.saldo_despesas}" />" />
                                            </div>

                                            <div class="saldo-atual">
                                                <label for="saldo_poupanca">Saldo Conta + Limite Conta:</label>
                                                <input type="text" readonly="readonly"  name="saldo_conta" value="<c:out value="${carteira.saldo_conta}" />" />
                                            </div>


                                            <!--
                                                 <div class="saldo-poupanca">
                                                <label for="saldo_poupanca">Saldo Poupança:</label>
                                                <input type="text" readonly="readonly"  name="saldo_poupanca" value="<c:out value="${carteira.saldo_poupanca}" />" />
                                            </div>
    
                                            <div class="saldo-cartao">
                                                <label for="saldo_cartao">Saldo Cartão:</label>
                                                <input type="text" readonly="readonly"  name="saldo_cartao" value="<c:out value="${carteira.saldo_cartao}" />" />
                                            </div>
                                            -->
                                           
                                    </div>
                                </div>
                            </form>

                            <div class="footer-dash">
                                <div class="copyright-dash">
                                    <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
                                </div>
                            </div>
                           
                           
                        </div>
                    </div>
                </div>

                
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>

</body>
    
</body>
</html>

<%
    // se não existir um login na sessao, 
    // vai enviar para a página de login novamente
} else {
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%
    }
%>