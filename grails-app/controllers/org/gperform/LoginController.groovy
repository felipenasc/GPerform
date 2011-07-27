package org.gperform

class LoginController {

    def index = { }
	
	def entrar = {
		Usuario u = Usuario.findByLogin(params.login)
		
		if(u){
			if(params.senha == u.senha){ // TODO usar .encodeAsSHA256() para a senha.
				session.usuario = new Expando(login:u.login, id:u.id)
				redirect(controller:"home")	
			}
		}
	}
	
	def sair = {
		session.usuario = null
		redirect(controller:"home")
	}
}
