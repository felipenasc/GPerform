package org.gperform

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Esta classe representa uma Avaliacao que um usuario faz de uma Foto (boa ou ruim)
 * @author Felipe
 *
 */
class Avaliacao implements Serializable {
	
	Foto foto
	Usuario avaliador
	Date dateCreated
	boolean boa
	
    static constraints = {
		avaliador(nullable:false)
		foto(nullable:false)
    }
	
	static mapping = {
		id composite: ['foto', 'avaliador']
		version false
		table 'foto_avaliacao'
	}
	
	boolean equals(other) {
		if(other?.is(this)) return true
		if(other.class != Avaliacao.class) return false
		return other.foto.id == foto.id && other.avaliador.id == avaliador.id
	}
   
	int hashCode() {
		return new HashCodeBuilder().append(foto.id).append(avaliador.id).toHashCode()
	}

}
