package com.qa.api.mocking.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetUserAPITest extends BaseTest {
	
	@Test
	public void getDummyUserMockAPITest() {

		APIMocks.defineGetUserMock();
		Response response = restClient.get(BASE_URL_WIREMOCKSERVER, WIREMOCKSERVER_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test
	public void getDummyUserMockAPITestWithJsonFile() {

		APIMocks.defineGetUserMockWithJsonFile();
		Response response = restClient.get(BASE_URL_WIREMOCKSERVER, WIREMOCKSERVER_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test
	public void getDummyUserMockAPITestWithQueryParamTest() {

		APIMocks.defineGetUserMockWithQueryParam();

		Map<String, String> userQueryMap = new HashMap<String, String>();
		userQueryMap.put("name", "Iphone 18");

		Response response = restClient.get(BASE_URL_WIREMOCKSERVER, WIREMOCKSERVER_ENDPOINT, userQueryMap, null, AuthType.NO_AUTH, ContentType.ANY);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

}
