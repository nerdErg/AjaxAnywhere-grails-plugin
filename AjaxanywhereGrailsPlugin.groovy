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

/**
 * @author Angel Ruiz (aruizca@gmail.com)
 */
class AjaxanywhereGrailsPlugin {
    def version = "1.1-SNAPSHOT"
    def grailsVersion = "1.3.x > *"
    def title = "AjaxAnywhere Grails Plugin"
    def description = 'Integrates the <a href="http://www.ajaxanywhere.com" target="_AjaxAnywhere">"AjaxAnywhere (Reloaded) library</a> seamlessly.'
    def documentation = "http://ajaxanywhere.com/documentation.html"
    def license = "APACHE"
    def organization = [ name: "nerdErg Pty. Ltd.", url: "http://www.nerderg.com/" ]
    def developers = [
        [ name: "Angel Ruiz", email: "aruizca@gmail.com" ]
    ]
    def issueManagement = [ system: "GitHub", url: "https://github.com/nerdErg/AjaxAnywhere-grails-plugin/issues" ]
    def scm = [ url: "https://github.com/nerdErg/AjaxAnywhere-grails-plugin" ]
    def loadAfter = ['resources']

    def doWithWebDescriptor = { webXml ->

        // AjaxAnywhere filter mapping
        def filters = webXml.filter[0]
        filters + {
            'filter' {
                'filter-name'('AjaxAnywhere')
                'filter-class'(AAFilter.name)
            }
        }

        filters = webXml.filter
        filters[filters.size() -1 ] + {
            'filter-mapping'{
                'filter-name'('AjaxAnywhere')
                'url-pattern'('/*')
            }
        }

        // Register listener to bridge AjaxAnywhere JUL based traces to SLF4J
        def contextParam = webXml.'context-param'
        contextParam[contextParam.size() - 1] + {
            listener {
                'listener-class'(JulToSlf4jBridgeListener.name)
            }
        }
    }
}
