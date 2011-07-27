package org.gperform

import java.io.Serializable;

class Perfil  implements Serializable{
	
	Usuario usuario
	
	static belongsTo = Usuario
	

    static constraints = {
    }
}
