<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <r:layoutResources />
        <g:layoutHead />
        <r:script>
        	$(document).ready(function() {
        		carregaFotosRecentes();
        	});
        </r:script>
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        
        <div class="container">
        	<div id="header">
        		<div class="span-12 logo">Grails Performance</div>
        		<div class="span-12 last hdireita">
        			<g:if test="${session.usuario}">
        				Olá ${session.usuario.login} [<a href="${createLink(controller:'login', action:'sair') }">sair</a>]
        			</g:if>
        			<g:else>
        				<g:render template="/formLogin" />
        			</g:else>
        		</div>
        		
        	</div>
        
	        <div id="conteudoPrincipal">
        		<div id="conteudoCentral" class="span-16">
        			<g:layoutBody />
	        	</div>
	        	<div id="conteudoLateral" class="span-7 last arredondado">
	        		<img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
	        	</div>
        	
	        	<p style="clear: both;"></p>
	        </div>
	        
	        <div id="footer">
	        	Yeste
	        </div>
        </div>
        
        <input type="hidden" id="tcContext" value="${org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getContextPath()}" />
        
        <r:layoutResources />
    </body>
</html>