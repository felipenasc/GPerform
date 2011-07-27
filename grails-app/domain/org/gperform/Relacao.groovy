package org.gperform

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Esta classe representa uma relacao de Seguidor entre um usuario e outro. Um eh o seguido, e o outro eh o seguidor.
 * @author Felipe
 *
 */
class Relacao implements Serializable{
	
	Usuario seguidor
	Usuario seguido

    static constraints = {
    }
	
	static mapping = {
		id composite: ['seguidor', 'seguido']
		version false
		table 'relacao_seguidor_seguido'
	}
	
	boolean equals(other) {
		if(other?.is(this)) return true
		if(other.class != Relacao.class) return false
		return other.seguidor.id == seguidor.id && other.seguido.id == seguido.id
	}
   
	int hashCode() {
		return new HashCodeBuilder().append(seguidor.id).append(seguido.id).toHashCode()
	}
	
}
