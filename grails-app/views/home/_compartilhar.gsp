<div>
    		
	<g:form controller="home" action="compartilhar" method="post" enctype="multipart/form-data" onsubmit="enviaFotoClicked()" >
	
		<p>
			Mande sua imagem: <br />
			<input type="file" name="fotoOriginal" size="20" />
		</p>
		<p style="margin-top: 10px;">
			Comentário:<br />
			<textarea rows="3" cols="70" name="comentario" style="width: 400px;height: 50px;"></textarea><br>
		</p>
		
		<p style="margin-top: 10px;">
			<input type="submit" value="Compartilhar com o Mundo" id="uplBt" class="submitBtn" />
			<img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}"  style="display: none;" id="imgUplBt"/>
		</p>
		
	</g:form>
  		
</div>