
function validaEmail(email) {
    var re = /\S+@\S+\.\S+/;
    if (re.test(email) || email === "admin") {
        return true;
    } else {
        confirm("EMAIL INVALIDO!");
        document.getElementById('email').value = "";
        return false;
    }
}


function verificarCPF(cpf) {
    // Remove os pontos/traço da expressão regular, caso exista
    cpf = cpf.replace(/[^\d]+/g, '');
    //console.log(cpf);
    if (cpf == '') {
        console.log(cpf + "tr");
        return true;
    } else {
        console.log(cpf + "ff");
        return false;
    }
}

function Vcpf(v) {
    v = v.replace(/\D/g, "");                    //Remove tudo o que não é dígito
    v = v.replace(/(\d{3})(\d)/, "$1.$2");       //Coloca um ponto entre o terceiro e o quarto dígitos
    v = v.replace(/(\d{3})(\d)/, "$1.$2");       //Coloca um ponto entre o terceiro e o quarto dígitos
    //de novo (para o segundo bloco de números)
    v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    console.log(v);//Coloca um hífen entre o terceiro e o quarto dígitos
    document.getElementById('cpf').value = v;
    return v;
}