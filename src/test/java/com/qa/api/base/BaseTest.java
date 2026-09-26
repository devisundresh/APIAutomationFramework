package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.qameta.allure.restassured.AllureRestAssured;
//import org.testng.annotations.Listeners;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
//import com.aventstack.chaintest.plugins.ChainTestListener;


//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	//I want my baseURL should be accessed by only child classes inside and outside of packages
	
	protected RestClient restClient;
	
	//**** API Base URLs******//	
	protected static String BASE_URL_GOREST;
	protected static String BASE_URL_CONTACTS;
	protected static String BASE_URL_REQRES;	
	protected static String BASE_URL_BASIC_AUTH;
	protected static String BASE_URL_FAKE_STORE;
	protected static String BASE_URL_OAUTH2_AMADEUS;
	protected static String BASE_URL_ERGAST_CIRCUIT;
	
	//**** API ENDPOINTS******//
	protected final static String GOREST_USERS_ENDPOINT="/public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT="/users/login";
	protected final static String CONTACTS_ENDPOINT="/contacts";
	protected final static String REQRES_ENDPOINT="/api/users";
	protected final static String BASIC_AUTH_ENDPOINT="/basic_auth";
	protected final static String FAKE_STORE_ENDPOINT="/products";
	protected final static String OAUTH2_AMADEUS_ENDPOINT="/v1/security/oauth2/token";
	protected final static String AMADEUS_FLIGHT_DEST_ENDPOINT="/v1/shopping/flight-destinations";
	protected final static String ERGAST_CIRCUIT_ENDPOINT="/api/f1/2017/circuits.xml";
	
	@BeforeSuite
	public void initSetUp() {
		RestAssured.filters(new AllureRestAssured());
		BASE_URL_GOREST = ConfigManager.get("baseurl.gorest").trim();
		BASE_URL_CONTACTS = ConfigManager.get("baseurl.contacts").trim();
		BASE_URL_REQRES = ConfigManager.get("baseurl.reqres").trim();
		BASE_URL_BASIC_AUTH = ConfigManager.get("baseurl.basic_auth").trim();
		BASE_URL_FAKE_STORE = ConfigManager.get("baseurl.fake_store").trim();
		BASE_URL_OAUTH2_AMADEUS = ConfigManager.get("baseurl.amadeus").trim();
		BASE_URL_ERGAST_CIRCUIT = ConfigManager.get("baseurl.ergast").trim();
	}

	@BeforeTest
	public void setUp() {
		
		restClient = new RestClient(); //can be used commonly in all my tests. not required to create object again and again
		
	}
	
}
