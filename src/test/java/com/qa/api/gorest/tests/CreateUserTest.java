package com.qa.api.gorest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import com.qa.api.utils.TokenUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	
	//@Before
	
	//@After
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken() {
		tokenId = "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6";
		ConfigManager.set("bearertoken", tokenId);
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Jerry", "male", "active"},
			{"Beam", "male", "inactive"},
			{"Cindrella", "female", "active"}
		};
	}

	
	@Test(dataProvider = "getUserData")
	public void createUserTestWithJsonString(String name, String gender,String status) throws IOException {
		
		TokenUtil token = new TokenUtil();
		token.setUpToken();
		
		User user = new User(null, name, StringUtils.getRandomMailId(),gender,status);		
//		String userString = "{\n"
//				+ "    \"name\": \"Priyadevi\",\n"
//				+ "    \"email\": \"Priyadevi00081@opencart.com\",\n"
//				+ "    \"gender\": \"female\",\n"
//				+ "    \"status\": \"active\"\n"
//				+ "}";
		
	
		System.out.println("AuthType.BEARER_TOKEN: "+AuthType.BEARER_TOKEN);
		Response response =restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertEquals(response.jsonPath().getString("status"), status);
		Assert.assertNotNull(response.jsonPath().getString("id"));	
		ChainTestListener.log("user id" +response.jsonPath().getString("id"));
	}
	
	@Test
	public void createUserTestWithJsonFileObject() throws IOException {
		
		TokenUtil token = new TokenUtil();
		token.setUpToken();
		
	//	File userFile = new File("./src/test/resources/jsons/user.json"); //for static json
		String emailId =  StringUtils.getRandomMailId();
		
		//convert json file content to string class object
		String rawJson=new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailId);
		
		Response response =restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, updatedJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Tom Jerry");
		Assert.assertNotNull(response.jsonPath().getString("id"));		
	}

}
