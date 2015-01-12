package com.nerderg.ajaxanywhere

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AAZoneTagLib)
class AAZoneTagLibSpec extends Specification {

    void "test taglib output"() {
        expect:
            applyTemplate('<aa:zone id="formZone">whatever</aa:zone>') == '<div id="formZone" style="display:inline;">whatever</div>'
    }
}
