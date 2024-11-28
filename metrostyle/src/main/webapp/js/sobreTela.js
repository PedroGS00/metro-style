const gA = document.querySelector(".sobreTela-abrir");

const divXCAD = document.querySelector(".fechar-tela-cad");
const divXEDIT = document.querySelector(".fechar-tela-edit");

const divTelaCAD = document.querySelector(".sobreTela-abrir-cadastro");
const divTelaEDIT = document.querySelector(".sobreTela-abrir-editar");

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

function abrirDivEDIT_Prod(id, nome, desc, preco, estoque) {
    gA.style.visibility = "visible";
    divTelaEDIT.style.visibility = "visible";
    divXEDIT.style.visibility = "visible";

    document.querySelector('#id_produto').value = id;
    document.querySelector('#nome').value = nome;
    document.querySelector('#descricao').value = desc;
    document.querySelector('#preco').value = preco;
    document.querySelector('#estoque').value = estoque;
}

function abrirDivEDIT_Cli(id, nome, email, senha) {
    gA.style.visibility = "visible";
    divTelaEDIT.style.visibility = "visible";
    divXEDIT.style.visibility = "visible";

    document.querySelector('#id_cliente').value = id;
    document.querySelector('#nome').value = nome;
    document.querySelector('#user').value = email;
    document.querySelector('#senha').value = senha;
}

function abrirDetalhes_Relatorio(id_item_venda, id_venda, id_cliente, cliente_nome, produto_nome, preco_unitario, quantidade, subtotal, valor_total, data_venda) {
    gA.style.visibility = "visible";
    divTelaEDIT.style.visibility = "visible";
    divXEDIT.style.visibility = "visible";

    document.querySelector('#id_item_venda').value = id_item_venda;
    document.querySelector('#id_venda').value = id_venda;
    document.querySelector('#id_cliente').value = id_cliente;
    document.querySelector('#cliente_nome').value = cliente_nome;
    document.querySelector('#produto_nome').value = produto_nome;
    document.querySelector('#preco_unitario').value = preco_unitario;
    document.querySelector('#quantidade').value = quantidade;
    document.querySelector('#subtotal').value = subtotal;
    document.querySelector('#valor_total').value = valor_total;
    document.querySelector('#data_venda').value = data_venda;
}