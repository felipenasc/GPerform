// Arquivo core do GPerform

var tcContext = ""; // contexto do tomcat
function setTcContext(){
	if(tcContext == ""){
		tcContext = $("#tcContext").val();
	}
}
$(document).ready(function() {
	$("#spinner").ajaxStart(function() {
		var style = {
			left : (($(window).width() / 2) - ($(this).width() / 2)) + "px"
		};
		$(this).css(style).show()
	});
	$("#spinner").ajaxStop(function() {
		$(this).hide()
	});
	setTcContext();
});

function carregaFotosRecentes(){
	$('#conteudoLateral').load(tcContext + '/home/recentes');
}

function preparaLinkDialogoMaisRecentes(){
	$('#linkDialogoSolucaoMaisRecente').click(function(event){
		$( "#dialogoSolucaoMaisRecente" ).dialog({
			width:400,
			modal: true,
			buttons: {Ok: function() {$( this ).dialog( "close" );}}
		});
		event.preventDefault();
	});
}