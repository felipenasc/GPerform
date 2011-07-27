<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>


<div class="arredondado maisRecentes">
	<h2>Mais Recentes</h2>
	<div>
		<g:each var="foto" in="${fotosMaisRecentes}" status="i">
			<img src="http://localhost/thumbs/50/${foto.nomeArquivo}" />
		</g:each>
	</div>
	<div style="float: right;"><a href="#" id="linkDialogoSolucaoMaisRecente">aqui tem cache</a></div>

</div>

<div title="Uso do Springcache Plugin" style="display: none;" id="dialogoSolucaoMaisRecente">
	Este bloco é atualizado por ajax, chamando a action "recentes" do HomeController.<br/> 
	Esta action é anotada com @Cacheable do Springcache Plugin.<br />
	Quando uma nova foto é enviada, ela é processada de forma assíncrona, através do serviço FotoService. 
	O método executado deste serviço é anotado com @CacheFlush, fazendo com que o cache de 
	Fotos Mais Recentes seja descartado (e recriado novamente na próxima vez que a action "recentes" for requisitada novamente).
</div>

<script type="text/javascript">
	$(document).ready(function() {
		preparaLinkDialogoMaisRecentes();
	});
</script>
