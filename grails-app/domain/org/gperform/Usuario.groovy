package org.gperform

import java.io.Serializable;
import java.util.List;

class Usuario  implements Serializable{
	
	String login
	String senha
	Date dateCreated
	Date lastUpdated
	
	Perfil perfil
	
	//static hasMany = [seguidos:Usuario, fotos:Foto]
	
	static mapping = {
		//perfil fetch:'join' // GORM vai executar apenas uma query. Poderia usar lazy: false, mas neste caso seriam duas queries.
		// esta comentado pois talvez nao seja boa ideia trazer sempre o Perfil em qualquer busca pelo Usuario 
	}

    static constraints = {
		login(size:3..20, unique: true)
		senha(size: 4..30, validator: { passwd, user ->
			passwd != user.login
		})
		perfil(nullable: true)
    }
	
	boolean seguir(Usuario seguido, boolean flush = false){
		return new Relacao(seguidor: this, seguido:seguido).save(flush:flush)
	}
	
	boolean avaliar(Foto foto, boolean boa, boolean flush = false){
		return new Avaliacao(usuario:this, foto:foto, boa:boa).save(flush:flush)
	}
	
	List ultimasBoas(int qtde){
		return Foto.executeQuery("select a.foto from Avaliacao a where a.avaliador = :avaliador and a.boa = :boa ", [avaliador:this, boa:true], [max:qtde])
	}
}
