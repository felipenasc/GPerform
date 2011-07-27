// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }
	root {
		info 'stdout'
		additivity = false
	}
	
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

environments {
	production {
		gp.fotos.originais.folder="/home/gp/fotos/originais"
		gp.fotos.processadas.folder="/home/gp/fotos/processadas"
		gp.fotos.thumbs150.folder="/home/gp/fotos/thumbs/150"
		gp.fotos.thumbs50.folder="/home/gp/fotos/thumbs/50"
		gp.fotos.thumbs32.folder="/home/gp/fotos/thumbs/32"
	}
	development {
		gp.fotos.originais.folder="/Users/Felipe/temp/fotos/originais"
		gp.fotos.processadas.folder="/Users/Felipe/temp/fotos/processadas"
		gp.fotos.thumbs150.folder="/Users/Felipe/temp/fotos/thumbs/150"
		gp.fotos.thumbs50.folder="/Users/Felipe/temp/fotos/thumbs/50"
		gp.fotos.thumbs32.folder="/Users/Felipe/temp/fotos/thumbs/32"
	}

}

springcache {
	defaults {
		eternal = false
		diskPersistent = false
		timeToLive= 86400
	}
	caches {
		recentesCache {
			timeToLive= 3600
		}
	}
}



// ##########################################################################################
// ########  ESTA DEVE SER A ULTIMA DO ARQUIVO       ########################################
// ##########################################################################################
// Configuracao para que se olhe configuracoes externas para esta aplicacao, a partir de variavel de ambiente do servidor
// foi criado no servidor de producao, em /home/tomcat/configs/GPerformConfig.groovy
// e foi adicionada a variavel de ambiente GPERFORM_CONFIG no startUp.sh que inicializa o tomcat
// Esta configuracao deve ser a ultima deste arquivo para que as configuracoes externas se sobreponham a estas deste arquivo
def ENV_NAME = "GPERFORM_CONFIG"
if(!grails.config.locations || !(grails.config.locations instanceof List)) {
	grails.config.locations = []
}
if(System.getenv(ENV_NAME)) {
	println "Incluindo arquivo de configuracao especificado no ambiente: " + System.getenv(ENV_NAME);
	grails.config.locations << "file:" + System.getenv(ENV_NAME)
} else if(System.getProperty(ENV_NAME)) {
	println "Incluindo arquivo de configuracao especificado na linha de comando: " + System.getProperty(ENV_NAME);
	grails.config.locations << "file:" + System.getProperty(ENV_NAME)
} else {
	println "Nenhum arquivo externo de configuracao foi definido."
}
// ##########################################################################################
