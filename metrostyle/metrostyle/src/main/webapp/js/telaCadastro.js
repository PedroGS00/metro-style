const gA = document.querySelector(".manutencao-abrir");
const divTela = document.querySelector(".manutencao-abrir div");
const divX = document.querySelector(".fechar-tela");

function fecharDiv(){
    gA.style.visibility = "hidden";
    divTela.style.visibility = "hidden";
    divX.style.visibility = "hidden";
}

function abrirDiv(){
    gA.style.visibility = "visible";
    divTela.style.visibility = "visible";
    divX.style.visibility = "visible";
}