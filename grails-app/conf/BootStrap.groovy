import org.gperform.*
import org.codehaus.groovy.grails.plugins.codecs.*

class BootStrap {

    def init = { servletContext ->
		
		
		20.times{
			new Usuario(login:'usu$it', senha:'senha$it').save()
		}		
		
		def eumesmo = new Usuario(login:'eumesmo', senha:'1234').save()
		
		eumesmo.seguir(Usuario.findByLogin('usu1'))
		eumesmo.seguir(Usuario.findByLogin('usu7'))
		eumesmo.seguir(Usuario.findByLogin('usu8'))
		eumesmo.seguir(Usuario.findByLogin('usu11'))
		eumesmo.seguir(Usuario.findByLogin('usu18'))
		
		
    }
	
	
    def destroy = {
    }
}
