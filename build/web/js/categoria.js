function update() {
    var select = document.getElementById('listacategoria');
    var option = select.options[select.selectedIndex];
    document.getElementById('id_categoria').value = option.value;
    document.getElementById('descricao_categoria').value = option.text;
}

function updateBanco() {
    var select = document.getElementById('listabancos');
    var option = select.options[select.selectedIndex];
    document.getElementById('id_conta').value = option.value;
    document.getElementById('nome_banco').value = option.text;
}


function updateBanco2() {
    var select = document.getElementById('listabancos');
    var option = select.options[select.selectedIndex];
    document.getElementById('codico_banco').value = option.value;
    document.getElementById('nome_banco').value = option.text;
}
function validarDataReceita(data) {
    dataAtual = new Date();
    data = new Date(data);
    data.setDate(data.getDate() + 1);
    console.log(data);

    if (data > dataAtual) {
        return true;
    } else {
        document.getElementById('data_receita').value = data;
        alert("A DATA NAO PODE SER MENOR QUE DATA ATUAL!");
        return false;
    }
}

function validarDataDespesa(data) {
    dataAtual = new Date();
    data = new Date(data);
    data.setDate(data.getDate() + 1);

    if (data > dataAtual) {
        return true;
    } else {
        alert("A DATA NAO PODE SER MENOR QUE DATA ATUAL!");
        document.getElementById('data_despesa').value = data;
        return false;
    }
}

function validarDataVencimento(data) {
    dataAtual = new Date();
    data = new Date(data);
    data.setDate(data.getDate() + 1);

    if (data > dataAtual) {
        return true;
    } else {
        alert("O VENCIMENTO NAO PODE SER MENOR QUE DATA ATUAL!");
        document.getElementById('vencimento').value = data;
        return false;
    }
}

