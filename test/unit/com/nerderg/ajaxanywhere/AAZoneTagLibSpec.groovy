package com.nerderg.ajaxanywhere

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AAZoneTagLib)
class AAZoneTagLibSpec extends Specification {

    void "test taglib output 1"() {
        expect:
        applyTemplate('<aa:zone id="formZone">whatever</aa:zone>') == '<div id="formZone" style="display:inline;">whatever<!-- @end of zone [formZone]@ --></div>'
    }

    void "test taglib output 2"() {
        expect:
        applyTemplate('<aa:zone id="whatever" fragmentUrl="http://whatever.com/whatever" jsBefore="beforeJs();" jsAfter="afterJs();">whatever</aa:zone>') == '<div id="whatever" fragment-url="http://whatever.com/whatever" style="display:inline;" js-before="beforeJs();" js-after="afterJs();">whatever<!-- @end of zone [whatever]@ --></div>'
    }

    void "test taglib output 3"() {
        expect:
        applyTemplate('<aa:zone id="whatever" fragmentUrl="http://whatever.com/whatever" jsAfter="afterJs();">whatever</aa:zone>') == '<div id="whatever" fragment-url="http://whatever.com/whatever" style="display:inline;" js-after="afterJs();">whatever<!-- @end of zone [whatever]@ --></div>'
    }

    void "test taglib output 4"() {
        expect:
        applyTemplate('<aa:zone id="whatever" fragmentUrl="http://whatever.com/whatever" jsBefore="beforeJs();">whatever</aa:zone>') == '<div id="whatever" fragment-url="http://whatever.com/whatever" style="display:inline;" js-before="beforeJs();">whatever<!-- @end of zone [whatever]@ --></div>'
    }

    void "test taglib output 5"() {
        expect:
        applyTemplate('<aa:zone id="whatever" jsBefore="beforeJs();" jsAfter="afterJs();">whatever</aa:zone>') == '<div id="whatever" style="display:inline;">whatever<!-- @end of zone [whatever]@ --></div>'
    }

    void "test taglib output 6"() {
        expect:
        applyTemplate('<aa:zone id="whatever" tag="tbody">whatever</aa:zone>') == '<tbody id="whatever">whatever<!-- @end of zone [whatever]@ --></tbody>'
    }
}
