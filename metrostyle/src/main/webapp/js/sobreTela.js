const gA = document.querySelector(".manutencao-abrir");

const divXCAD = document.querySelector(".fechar-tela-cad");
const divXEDIT = document.querySelector(".fechar-tela-edit");

const divTelaCAD = document.querySelector(".manutencao-abrir-cadastro");
const divTelaEDIT = document.querySelector(".manutencao-abrir-editar");

function fecharDivCAD(){
    gA.style.visibility = "hidden";
    divTelaCAD.style.visibility = "hidden";
    divXCAD.style.visibility = "hidden";
}

function abrirDivCAD(){
    gA.style.visibility = "visible";
    divTelaCAD.style.visibility = "visible";
    divXCAD.style.visibility = "visible";
}


function fecharDivEDIT(){
    gA.style.visibility = "hidden";
    divTelaEDIT.style.visibility = "hidden";
    divXEDIT.style.visibility = "hidden";
}

function abrirDivEDIT_Prod(id, marca, desc, valor) {
    gA.style.visibility = "visible";
    divTelaEDIT.style.visibility = "visible";
    divXEDIT.style.visibility = "visible";

    document.querySelector('#id_produto').value = id;
    document.querySelector('#marca').value = marca;
    document.querySelector('#descricao').value = desc;
    document.querySelector('#valor').value = valor;
}

function abrirDivEDIT_Cli(id, nome, user, senha) {
    gA.style.visibility = "visible";
    divTelaEDIT.style.visibility = "visible";
    divXEDIT.style.visibility = "visible";

    document.querySelector('#id_cliente').value = id;
    document.querySelector('#nome').value = nome;
    document.querySelector('#user').value = user;
    document.querySelector('#senha').value = senha;
}