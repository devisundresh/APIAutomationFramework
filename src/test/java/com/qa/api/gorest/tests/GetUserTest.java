package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Epic("Epic 100: Go Rest Get User API Feature")
@Story("US 100: feature go rest api - get user api")
public class GetUserTest extends BaseTest{
	
	@BeforeClass
	public void setToken() {
		ConfigManager.set("bearertoken", "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6");
	}
	
	@Description("getting all the users...")
	@Owner("Naveen Automation Labs")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUserTest() {
		ChainTestListener.log("Get all users API Test");
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		
	}
	
	@Description("getting all the users...")
	@Owner("Naveen Automation Labs")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUserWithQueryParamTest() {
		
		Map<String,String> queryParam= new HashMap<String, String>();
		
		queryParam.put("name", "Devi");
		queryParam.put("age","35");
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, queryParam, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		
	}
	
	@Description("getting all the users...")
	@Owner("Naveen Automation Labs")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = true)
	public void getSingleUserWithPathParamTest() {
		
		String userId = "8636678";	
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertEquals(response.jsonPath().getString("id"), userId);

		
	}
	

}
