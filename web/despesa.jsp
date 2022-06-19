<%-- 
    Document   : despesa
    Created on : 15 de jun. de 2022, 18:00:10
    Author     : anpriscilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="author" content="Projeto Integrador Grupo 2">
        <meta name="description" content="Sistema Web de controle financeiro">
        <meta name="keywords" content="finanças, controle financeiro">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Despesa - ControlCard - PI</title>
        <script src="./js/categoria.js"></script>
        <%
            if (request.getAttribute("confirmaCadastroDespesa") != null) {
        %>
        <script type="text/javascript" > confirm("<%=request.getAttribute("confirmaCadastroDespesa")%>");</script>
        <%
                request.setAttribute("confirmaCadastroDespesa", null);
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
                    <section class="receita">
                        <div class="container-receita">
                            <div class="section-receita">
                                <div class="formularios-receita"> 
                                    <form method="POST" action="ControllerDespesa" name="frmCarteira" id="frmCarteira">
                                        <div class="title-receita">
                                            <h1>Despesa</h1>
                                            <div class="divisor-align"></div>
                                            <div class="botoes-receita">
                                                <button name="botao" value="carteira"><i class="fas fa-wallet"></i> Carteira</button>
                                                <button name="botao" value="categoria"><i class="fas fa-pen"></i> Cadastrar Categoria</button>
                                            </div>
                                        </div>
                                        
                                        <div class="r-usuario">
                                            <div>
                                                 <!--<td><label for="id_usuario">ID_Usuario: </label></td>-->
                                                 <input type="hidden" type="text"  readonly="readonly" name="id_usuario" value="<c:out value="${carteira.usuario.id_usuario}" />" />
                                            </div>
                                            <div>
                                                <label for="nome">Nome Usuario: </label>
                                                <input type="text" readonly="readonly"  name="nome" value="<c:out value="${carteira.usuario.nome}" />" />
                                            </div>
                                        </div>
        
                                        <div class="r-saldos">
                                            <div>
                                                <!--<td><label for="id_usario_carteira">ID_Carteira: </label></td>-->
                                                <input type="hidden"  type="text" readonly="readonly"  name="id_carteira" value="<c:out value="${carteira.id_carteira}" />" />
                                            </div>
                                            <div class="box-1-receitas">
                                                <div>
                                                    <label for="saldo_atual">Saldo Atual: </label>
                                                    <input type="text" readonly="readonly"  name="saldo_atual" value="<c:out value="${carteira.saldo_atual}" />" />
                                                </div>
                                                <div class="r-saldo-receitas">
                                                    <label for="saldo_receitas">Saldo Despesas: </label>
                                                    <input type="text" readonly="readonly"  name="saldo_receitas" value="<c:out value="${carteira.saldo_despesas}" />" />
                                                </div>
                                            </div>
                                        </div>
        
                                        <div class="r-select">
                                            <div class="r-campo-select">
                                                <label>Categoria: </label>
                                                <select name ="id_categoria" id="listacategoria" onChange="update()" >
                                                    <c:forEach items="${categorias}" var="categoria">
                                                        <option value="<c:out value="${categoria.id_categoria}"/>" 
                                                                    ${categoria.id_categoria ==  receita.categoria.id_categoria ? 'selected' : ''}>
                                                            <c:out value="${categoria.descricao}"/>
                                                        </option >
                                                    </c:forEach>
                                                </select>
                                            </div>
    
                                            <div class="r-campo-desc-cat">
                                                <label for="descricao_categoria">Descrição Categoria: </label>
                                                <input type="text" readonly="readonly" name="descricao_categoria" id="descricao_categoria" value="<c:out value="${categoria.descricao}" />" />
                                            </div>
                                        </div>
        
                                        <div class="r-descricao">
                                            <div>
                                                 <!--<td><label for="id_categoria">ID_categoria: </label></td>-->
                                                 <input type="hidden" type="text" name="id_categoria" id="id_categoria" value="<c:out value="${categoria.id_categoria}" />" />
                                                
                                            </div>
                                            <div class="r-desc-receita">
                                                 <!--<td><label for="id_usuario">ID_Receita: </label></td>-->
                                                 <input type="hidden" type="text" name="id_despesa" value="" />
                                                 <label for="descricao_despesa">Descrição Despesa: </label>
                                                 <input type="text" name="descricao_despesa" value="<c:out value="${despesa.id_despesa}" />" />
                                            </div>
                                        </div>
                            
                                        <div class="r-datavalor">
                                            <div class="r-campo-data">
                                                <label for="data">Data: </label>
                                                <input type="Date" onchange="validarDataDespesa(this.value)"  id="data_despesa" name="data_despesa" value="${despesa.data}" />
                                            </div>
                                            <div class="r-campo-valor">
                                                <label for="data">Vencimento </label>
                                                <input type="Date" onchange="validarDataVencimento(this.value)" id="vencimento" name="vencimento" value="${despesa.vencimento}" />
                                            </div>
     
                                        </div>
    
                                        <div class="r-parc-valor">
                                            <div class="d-qtd-parcelas">
                                                <label for="qtd_parcelas">Quantidade Parcelas: </label>
                                                <input type="text" name="qtd_parcelas" value="<c:out value="${despesa.qtd_parcelas}" />1"  >
                                            </div>
                                            <div class="d-valor">
                                                <label for="valor">Valor: </label>
                                                <input type="text" name="valor" value="<c:out value="${despesa.valor}" />" />
                                            </div>
                                        </div>
                                       
        
                                        <div class="btn-salvar">
                                            <input type="submit" name="botao" value="Salvar" />
                                        </div>
                                        
                                    </form>
                                </div>
                                <div class="consultar-despesas">
                                    <form method="GET" action="ControllerDespesa" name="frmTabelaCliente">
                                        <input type="hidden" readonly="readonly" name="id_usuario2" value="<c:out value="${carteira.usuario.id_usuario}" />" />
    
                                        <div class="box-btns-consultar">
                                            <div class="r-consultar-data">
                                                <div class="r-datas">
                                                    <div class="data-inicio">
                                                        <label for="valor">Data Inicio: </label>
                                                        <input type="date" name="data_inicio" id="data_receita" value="<fmt:formatDate pattern="dd-MM-yyyy" value="" />" />
                                                    </div>
                                                    <div class="data-fim">
                                                        <label for="valor">Data Fim: </label>
                                                        <input type="date" name="data_fim" id="data_receita" value="<fmt:formatDate pattern="dd-MM-yyyy" value="" />" />
                                                    </div>
                                                </div>
    
                                                <div class="btn-consult-data">
                                                    <button name="action" value="data"><i class="fas fa-calendar"></i> Consulta Periodo Por Data</button>
                                                    <button name="action" value="id"><i class="fas fa-user"></i> Consulta Periodo Por ID</button>
                                                    <button name="action" value="valor"><i class="fas fa-coins"></i> Consulta Periodo Por Valor</button>
                                                </div>
    
                                                <div class="btn-consultar">
                                                    <button name="action" value="consulta"><i class="fas fa-book-open"></i> Consulta Todas as Despesas</button>
                                                </div> 
                                            </div>
                                        </div>
                                        
                                        <div class="tabela-despesas">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Valor</th>
                                                        <th>Data</th>
                                                        <th>Vencimento</th>
                                                        <th>QTD Parcelas</th>
                                                        <th>Descricao</th>
                                                        <th>ID Carteira</th>
                                                        <th>ID Categoria</th>
                                                        <th colspan=2>Ação</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${lista_despesa}" var="despesa">
                                                        <tr>
                                                            <td><c:out value="${despesa.id_despesa}"/></td>
                                                            <td><c:out value="${despesa.valor}"/></td>
                                                            <td><fmt:formatDate value="${despesa.data}" pattern="dd/MM/yyyy" /></td>
                                                            <td><fmt:formatDate value="${despesa.vencimento}" pattern="dd/MM/yyyy" /></td>
                                                            <td><c:out value="${despesa.qtd_parcelas}"/></td>
                                                            <td><c:out value="${despesa.descricao}"/></td>
                                                            <td><c:out value="${despesa.carteira.id_carteira}"/></td>
                                                            <td><c:out value="${despesa.categoria.id_categoria}"/></td>
                                                            <td><a href="ControllerDespesa?action=alterar&id_despesa=<c:out value="${despesa.id_despesa}"/>"> <i class="fas fa-pen-fancy"></i> alterar</a></td>
                                                            <td><a href="ControllerDespesa?action=remover&id_despesa=<c:out value="${despesa.id_despesa}"/>"><i class="fas fa-trash"></i> remover</a></td>                         
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
          
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
