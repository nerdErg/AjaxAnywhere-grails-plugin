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
package com.nerderg.ajaxanywhere

/**
 * @author Angel Ruiz (aruizca@gmail.com)
 *
 * This taglib is the Grails equivalent to the Java taglib ZoneTag included in the Aj`axAnywhere JAR file.<br/>
 * We thought this might be more convenient for Grails developers
 */
class AAZoneTagLib {

    static namespace = "aa"

    /**
     * @attr id REQUIRED DOM id of the div element that will get updated
     * @attr tag html inline or block element tag to be generated instead of the default 'div' block tag
     * @attr fragmentUrl url to get content when page is loaded
     * @attr jsBefore javascript that will get evaluated before the Ajax request
     * @attr jsAfter javascript that will get evaluated after the successful Ajax request
     */
    def zone = { attrs, body ->

        out << "${attrs.fragmentUrl ? AAUtils.getZoneStartDelimiter(attrs.id, attrs.tag?: 'div', attrs.fragmentUrl, attrs.jsBefore, attrs.jsAfter) : AAUtils.getZoneStartDelimiter(attrs.id, attrs.tag?: 'div')}"
        out << body()
        out << "${AAUtils.getZoneEndDelimiter(attrs.id, attrs.tag?: 'div')}"
    }

}
