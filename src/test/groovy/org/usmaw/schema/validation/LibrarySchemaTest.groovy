package org.usmaw.schema.validation

import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.load.configuration.LoadingConfiguration
import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.github.fge.jsonschema.uri.*
import org.apache.log4j.Logger;

class LibrarySchemaTest extends GroovyTestCase {


	def log = Logger.getLogger(this.class)
	def report


	void testLibraryPoleObject(){
 		final LoadingConfiguration cfg = LoadingConfiguration.newBuilder().setNamespace(new File("resources").toURI().toString()).freeze();
    	final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
		def schema = factory.getJsonSchema("v1/schema/library/pole.schema")
		report = schema.validate(JsonLoader.fromString(new File("resources/v1/examples/library/library_pole_example.json").text))
		report.each{ log.info "validation report "+it.toString() }
		assertTrue "the instance should be valid against a schema", report.isSuccess()		
	}

	void testLibraryAnchorObject(){
		final LoadingConfiguration cfg = LoadingConfiguration.newBuilder().setNamespace(new File("resources").toURI().toString()).freeze();
		final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
		def schema = factory.getJsonSchema("v1/schema/library/anchor.schema")
		report = schema.validate(JsonLoader.fromString(new File("resources/v1/examples/library/library_anchor_example.json").text))
		report.each{ log.info "validation report "+it.toString() }
		assertTrue "the instance should be valid against a schema", report.isSuccess()
	}

	void testLibraryInsulatorObject(){
		final LoadingConfiguration cfg = LoadingConfiguration.newBuilder().setNamespace(new File("resources").toURI().toString()).freeze();
		final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
		def schema = factory.getJsonSchema("v1/schema/library/insulator.schema")
		report = schema.validate(JsonLoader.fromString(new File("resources/v1/examples/library/library_insulator_example.json").text))
		report.each{ log.info "validation report "+it.toString() }
		assertTrue "the instance should be valid against a schema", report.isSuccess()
	}

	void testLibraryWireObject(){
		final LoadingConfiguration cfg = LoadingConfiguration.newBuilder().setNamespace(new File("resources").toURI().toString()).freeze();
		final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
		def schema = factory.getJsonSchema("v1/schema/library/wire.schema")
		report = schema.validate(JsonLoader.fromString(new File("resources/v1/examples/library/library_wire_example.json").text))
		report.each{ log.info "validation report "+it.toString() }
		assertTrue "the instance should be valid against a schema", report.isSuccess()
	}
}