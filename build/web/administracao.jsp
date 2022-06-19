<%-- 
    Document   : administracao
    Created on : 18 de jun. de 2022, 09:33:33
    Author     : anapriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%
    // verificando se tem um atributo login na sessao
    // se tiver vai continuar e mostrar o menu
    if (session.getAttribute("email") != null) {
%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="./js/login.js"></script>
        <title>Administração - ControlCard - PI</title>
        <%
            if (session.getAttribute("confirmaValidacao") != null) {
        %>
        <script type="text/javascript" > confirm("<%=session.getAttribute("confirmaValidacao")%>")</script>

        <%
                session.setAttribute("confirmaValidacao", null);
            }
        %>

        <%
            if (request.getAttribute("confirmaCadastro") != null) {
        %>
        <script type="text/javascript" > confirm("<%=request.getAttribute("confirmaCadastro")%>");</script>
        <%
                request.setAttribute("confirmaCadastro", null);
            }
        %>
        <link rel="shortcut icon" type="imagem/x-icon" href="css/imgs/favicon.webp" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
    </head>
    <body>
        <header class="header-admin">
            <div class="itens-menu">
                <img src="css/imgs/logoPI.webp" alt="logoPI">
                <nav class="btns-home">
                    <a href="ControllerLogin?acao=logout">
                        <button class="entrar">Sair</button>
                    </a>
                </nav>
            </div>
        </header>
        <section class="admin-sistema">
            <div class="box-admin">
                <div class="formularios-admin">
                    <form method="POST" action="ControllerAdministracao" name="frmTabelaCliente">
                        <div class="title-admin">
                            <h1>Administração!</h1>
                            <h3>Bem Vindo, <% out.print(session.getAttribute("email")); %>.</h3>
                            <div class="divisor-align"></div>
                        </div>
                        <div class="box-form-admin">
                            <div class="box-1">
                                <div>
                                    <label>ID Usuario:</label>
                                    <input type="text" name="id_usuario" value="<c:out value="${usuario.id_usuario}" />"/>
                                </div>
                                <div>
                                    <label>Nome:</label>
                                    <input type="text" name="nome" value="<c:out value="${usuario.nome}" />"/>
                                </div>
                                <div class="content-medio">
                                    <label>E-mail:</label>
                                    <input type="text" name="email" id="email" onchange="validaEmail(this.value)" value="<c:out value="${usuario.email}" />"/> 
                                </div>
                            </div>
                            
                            <div class="box-2">
                                <div>
                                    <label>CPF:</label>
                                    <input type="text" name="cpf"id="cpf" onchange="Vcpf(this.value)" value="<c:out value="${usuario.cpf}" />"/>   
                                </div>
                                <div>
                                    <label>Senha:</label>
                                    <input type="password" name="password"  value="<c:out value="${usuario.senha}" />"/>  
                                </div>
                                <div class="btn-admin-alterar">
                                    <input type="submit" value="AlterarUsuario" />
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
               
                <div class="tabelas-admin">
                    <form method="GET" action="ControllerAdministracao" name="frmTabelaCliente">
                        <div> 
                            <table>
                                <div class="btn-consultar-usuarios">
                                    <button name="action" value="consultaUsuario">Consulta Todos Usuarios</button>
                                </div>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>
                                        <th>Email</th>
                                        <th>CPF</th>
                                        <th colspan=2>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${lista_usuarios}" var="usuario">
                                        <tr>
                                            <td><c:out value="${usuario.id_usuario}"/></td>
                                            <td><c:out value="${usuario.nome}"/></td>
                                            <td><c:out value="${usuario.email}"/></td>
                                            <td><c:out value="${usuario.cpf}"/></td>
                                            <td><a href="ControllerAdministracao?action=alterarUsuario&id_usuario=<c:out value="${usuario.id_usuario}"/>"><i class="fas fa-pen-fancy"></i> alterar</a></td>
                                            <td><a href="ControllerAdministracao?action=removerUsuario&id_usuario=<c:out value="${usuario.id_usuario}"/>"><i class="fas fa-trash"></i> remover</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div><br><br>      
                        <div>
                            <table>
                                <div class="btn-consultar-carteiras">
                                    <button name="action" value="consultaCarteira">Consulta Todas As Carteiras</button>
                                </div>
                                <thead>
                                    <tr>
                                        <th>ID Carteira</th>
                                        <!--<th>Saldo Atual</th>-->
                                        <!--<th>Saldo Despesa</th>-->
                                        <!--<th>Saldo Receita</th>-->
                                        <!--<th>Saldo Cartao</th>-->
                                        <!--<th>Saldo Poupanca</th>-->
                                        <th>ID Usuario</th>
                                        <th colspan=2>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${lista_carteiras}" var="carteira">
                                        <tr>
                                            <td><c:out value="${carteira.id_carteira}"/></td>
                                            <!--<td><c:out value="${carteira.saldo_atual}"/></td>-->
                                            <!--<td><c:out value="${carteira.saldo_receitas}"/></td>-->
                                            <!--<td><c:out value="${carteira.saldo_despesas}"/></td>-->
                                            <!--<td><c:out value="${carteira.saldo_poupanca}"/></td>-->
                                            <!--<td><c:out value="${carteira.saldo_cartao}"/></td>-->
                                            <td><c:out value="${carteira.usuario.id_usuario}"/></td>
                                            <td><a href="ControllerAdministracao?action=alterarCarteira&id_carteira=<c:out value="${carteira.id_carteira}"/>"><i class="fas fa-pen-fancy"></i> alterar</a></td>
                                            <td><a href="ControllerAdministracao?action=removerCarteira&id_carteira=<c:out value="${carteira.id_carteira}"/>"><i class="fas fa-trash"></i> remover</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </form> 
                </div>
            </div>
           

        </section>

        <footer class="footer-admin">
            <div class="copyright">
                <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
            </div>
        </footer>

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