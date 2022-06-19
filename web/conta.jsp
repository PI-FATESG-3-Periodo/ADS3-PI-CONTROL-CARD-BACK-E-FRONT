<%-- 
    Document   : conta
    Created on : 14 de jun. de 2022, 17:52:05
    Author     : WsmGyn
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
    <script src="./js/categoria.js"></script>
    <title>Conta - ControlCard - PI</title>

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
                    <form method="POST" action="ControllerConta" name="frmCarteira" id="frmCarteira">
                        <div class="title-categoria divisor-align">
                            <h1 class="title-conta">Cadastrar Conta</h1>
                            <div class="botoes">
                                <button name="botao" value="carteira"><i class="fas fa-wallet"></i> Carteira</button>
                                <button name="botao" value="banco"><i class="fas fa-coins"></i> Banco</button>
                                <button name="botao" value="poupanca"><i class="fas fa-piggy-bank"></i> Poupança</button>
                            </div>
                        </div>
                    
                        <div class="saldo-limite">
                            <div>
                                <input type="hidden" readonly="readonly"  name="id_carteira" value="<c:out value="${carteira.id_carteira}" />" />
                            </div>
                            <div>
                                <input type="hidden" readonly="readonly" name="id_usuario" value="<c:out value="${carteira.usuario.id_usuario}" />" />
                            </div>
                            <div>
                                <input type="text" readonly="readonly"  name="nome" value="<c:out value="${carteira.usuario.nome}" />" />
                            </div>
                            <div class="limite">
                                <label for="saldo_conta">Saldo Conta + Limite Conta: </label>
                                <input type="text" name="saldo_conta" value="<c:out value="${carteira.saldo_conta}" />" />
                            </div>
                        </div>

                        <div class="box-conta">
                            <div class="box-cadastrar-conta">
                                <h2>Selecione um Banco para Cadastrar a Conta:</h2>
                                <div>
                                    <label>Bancos:</label>
                                    <select name ="id_banco" id="listabancos" onchange="updateBanco()">
                                        <c:forEach items="${bancos2}" var="banco2">
                                            <option value="<c:out value="${banco2.id_banco}"/>" 
                                                    ${banco2.id_banco ==  banco2.id_banco ? 'selected' : ''}>                                
                                                <c:out value="${banco2.nome}"/>
                                            </option >
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <input type="hidden"  readonly="readonly" name="id_conta"  id="id_conta" value="" />
                                </div>
                                <div>
                                    <label for="nome_banco">Banco Selecionado: </label>
                                    <input type="text"   name="nome_banco" id="nome_banco" value="" />
                                </div>
                                <div>
                                <!--<td><label for="id_usario_carteira">ID_Banco: </label></td>-->
                                <input type="hidden"  type="text" readonly="readonly"  name="id_conta" value="<c:out value="${conta.id_conta}" />" />
                                </div>
                                <div>
                                    <label for="conta_numero">Numero da Conta: </label>
                                    <input type="text" name="numero_conta" value="<c:out value="${conta.numero_conta}" />" />
                                </div>
                                <div>
                                    <label for="limite_conta">Limite Conta: </label>
                                    <input type="text" name="limite_conta" value="<c:out value="${conta.limite}" default="0" />"  />
                                </div>
                                <div class="c-saldo-conta">
                                    <label for="saldo_conta">Saldo Conta: </label>
                                    <input type="hidden" name="saldo_conta" value="<c:out value="${conta.saldo_conta}" default="0" />"  />
                                </div>
                                <div class="c-data-criacao">
                                    <label for="data_criacao">Data Criação: </label>
                                    <input type="Date" onchange="validarDataDespesa(this.value)"  id="data_despesa" name="data_criacao"value="<c:out value="${conta.data_criacao}" />" />
                                </div>
                                <div class="btn-salvar">
                                    <input type="submit" name="botao" value="Salvar" />
                                </div>
                            </div>

                            <div class="box-movimentar-conta">
                                <h2>Movimentar Conta:</h2>
                                <div>
                                    <input type="hidden" readonly="readonly"  name="id_conta2" value="<c:out value="${conta2.id_conta}" />" />
                                </div>
                                <div>
                                    <label for="conta_numero">Nome Banco: </label>
                                    <input type="text" name="nome_banco_conta2" value="<c:out value="${conta2.banco.nome}" />" />
                                </div>
                                <div>
                                    <label for="conta_numero">Agencia: </label>
                                    <input type="text" name="agencia_banco_conta2" value="<c:out value="${conta2.banco.agencia}" />" />
                                </div>
                                <div>
                                    <label for="conta_numero">Numero da Conta: </label>
                                    <input type="text" name="numero_conta2" value="<c:out value="${conta2.numero_conta}" />" />
                                </div>
                                <div>
                                    <!--<td><label for="id_mov_conta">ID_Movimentação da Conta : </label></td>-->
                                    <input   type="hidden" id="id_mov"  name="id_mov_conta" value="<c:out value="${mov_conta.id_mov_conta}" />" />
                                </div>
                                <div>
                                    <label for="id_valor">Valor : </label>
                                    <input   type="text" name="valor_mov" id="valor_desejado" value="<c:out value="${mov_conta.valor_mov}"/>" />
                                </div>
                                <div class="c-data-mov">
                                    <label for="data_mov">Data da Movimentação da Conta : </label>
                                    <input type="date" id="data_mov_conta"  name="data_mov_conta" value="<c:out value="${mov_conta.data_mov}"/>" />
                                </div>
                                <div>
                                    <label for="tipo_mov">Tipo de Movimentação : </label>
                                    <input type="text"  id="text_mov"  name="tipo_mov" value="<c:out value="${mov_conta.tipo_mov}" />" />
                                </div>
                                <div class="box-botoes-sac">
                                    <div class="btn-sacar">
                                        <input type="submit" id="btn_sacar" name="botao" onmouseover="tipo_mov_conta(this).toString()" value="Sacar" />
                                    </div>
                                   <div class="btn-depositar">
                                        <input type="submit" id="btn_depositar" name="botao" onmouseover="tipo_mov_conta(this).toString()"  value="Depositar" />
                                   </div>
                                    
                                </div>
                            </div> 
                        </div>    
                    </form>

                    <div class="conta-consultar">
                        <form method="GET" action="ControllerConta" name="frmTabelaCliente">
                            <h3>Selecione uma Conta para Movimentação (SACAR E DEPOSITAR):</h3>
                            <button name="action" value="consulta"><i class="fas fa-book-open"></i> Consultar Todas as Contas</button><br><br>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID Conta</th>
                                        <th> Numero Conta</th>
                                        <th>Limite</th>
                                        <th>Saldo Conta</th>
                                        <th>Data Criacao</th>
                                        <th>ID Carteira</th>
                                        <th>ID Banco</th>
                                        <th colspan=2>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${contas3}" var="contas3">
                                        <tr>
                                            <td><c:out value="${contas3.id_conta}" /></td>
                                            <td><c:out value="${contas3.numero_conta}" /></td>
                                            <td><c:out value="${contas3.limite}" /></td>
                                            <td><c:out value="${contas3.saldo_conta}" /></td>
                                            <td><fmt:formatDate value="${contas3.data_criacao}" pattern="dd/MM/yyyy" /></td>
                                            <td><c:out value="${contas3.carteira.id_carteira}" /></td>
                                            <td><c:out value="${contas3.banco.id_banco}" /></td>
                                            <td><a href="ControllerConta?action=alterar&id_conta3=<c:out value="${contas3.id_conta}"/>">Selecionar</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </form> 
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

    <script>
        function tipo_mov_conta(obj)
        {
            // console.log("FUNCIONOU");
            var btn_sacar = document.getElementById("btn_sacar");
            var btn_depositar = document.getElementById("btn_depositar");

            if (obj === btn_sacar)
            {
                // console.log("SACAR");

                obj = document.getElementById("text_mov").style.color = "red";
                obj = document.getElementById("text_mov").value = "Sacado";
                //console.log("obj : " + obj);

                //  obter_data_hoje();

            } else if (obj === btn_depositar) {
                console.log("DEPOSITAR");
                obj = document.getElementById("text_mov").style.color = "green";
                obj = document.getElementById("text_mov").value = "Depositado";
                // obter_data_hoje();
            }
        }
        // onkeyup = "obter_data_hoje()";

        //  function obter_data_hoje() {
        //   var data = new Date();
        //data.setDate(data.getDate()).padStart(2, '0');
        //data.setDate(data.getMonth() + 1).padStart(2, '0');
        //var ano = data.getFullYear();
        // dataAtual = dia + "/" + mes + "/" + ano;
        //    console.log("Data de hoje :" + data.toDateString());
        //     document.getElementById("data_mov_conta").value = data.toLocaleDateString();
        //   }
        //  obter_data_hoje();
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>

</body>
    
</body>
</html>