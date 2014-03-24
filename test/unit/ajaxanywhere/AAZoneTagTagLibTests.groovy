package ajaxanywhere

import grails.test.*
import org.junit.Test

class AAZoneTagTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	@Test
    void testSomething() {
		tagLib.zone('formZone') { }
		println tagLib.out.toString()
    }
}
