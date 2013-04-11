/*
   Copyright 2013  nerdErg Pty Ltd (info@nerderg.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
import com.nerderg.ajaxanywhere.AAFilter
import com.nerderg.ajaxanywhere.listener.JulToSlf4jBridgeListener
import com.nerderg.ajaxanywhere.servlet.StaticResourcesServlet

/**
 * @author Angel Ruiz (aruizca@gmail.com)
 */
class AjaxanywhereGrailsPlugin {
    // the plugin version
    def version = "2.0-SNAPSHOT"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.x > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Angel Ruiz Calvo"
    def authorEmail = "aruizca@gmail.com"
    def title = "AjaxAnywhere Grails Plugin"
    def description = '''\\
This plugin integrates the <a href="http://www.ajaxanywhere.com" target="_AjaxAnywhere">"AjaxAnywhere (Reloaded) library</a> seamlessly inside the Grails Framework ecosystem.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/ajaxanywhere"

    def doWithWebDescriptor = { webXml ->

        // AjaxAnywhere filter mapping
        def contextParam = webXml.'context-param'
        contextParam[contextParam.size() - 1] + {
            'filter' {
                'filter-name'('AjaxAnywhere')
                'filter-class'(AAFilter.name)
            }
        }

        def filter = webXml.'filter-mapping'.find {
            it.'filter-name'.text() == "springSecurityFilterChain"
        }
        filter + {
            'filter-mapping'{
                'filter-name'('AjaxAnywhere')
                'url-pattern'('/*')
            }
        }

        // Register listener to bridge AjaxAnywhere JUL based traces to SLF4J
        contextParam[contextParam.size() - 1] + {
            'listener' {
                'listener-class'(JulToSlf4jBridgeListener.name)
            }
        }

        // AjaxAnywhere static resource servlet mapping
        def servletElements = webXml.'servlet'
        def lastServletDefinition = servletElements[servletElements.size() - 1]
        lastServletDefinition + {
            'servlet' {
                'servlet-name'("AAResourcesServlet")
                'servlet-class'(StaticResourcesServlet.name)
            }
        }

        def servletMappingElement = webXml.'servlet-mapping'
        def lastMapping = servletMappingElement[servletMappingElement.size() - 1]
        lastMapping + {
            'servlet-mapping' {
                'servlet-name'("AAResourcesServlet")
                'url-pattern'("/js/jquery-aa.js")
            }
        }
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
