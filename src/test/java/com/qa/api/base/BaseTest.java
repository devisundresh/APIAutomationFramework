package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.qameta.allure.restassured.AllureRestAssured;
//import org.testng.annotations.Listeners;

import com.qa.api.client.RestClient;

import io.restassured.RestAssured;
//import com.aventstack.chaintest.plugins.ChainTestListener;


//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	//I want my baseURL should be accessed by only child classes inside and outside of packages
	
	protected RestClient restClient;
	
	//**** API Base URLs******//	
	protected final static String BASE_URL_GOREST="https://gorest.co.in";
	protected final static String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_REQRES="https://reqres.in";	
	protected final static String BASE_URL_BASIC_AUTH="https://the-internet.herokuapp.com";
	protected final static String BASE_URL_FAKE_STORE="https://fakestoreapi.com/";
	protected final static String BASE_URL_OAUTH2_AMADEUS="https://test.api.amadeus.com";
	protected final static String BASE_URL_ERGAST_CIRCUIT="http://ergast.com";
	
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
	public void setupAllureReport() {
		RestAssured.filters(new AllureRestAssured());
	}

	@BeforeTest
	public void setUp() {
		
		restClient = new RestClient(); //can be used commonly in all my tests. not required to create object again and again
		
	}
	
}
