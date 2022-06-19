<%-- 
    Document   : login
    Created on : 16 de jun. de 2022, 10:00:15
    Author     : anapriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <title>Login - ControlCard - PI</title>
        <%
            if (request.getAttribute("confirmaCadastro") != null) {
        %>
        <script type="text/javascript" > confirm("<%=request.getAttribute("confirmaCadastro")%>")</script>
        <%
                request.setAttribute("confirmaCadastro", null);

                request.setAttribute("email", null);
            }

            if (session.getAttribute("confirmaValidacao") != null) {
        %>
        <script type="text/javascript" > confirm("<%=session.getAttribute("confirmaValidacao")%>")</script>
        <%
                session.setAttribute("confirmaValidacao", null);
            }
        %>
        <link rel="shortcut icon" type="imagem/x-icon" href="css/imgs/favicon.svg" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
    </head>
    <body>
        <header class="header-login">
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
                
                    <div class="banner-lateral-login">
                        <h2>Hora de controlar suas finanças!</h2>
                        <img src="css/imgs/login.webp" alt="representacao login">
                        <p>Transformação financeira é um grande passo 
                            e nós te ajudaremos nesse caminho.</p>
                    </div>
                    <div class="formulario">
                        <h2>Entrar</h2>
                        <form method="POST" action="ControllerLogin">
                            <div class="email">
                                <label>E-mail:</label>
                                <input type="text" name="email" id="email" onchange="validaEmail(this.value)" value="" required="true"/>
                            </div>
                            <div class="senha">
                                <label>Senha:</label>
                                <input type="password" name="senha"  value="" required="true" />
                            </div>
    
                            <div class="bt-entrar">
                                <button type="submit" value="Logar">Entrar</button>
                  
                            </div>
                            <p>Não é cadastrado? <a href="./cadastro.jsp">Faça seu cadastro aqui</a></p>
                            <input type="hidden" name="acao" value="login" />
                        </form>
                    </div>
                
            </section>
           
          
        </main>
        
        <footer class="footer-login">
            <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
        </footer>
       
    </body>
</html>