package org.gperform.events

import org.springframework.context.ApplicationEvent;

/**
 * Classe de evento usada para que o controller de upload de foto possa lançar um evento do tipo desta classe aqui.
 * Será usada posteriormente pela FotoService para "escutar" este tipo de evento e assim poder processar a foto que foi "subida" (upload)
 * pelo usuario  
 * @author Felipe
 *
 */
class FotoSubidaEvent extends ApplicationEvent {

	FotoSubidaEvent(Expando fotoOriginalSource){
		super(fotoOriginalSource)
	}
	
}
