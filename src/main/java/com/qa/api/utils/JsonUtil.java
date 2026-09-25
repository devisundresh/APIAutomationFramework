package com.qa.api.utils;

import org.checkerframework.checker.units.qual.t;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtil {
		
	private static ObjectMapper mapper = new ObjectMapper();

	public static <T>T deserialize(Response response, Class<T> targetClass) {
		
		try {
		return mapper.readValue(response.getBody().asString(), targetClass);
		}
		catch(Exception e) {
			throw new RuntimeException("deserialize is failed...."+targetClass.getName());
		}
	}
}
