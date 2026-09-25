package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserApiSchemaTest extends BaseTest{
	
	@Test
	public void getUserAPISchemaTest() {
		
				ConfigManager.set("bearertoken", "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6");
				
				Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
				
				Assert.assertTrue(SchemaValidator.validateSchema(response, "./schema/getUsersSchema.json"));

		}
	
	@Test
	public void cretaeUserAPISchemaTest() {
		
				ConfigManager.set("bearertoken", "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6");
				
				User user = User.builder()
				.name("Beam Boy")
				.status("active")
				.email(StringUtils.getRandomMailId())
				.gender("female")
				.build();
				
				Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
					
				Assert.assertTrue(SchemaValidator.validateSchema(response, "./schema/createuserschema.json"));

		}
}

