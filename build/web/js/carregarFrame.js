/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function carregarCliente(){
    document.getElementById("frmCarteira").src = "./ControllerCategoria?action=consultar";
}

function carregarItem(){
    document.getElementById("fraFormulario").src = "./ItemBll?action=consultar";
}

function carregarPedido(){
    document.getElementById("fraFormulario").src = "./PedidoBll?action=consultar";
}

function carregarPedidoItem(){
    document.getElementById("fraFormulario").src = "./PedidoItemBll?action=consultar";
}
