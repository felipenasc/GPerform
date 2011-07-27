package org.gperform

import org.springframework.context.ApplicationListener
import org.gperform.events.FotoSubidaEvent
import com.thebuzzmedia.imgscalr.Scalr;

import grails.plugin.springcache.annotations.CacheFlush;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.groovy.grails.commons.ConfigurationHolder

/**
 * Classe de servico para processar, redimensionar a imagem enviada pelo usuario. Serve apebnas para ilustrar o uso do Spring Events para 
 * execução assíncrona de tarefas. 
 * @author Felipe
 *
 */
class FotoService implements ApplicationListener<FotoSubidaEvent>{
	
	private static int OUTPUT_IMAGE_TARGET = 800;
	private static int THUMBNAIL_TARGET = 200;
	
    static transactional = true

	@CacheFlush("recentesCache")
	void onApplicationEvent(FotoSubidaEvent event) {
		if(log.isInfoEnabled()) log.info "Executando evento FotoSubidaEvent..."
		
		Foto foto = Foto.get(event.source.id)
		if(foto){
			BufferedImage imgOrigem = ImageIO.read(new File(ConfigurationHolder.config.gp.fotos.originais.folder + File.separator + foto.nomeArquivo))
			
			BufferedImage imgDestino150 = null
			BufferedImage imgDestino50 = null
			BufferedImage imgDestino32 = null
			
			if(log.isDebugEnabled()){
				log.debug "Altura original: " + imgOrigem.height
				log.debug "Largura original: " + imgOrigem.width
			}
			
			processaImagem(imgOrigem, foto, OUTPUT_IMAGE_TARGET, ConfigurationHolder.config.gp.fotos.processadas.folder)
			processaImagem(imgOrigem, foto, 150, ConfigurationHolder.config.gp.fotos.thumbs150.folder)
			processaImagem(imgOrigem, foto, 50, ConfigurationHolder.config.gp.fotos.thumbs50.folder)
			processaImagem(imgOrigem, foto, 32, ConfigurationHolder.config.gp.fotos.thumbs32.folder)
		}
		
	}

	private boolean processaImagem(BufferedImage imgOrigem, Foto foto, int dimensaoAlvo, String diretorioDestino) {
		BufferedImage imgDestino = null
		boolean processada = false
		if(imgOrigem.height >= imgOrigem.width){
			if(imgOrigem.height > dimensaoAlvo){
				log.debug "A altura eh maior que a largura. Foto eh Portrait"
				imgDestino = Scalr.resize(imgOrigem, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, dimensaoAlvo)
				processada = true
			}
		}
		else{
			if(imgOrigem.width > dimensaoAlvo){
				if(log.isDebugEnabled()) log.debug "A largura eh maior que a altura. Foto eh Landscape"
				imgDestino = Scalr.resize(imgOrigem, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, dimensaoAlvo)
				processada = true
			}
		}

		if(log.isDebugEnabled()) {
			log.debug "Altura final: " + imgDestino.height
			log.debug "Largura final: " + imgDestino.width
		}

		if(processada){
			File imagemProcessada = new File(diretorioDestino + File.separator + foto.nomeArquivo)
			String formatName = foto.nomeArquivo.substring(foto.nomeArquivo.lastIndexOf('.')+1);
			ImageIO.write(imgDestino, formatName.toUpperCase(), imagemProcessada)
			foto.processada = true // o GORM ira salvar este objeto pois ele foi alterado
		}
		else{
			FileUtils.copyFile(
					new File(ConfigurationHolder.config.gp.fotos.originais.folder + File.separator + foto.nomeArquivo),
					new File(diretorioDestino + File.separator + foto.nomeArquivo)
				)
			foto.processada = true // o GORM ira salvar este objeto pois ele foi alterado
		}
	}

}
