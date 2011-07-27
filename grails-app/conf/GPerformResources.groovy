modules = {
	gpcore {
		dependsOn 'jquery, jquery-ui, blueprint'
		defaultBundle 'gpcore'
		resource url:'/js/gpcore.js'
		resource url:'/css/gpcore.css'
		resource url:'/css/gplayout.css'
	}
	
	gpforms{
		dependsOn 'gpcore'
		defaultBundle 'gpcore'
		resource url:'/js/gpforms.js'
	}
	
	overrides{
		jquery{
			resource id:'js', bundle:'gpcore', disposition:'defer'
		}
		'jquery-theme' {
			resource id:'theme',bundle:'gpcore',
				url:'/css/gp-theme/jquery-ui-1.8.7.custom.css',
				attrs:[media:'screen, projection']
		}
		'jquery-ui' {
			resource id:'js', bundle:'gpcore'
			
		}
		'blueprint'{
			resource id:'main', bundle:'gpcore'
		}
	}

}