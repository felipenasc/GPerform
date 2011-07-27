package org.gperform

import grails.plugin.springcache.annotations.CacheFlush;
import grails.plugin.springcache.annotations.Cacheable;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile
import org.apache.commons.lang.RandomStringUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.gperform.events.FotoSubidaEvent;

class HomeController {

    def index = {
			
	}
	
	@Cacheable("recentesCache")
	def recentes = {
		log.info "Executando action 'recentes' e por isso nao ta usando o cache agora."
		def recentes = buscaFotosMaisRecentes(20)
		[fotosMaisRecentes:recentes]
	}
	
	def compartilhar = {
		
		String msgErro = ""
		
		if(!session.usuario){
			redirect(controller:'home')
			return
		}
		
		// Pega a foto do formulario e salva no diretorio para ser processada
		MultipartFile fotoOriginal = request.getFile('fotoOriginal')
		String extensao = identificaExtensao(fotoOriginal)
		
		String nomeArquivo = RandomStringUtils.random(10).encodeAsSHA256() + extensao
		
		try {
			File destino = new File(ConfigurationHolder.config.gp.fotos.originais.folder + File.separator + nomeArquivo)
			fotoOriginal.transferTo(destino)
			
			// Salva uma isntancia do objeto de metadados Foto
			Foto foto = new Foto(params)
			foto.autor = Usuario.get(session.usuario.id)
			foto.nomeArquivo = nomeArquivo
			if(foto.save(flush:true)){
				
				// metodo adicionado pelo spring-events Plugin
				publishEvent(new FotoSubidaEvent(new Expando(id:foto.id)))
				flash.message = "Foto enviada com sucesso. Aguarde processamento."
				redirect(action:index)
			}
			else{
				if(destino.exists()) destino.delete()
				msgErro = "Erro ao salvar os dados da foto."
				render(view:'index', model:[msgErro:msgErro, foto:foto])
			}
			
			
			
			
		} catch (IOException ioe) {
			msgErro = "Erro ao fazer upload do arquivo."
		} catch (IllegalStateException ie) {
			msgErro = "Erro ao fazer upload do arquivo."
		}
		
		
		
		
	}
	
	private List buscaFotosMaisRecentes(int qtde){
		Foto.executeQuery("from Foto f where f.processada = :proc order by f.dateCreated desc", [proc:true], [max:qtde])
	}
	
	private String identificaExtensao(MultipartFile file){
		String contentType = file.getContentType();
		String fileExtension = null;
		if (contentType.equalsIgnoreCase("image/pjpeg") || contentType.equalsIgnoreCase("image/jpeg")) {
			fileExtension = ".jpg";
		}
		else if (contentType.equalsIgnoreCase("image/gif")) {
			fileExtension = ".gif";
		}
		else if (contentType.equalsIgnoreCase("image/png")) {
			fileExtension = ".png";
		}
		
		if(!fileExtension){
			fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
		}

		return fileExtension;
	}

	private static boolean canReadFormat(String formatName) {
		Iterator iter = ImageIO.getImageReadersByFormatName(formatName);
		return iter.hasNext();
	}
	
}
