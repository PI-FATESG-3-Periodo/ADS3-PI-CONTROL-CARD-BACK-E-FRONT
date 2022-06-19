<%-- 
Document   : banco
Created on : 18 de jun. de 2022, 09:33:33
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
    <script src="./js/categoria.js"></script>
    <title>Banco - ControlCard - PI</title>

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
                        <a href="categoria.jsp" class="dashboard-nav-dropdown-item">Categoria</a>
                    </div>
                </div>
               
              <div class="exit">
                    <a href="ControllerLogin?acao=logout" class="dashboard-nav-item"><i class="fas fa-sign-out-alt"></i> Sair </a>
              </div>
              
            </nav>
        </div>
        <div class="dashboard-app">
            <header class="dashboard-toolbar"><a href="#!" class="menu-toggle"><i class="fas fa-bars"></i></a></header>
            <div class="dashboard-content">
                <section class="categoria">
                    
                    <form method="POST" action="ControllerBanco" name="frmCarteira" id="frmCarteira">
                       
                        <div class="box-geral-banco">
                            <div class="box-banco-1">
                                <div class="title-categoria divisor-align">
                                    <h1>Banco</h1>
                                    <div class="botoes">
                                        <button name="botao" value="conta">Conta</button>
                                    </div>
                                </div>
    
                            <div class="b-nome">
                                <div>
                                    <input type="hidden" readonly="readonly"  name="id_carteira" value="<c:out value="${carteira.id_carteira}" />" />
                                </div>
                                <div>
                                    <input type="hidden" readonly="readonly" name="id_usuario" value="<c:out value="${carteira.usuario.id_usuario}" />" />
                                </div>
                                <div>
                                    <input type="text" readonly="readonly"  name="nome" value="<c:out value="${carteira.usuario.nome}" />" />
                                </div>
                            </div>
                           </div>
                            
                           <div class="box-banco-2">
                                <div class="box-banco">
                                <div class="box-cadastrar-banco">
                                    <label>Bancos:</label>
                                    <select name ="id_banco" id="listabancos" onchange="updateBanco2()">
                                        <c:forEach items="${bancos2}" var="banco2">
                                            <option value="<c:out value="${banco2.codigo_banco}"/>" 
                                                    ${banco2.codigo_banco ==  banco2.codigo_banco ? 'selected' : ''}>                                
                                                <c:out value="${banco2.nome}"/>
                                            </option >
                                        </c:forEach>
                                    </select>
    
                                    <div>
                                        <div>
                                            <label for="codigo_banco">Codigo Banco: </label>
                                            <input type="text"   name="codigo_banco" id="codico_banco" value="<c:out value="${banco.codigo_banco}" />" />
                                        </div>
            
                                        <div>
                                            <label for="nome_banco">Nome do Banco: </label>
                                            <input type="text"   name="nome_banco" id="nome_banco" value="<c:out value="${banco.nome}" />" />
                                        </div>
            
                                        <div>
                                            <label for="agencia">Agencia: </label>
                                            <input type="text"   name="agencia" id="agencia" value="<c:out value="${banco.agencia}" />" />
                                        </div>
                                    </div>
            
                                      <div class="btn-salvar">
                                        <input type="submit" name="botao" value="Salvar" />
                                      </div>
                                </div>
                               
                                </div>
                           </div>
                        </div>
  
                    </form> 

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
    
</body>
</html>