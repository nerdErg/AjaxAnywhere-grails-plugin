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
package ajaxanywhere

/**
 * @author Angel Ruiz (aruizca@gmail.com)
 *
 * This taglib is the Grails equivalent to the Java taglib ZoneTag included in the AjaxAnywhere JAR file.<br/>
 * We thought this might be more convenient for Grails developers
 */
class AAZoneTagLib {

    static namespace = "aa"

    def zone = { attrs, body ->
        out << "<div id=\"${attrs.id}\" style=\"display:inline;\">"
        out << body()
        out << "</div>"
    }

}
