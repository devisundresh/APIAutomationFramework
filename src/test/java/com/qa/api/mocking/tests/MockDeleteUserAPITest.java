package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockDeleteUserAPITest extends BaseTest{
	
	@Test
	public void deleteUserMockTest() {
		
		APIMocks.defineDeleteUserMock();
		Response response  =restClient.delete(BASE_URL_WIREMOCKSERVER, WIREMOCKSERVER_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		response.then().assertThat().statusCode(204);
	}

}
