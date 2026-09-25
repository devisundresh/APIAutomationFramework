package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest{
	
	@Test
	public void updateUserTest() {
		
		//Create User
		User user = User.builder().name("chottaabeam").email(StringUtils.getRandomMailId()).status("active").gender("Male").build();
	   //User user = new User("Tom Jerry", StringUtils.getRandomMailId(),"male","active");
		Response responsePost =restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("name"), "chottaabeam");
		Assert.assertNotNull(responsePost.jsonPath().getString("id"));
		String userId = responsePost.jsonPath().getString("id");
		System.out.println("userId ==>" +userId);
        //GetUser
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);
		
		
		//UpdateUser
		user.setName("chotta beam");
		user.setStatus("inactive");
		Response responsePut = restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responsePut.statusLine().contains("OK"));
		Assert.assertEquals(responsePut.jsonPath().getString("id"),userId);
		Assert.assertEquals(responsePut.jsonPath().getString("name"), "chotta beam");
		Assert.assertEquals(responsePut.jsonPath().getString("status"), "inactive");
		
        //GetUser
		responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), "chotta beam");
		Assert.assertEquals(responseGet.jsonPath().getString("status"), "inactive");
	}
		

}
