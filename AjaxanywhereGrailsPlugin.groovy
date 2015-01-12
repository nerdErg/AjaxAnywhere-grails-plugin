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
    def version = "1.0-SNAPSHOT"
    def grailsVersion = "1.3.x > *"
    def pluginExcludes = [
        "web-app/WEB-INF/**"
    ]

    def title = "AjaxAnywhere Grails Plugin"
    def description = '''\\
This plugin integrates the <a href="http://www.ajaxanywhere.com" target="_AjaxAnywhere">"AjaxAnywhere (Reloaded) library</a> seamlessly inside the Grails Framework ecosystem.
'''

    def documentation = "http://www.ajaxanywhere.com/"
    def license = "APACHE"
    def organization = [ name: "nerdErg Pty. Ltd.", url: "http://www.nerderg.com/" ]
    def author = "Angel Ruiz"
    def authorEmail = "aruizca@gmail.com"
    def developers = [
        [ name: "Angel Ruiz", email: "aruizca@gmail.com" ]
    ]
    def issueManagement = [ system: "GitHub", url: "https://github.com/nerdErg/AjaxAnywhere-grails-plugin/issues" ]
    def scm = [ url: "https://github.com/nerdErg/AjaxAnywhere-grails-plugin" ]

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
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
