<%-- 
    Document   : cadastro
    Created on : 15 de junho de 2022, 18:00:10
    Author     : anpriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="author" content="Projeto Integrador Grupo 2">
        <meta name="description" content="Sistema Web de controle financeiro">
        <meta name="keywords" content="finanças, controle financeiro">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script src="./js/login.js"></script>
        <title>Cadastrar - ControlCard - PI</title>
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
            <header class="header-cadastrar">
                <div class="itens-menu">
                    <img src="css/imgs/logoPIAzul.webp" alt="logoPI">
                    <nav class="btns-home">
                        <a href="./index.jsp">
                            <p class="home">Home</p>
                        </a>
                    </nav>
                </div>
            </header>

        <main class="container-home">
                <section class="container-login">
                    <div class="banner-lateral-cadastro">
                        <h2>Hora de controlar suas finanças!</h2>
                        <img src="css/imgs/cadastrar.webp" alt="representacao login">
                        <p>Transformação financeira é um grande passo 
                            e nós te ajudaremos nesse caminho.</p>
                    </div>
                    <div class="formulario">
                        <h2>Cadastrar</h2>
                        <form method="POST" action='ControllerCadastro' name="frmCadastroUsuario">
                            <div class="nome-cadastrar">
                                <label>Nome:</label>
                                <input type="text" name="nome" required="true" value="<c:out value="${usuario.nome}" />"/>
                            </div>
                            <div class="email">
                                <label>E-mail:</label>
                                <input type="text" name="email" id="email" onchange="validaEmail(this.value)"  required="true" value="<c:out value="${usuario.email}" />"/>
                            </div>
                            <div class="cpf">
                                <label>CPF:</label>
                                <input type="text" name="cpf"id="cpf" onchange="Vcpf(this.value)"  required="true" value="<c:out value="${usuario.cpf}" />"/>
                            </div>
                            <div class="senha">
                                <label>Senha:</label>
                                <input type="password" name="password"  required="true" value="<c:out value="${usuario.senha}" />"/>
                            </div>
    
                            <div class="bt-entrar">
                                <button type="submit" value="Cadastrar">Concordar e Cadastrar</button>
                            </div>
                            <p>Já possui cadastro? <a href="./login.jsp">Faça seu login aqui</a></p>
                        </form>
                    </div>
                </section>
            

        </main>

        <footer class="footer-login">
            <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
        </footer>
       
    </body>
</html>