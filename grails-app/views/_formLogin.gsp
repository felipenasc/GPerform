<r:script>
	var limpouCampo=false;
	function apagaCampo(campoId){
		if(!limpouCampo) $('#'+campoId).val('');
		limpouCampo = true;
	}
</r:script>
<form action="${createLink(controller:'login', action:'entrar') }" method="post" id="loginForm">
	<input name="login" type="text" value="Login" onfocus="apagaCampo('login')" id="login">
	<input name="senha" type="password" value="Senha" onfocus="apagaCampo('passwd')" id="passwd">
	<input type="submit" value="OK">
</form>