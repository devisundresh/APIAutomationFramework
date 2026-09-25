package com.qa.api.utils;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathUtil {
	
	
	private static XmlPath getXMLPAth(Response response) {
		String responseBody = response.getBody().asString();
		return new XmlPath(responseBody);
	}

	public static <T> T read(Response response, String xmlPathExpression) { // 
		return getXMLPAth(response).get(xmlPathExpression);
	}

	public static <T> T readList(Response response, String xmlPathExpression) { //
		return getXMLPAth(response).get(xmlPathExpression);
	}

	public static <T> T readListOfMap(Response response, String xmlPathExpression) { //
		return getXMLPAth(response).get(xmlPathExpression);
	}

}
