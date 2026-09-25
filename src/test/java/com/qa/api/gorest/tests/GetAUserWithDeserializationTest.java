package com.qa.api.gorest.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAUserWithDeserializationTest extends BaseTest{
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken() {
		tokenId = "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6";
		ConfigManager.set("bearertoken", tokenId);
	}
	
	@Test
	public void createUserTestWithJsonString() throws IOException {
		
		User user = new User(null, "Tom Jerry", StringUtils.getRandomMailId(),"male","active");		
		String userString = "{\n"
				+ "    \"name\": \"Priyadevi\",\n"
				+ "    \"email\": \"Priyadevi001@opencart.com\",\n"
				+ "    \"gender\": \"female\",\n"
				+ "    \"status\": \"active\"\n"
				+ "}";
		
	
		Response response =restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Tom Jerry");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
		
		String userId = response.jsonPath().getString("id");
		
		 //GetUser
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		
		User userResponse = JsonUtil.deserialize(responseGet, User.class);
		Assert.assertEquals(userResponse.getName(), user.getName());
		

}
}
