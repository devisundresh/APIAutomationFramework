package com.qa.api.gorest.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken() {
		tokenId = "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6";
		ConfigManager.set("bearertoken", tokenId);
	}
	
	@Test
	public void deleteUserTest() {
		
		User user =User.builder()
				.name("Martin John")
				.email(StringUtils.getRandomMailId())
				.status("active")
				.gender("Male").build();
		
		//CreateUser
		Response responsePost = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("name"),"Martin John");
		Assert.assertNotNull(responsePost.jsonPath().getString("id"));
		
		//fetch the userId
		String userId = responsePost.jsonPath().getString("id");
		System.out.println("userId ===>"+userId);
		ChainTestListener.log("user id" +userId);
		
		//GetUser
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertNotNull(responsePost.jsonPath().getString("id"));
		
		//DeleteUser
		Response responseDelete = restClient.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseDelete.statusLine().contains("No Content"));
		
		//GetUser After Delete
		responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.jsonPath().getString("message").contains("Resource not found"));
		Assert.assertTrue(responseGet.statusLine().contains("Not Found"));
		Assert.assertEquals(responseGet.statusCode(), 404);
		//Assert.assertNotNull(responsePost.jsonPath().getString("id"));
		

		
	}

}
