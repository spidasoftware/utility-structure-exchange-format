package org.usmaw.schema.validation

import junit.framework.TestCase

/**
 * Created with IntelliJ IDEA.
 * User: mford
 * Date: 7/24/13
 * Time: 2:45 PM
 */
class ValidatorTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	void testOneOfEverythingObject() throws Exception {
		def schema = "/v1/schema/structure/structure.schema"
		def instance = new File("resources/v1/examples/designs/one_of_everything.json").text
		def report = new Validator().validateAndReport(schema, instance)
		assertTrue "the instance should be valid against a schema", report.isSuccess()

		def exception = null
		try {
			new Validator().validateAndReport(schema, instance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNull("Should not have thrown a servlet exception", exception)
	}

	void testBadObject() throws Exception {
		def schema = "/v1/schema/structure/structure.schema"
		def instance = '{"id":"externalId", "distance":{"unit":"FOOT", "value":10}, "direction":0}'
		def report = new Validator().validateAndReport(schema, instance)
		assertFalse "the instance should be valid against a schema", report.isSuccess()
	}

	void testBadInput() throws Exception {
		def badSchema = "/"
		def schema = "/v1/schema/structure/structure.schema"
		def instance = '{"id":"externalId", "distance":{"unit":"FOOT", "value":10}, "direction":0}'
		def exception = null
		try {
			new Validator().validateAndReport(badSchema, instance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNotNull("Should have thrown an exception", exception)

		def badInstance = "{{";
		try {
			new Validator().validateAndReport(schema, badInstance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNotNull("Should have thrown an exception", exception)
	}

	void testFromText() throws Exception {
		def schemaText=  '''
{
  "description": "This is for testing schemas as text instead of as paths to a file.",
  "type": "object",
  "required": [
    "properties"
  ],
  "properties": {
    "externalId": {
      "description": "Option unique id for tracking within integrator systems.",
      "type": "string"
    }
  }
}
'''
		def file = new File("build/test.json")
		file.text = schemaText
		def instance = '{"properties":{"externalId":"abc123"}}'
		def report = new Validator().validateAndReportFromText(schemaText, instance)
		report.each{println it}
		assertTrue "the instance should be valid against a schema", report.isSuccess()

		def exception = null
		try {
			new Validator().validateAndReport(file, instance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNull("Should not have thrown a servlet exception", exception)
	}

	void testFromFile() {
		def schemaFile = new File("resources/v1/schema/structure/structure.schema")
		def instance = new File("resources/v1/examples/designs/one_of_everything.json").text
		def report = new Validator().validateAndReport(schemaFile, instance)
		assertTrue "the instance should be valid against a schema", report.isSuccess()

		def exception = null
		try {
			new Validator().validateAndReport(schemaFile, instance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNull("Should not have thrown a servlet exception", exception)
	}

	void testFromURL() {
		def schemaURL = this.getClass().getResource("/v1/schema/structure/structure.schema")
		def instance = new File("resources/v1/examples/designs/one_of_everything.json").text
		def report = new Validator().validateAndReport(schemaURL, instance)
		assertTrue "the instance should be valid against a schema", report.isSuccess()

		def exception = null
		try {
			new Validator().validateAndReport(schemaURL, instance)
		} catch (Exception jse) {
			exception = jse
		}
		assertNull("Should not have thrown a servlet exception", exception)
	}
}
