modules = {

    ajaxanywhere {
        dependsOn 'jquery'

        resource url:[plugin: 'ajaxanywhere', dir: 'js',file:'jquery-ajaxq-0.0.2.js']
        resource url:[plugin: 'ajaxanywhere', dir: 'js',file:'aa.js']
    }
    
}