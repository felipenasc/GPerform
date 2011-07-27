<html>
    <head>
        <meta name="layout" content="main" />
		<r:require modules="gpforms" />
		
        <r:script>
			/*$(document).ready(function() {
				$( "#dialog-message" ).dialog({
					modal: true,
					buttons: {
						Ok: function() {
							$( this ).dialog( "close" );
						}
					}
				});
			});*/
      	</r:script>
		
    </head>
    <body>
    	
    	<g:if test="${flash.message}">
    		<div class="message">${ flash.message }</div>
    	</g:if>
    	<g:if test="${msgErro}">
    		<div class="errors">${ msgErro }</div>
    		<g:hasErrors bean="${foto}">
			    <div class="errors">
			       <g:renderErrors bean="${foto}" as="list" />
			    </div>
			</g:hasErrors>
    	</g:if>
    
    	<g:if test="${session.usuario }">
    		<g:render template="compartilhar" />
    	</g:if>
    	<g:else>
    		Faça login acima!!
    	</g:else>
    	
    	
    	
        <!-- <div id="dialog-message" style="display:none;">
           Hello World
      	</div> -->
    </body>
</html>
