<%-- 
    Document   : index
    Created on : 15 de jun. de 2022, 18:00:10
    Author     : anpriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Projeto Integrador Grupo 2">
    <meta name="description" content="Sistema Web de controle financeiro">
    <meta name="keywords" content="finanças, controle financeiro">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ControlCard - PI</title>
    <link rel="shortcut icon" type="imagem/x-icon" href="css/imgs/favicon.webp" />
    <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
</head>
<body>
    <header class="header-homepage">
        <div class="itens-menu">
            <img src="css/imgs/logoPIAzul.webp" alt="logoPI">
            <nav class="btns-home">
                <a href="login.jsp">
                    <button class="entrar">Entrar</button>
                </a>
                <a href="cadastro.jsp">
                    <button class="cadastrar">Cadastrar</button>
                </a>
            </nav>
        </div>
    </header>

    <main class="container-home">
        <div class="main-homepage">
            <section class="section-homepage">
                <div class="info-info-homepage">
                    <h1>Quer ter controle <span>financeiro</span> em sua vida?</h1>
                    <h3>Conheça a Control Card:</h3>
                    <p>
                         Aqui você controla suas receitas e despesas financeiras com possibilidade de análise de contas pagas, 
                         verificar e adicionar as contas que foram parceladas e contas que serão fixas do mês, observa
                         os seus limites dos cartões selecionados e cadastrados e análisa os valores que foram depositados no banco. 
                       
                    </p>
                    <a href="cadastro.jsp">
                        <button class="vamos-comecar">
                            Vamos Começar
                        </button>
                    </a>
                    
                </div>
                <div class="info-homepage">
                    <img src="css/imgs/homepage.webp" alt="imagem homem e mulher controle financeiro homepage">
                </div>
            </section>
        </div>
       
    </main>

    <footer class="footer-homepage">
        <p><i class="fas fa-graduation-cap"></i> Design e Desenvolvimento por G2 - | PI - ADS - 3º Período - FATESG  | - 2022 </p>
    </footer>
    
</body>
</html>