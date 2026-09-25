package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	private static ReadContext getReadContext(Response response) {
		String jsonResponse = response.getBody().asString();
		return JsonPath.parse(jsonResponse);

	}

	public static <T> T read(Response response, String jsonPath) { // $.id or $.name
		return getReadContext(response).read(jsonPath);
	}

	public static <T> List<T> readList(Response response, String jsonPath) {
		return getReadContext(response).read(jsonPath);
	}

	public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
		return getReadContext(response).read(jsonPath);
	}
}
