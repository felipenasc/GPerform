package org.gperform

import java.io.Serializable;

class Foto  implements Serializable{
	
	Usuario autor
	boolean processada = false
	String url
	String comentario
	String nomeArquivo
	Date dateCreated
	
    static constraints = {
		url(nullable:true)
		comentario(nullable:true, blank:true)
		autor(nullable:false)
		nomeArquivo(nullable:false)
    }
	
	static mapping = {
		autor fetch:'join'
		comentario type: 'text'
	}
}
