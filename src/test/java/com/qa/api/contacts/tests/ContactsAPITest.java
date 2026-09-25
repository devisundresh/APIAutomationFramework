package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest{
	
	private String tokenId;
	
	@BeforeMethod
	public void getToken() {
		ContactsCredentials creds = ContactsCredentials
										.builder()
										.email("devi.sundresh@gmail.com")
										.password("Devi@6616")
										.build();
		
		Response response = restClient.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINT, creds, null, null, AuthType.NO_AUTH, ContentType.JSON);
		tokenId = response.jsonPath().getString("token");
		System.out.println(tokenId);
		ConfigManager.set("bearerToken", tokenId);
		
	}
	
	@Test
	public void GetAllContactsTest() {
		Response response = restClient.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.getStatusLine().contains("OK"));
//		Assert.assertEquals(response.jsonPath().getString("firstName"), "Devi");
//		Assert.assertEquals(response.jsonPath().getString("birthDate"), "1989/07/18");
		Assert.assertNotNull(response.statusLine().contains("_id"));
		String id = response.jsonPath().getString("_id");
		System.out.println("contacts id ==> "+id);
		 
	}

}
