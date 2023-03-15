package Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;

public class BaseBuilder {
	PropertiesReader pro = new PropertiesReader();
	RequestSpecBuilder builder;
	RequestSpecification reqspec;
	
	public RequestSpecification placeSpecBuilder() throws FileNotFoundException {
		builder = new RequestSpecBuilder();

		builder.setBaseUri(pro.getPropValue("swaggerBaseURI"));
		builder.setContentType("application/json");

		reqspec =  builder.build();	
		return reqspec;
	}
}
