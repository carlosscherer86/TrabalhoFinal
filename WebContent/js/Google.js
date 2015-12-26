function atualizaImagem(pNomeImagem){ 
	document.images["imagemModal"].src = pNomeImagem;  
}

function habilitarValor(){ 
	var valorselecionado = document.getElementById('cadastro:TipoOferta').value;  
	if(valorselecionado == 1 || valorselecionado == 2){ 
		document.getElementById("cadastro:valorUni").style.visibility = "visible";
		document.getElementById("cadastro:valorUni").required = true;
		document.getElementById("valor").style.visibility = "visible";
	}else{
		document.getElementById("cadastro:valorUni").style.visibility = "hidden";
		document.getElementById("cadastro:valorUni").required = false;
		document.getElementById("valor").style.visibility = "hidden";
	} 
}

function geocode(cidade, bairro, endereco, cep, numero) {
	PF('geoMap').geocode(cidade+' '+bairro + ' '+ endereco +' '+numero);
}

function validaSenha(){ 
	var senha1 = document.getElementById('cadastro:senha').value; 
	var senha2 = document.getElementById('cadastro:senhaConfirma').value; 
	if(senha1 != senha2){ 
		document.getElementById('cadastro:senha').value= ""; 
		document.getElementById('cadastro:senhaConfirma').value= "";
		alert("Erro na confirmação da senha. Valor inválido."); 
	} 
}


$("#myModal").on("show", function() {    // wire up the OK button to dismiss the modal when shown
    $("#myModal a.btn").on("click", function(e) {
        console.log("button pressed");   // just as an example...
        $("#myModal").modal('hide');     // dismiss the dialog
    });
});
$("#myModal").on("hide", function() {    // remove the event listeners when the dialog is dismissed
    $("#myModal a.btn").off("click");
});

$("#myModal").on("hidden", function() {  // remove the actual elements from the DOM when fully hidden
    $("#myModal").remove();
});

$("#myModal").modal({                    // wire up the actual modal functionality and show the dialog
  "backdrop"  : "static",
  "keyboard"  : true,
  "show"      : true                     // ensure the modal is shown immediately
});
