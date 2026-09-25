package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.constants.AuthType;
import com.qa.api.errors.APIException;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.expect;

import static org.hamcrest.Matchers.*;

public class RestClient {
	
	//More generic way
	//Any kind of Get/post/put/delete calls(testng wrapper) can be used with these methods
	//No utility here
	//supply data from testng from excel/csv
	
	
	//define Response specs:
	
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);
	
	public RequestSpecification setUpRequest(String baseUrl,AuthType authType, ContentType contentType) {
		ChainTestListener.log("API base url : " +baseUrl);
		ChainTestListener.log("Auth Type : " +authType.toString());
		
		RequestSpecification request = given().log().all()
										.baseUri(baseUrl)
										.contentType(contentType)
										.accept(contentType);
		
		
		switch(authType) {
		case BEARER_TOKEN:
			request.header("Authorization","Bearer "+ConfigManager.get("bearertoken"));
			break;
		
		case OAUTH2:
			request.header("Authorization","Bearer "+"oauth2 token");
			break;
			
		case BASIC_AUTH:
			request.header("Authorization","Basic "+generateBasicAuthToken());
			break;
			
		case API_KEY:
			request.header("x-api-key","api key");
			break;
			
		case NO_AUTH:
			System.out.println("Auth is not required");
			break;
			
		default:
			System.out.println("Invalid Auth.. Please supply the right AuthType ");
			throw new APIException("======InvalidAuth========");
     	}
		
		return request ;
		
	}
	
	private String generateBasicAuthToken() {
		String credentials =ConfigManager.get("basicauthusername") + ":" + ConfigManager.get("basicauthpassword");
		//admin:admin -> "5trtrtreert" ->(base64 encoded value)
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
	
	private void applyParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String>  pathParams ) {
		
		if(queryParams !=null) {
			request.queryParams(queryParams);
		}
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
		
	}
	
	//CRUD:
	
	//get:
	
	/***
	 * This method used to call GET APIs
	 * @param baseUrl
	 * @param endPoint
	 * @param queryParam
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return get call response
	 */
	//@Step("Calling get api with Base url: {0}") -> Not recommened since report already has this details
	public Response get(String baseUrl, String endPoint, Map<String,String> queryParam, Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParams);
		Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
	}
	
	//post:
	/***
	 * This method used to call POST APIs
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param queryParam
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return POST call response
	 */
	public <T>Response post(String baseUrl, String endPoint, T body, Map<String, String> queryParam, Map<String, String>  pathParams,AuthType authType, ContentType contentType) {
		RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParams);
		Response response = request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
      }
	
	/***
	 * This method used to call POST APIs with file 
	 * @param baseUrl
	 * @param endPoint
	 * @param file
	 * @param queryParam
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return Post Call Response
	 */
	public Response post(String baseUrl, String endPoint, File file, Map<String, String> queryParam, Map<String, String>  pathParams,AuthType authType, ContentType contentType) {
		RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParams);
		Response response = request.body(file).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
      }
	
	public Response post(String baseUrl, String endPoint, String ClientId, String clientSecret, String grantType, ContentType contentType) {
		Response response = RestAssured.given()				
				.contentType(contentType)
				.formParam("grant_type", grantType)
				.formParam("client_id", ClientId)
				.formParam("client_secret", clientSecret)
				.when()
				.post(baseUrl+endPoint);
		response.prettyPrint();
		return response;
      }
	
	/***
	 * This method used to call PUT APIs
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param queryParam
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return PUT call response
	 */
	
	public <T>Response put(String baseUrl, String endPoint, T body, Map<String, String> queryParam, Map<String, String>  pathParams,AuthType authType, ContentType contentType) {
		RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParams);
		Response response = request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
      }
	
	/***
	 * This method used to call PATCH APIs
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param querParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return patch call Response
	 */
	public <T>Response patch(String baseUrl,String endPoint, T body, Map<String,String> querParams,Map<String,String> pathParams,AuthType authType,ContentType contentType ){
		 RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		 applyParams(request, querParams, pathParams);
		 Response response =request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		 response.prettyPrint();
		 return response;
		 
	}
	/***
	 * This method used to call DELETE APIs
	 * @param baseUrl
	 * @param endPoint
	 * @param querParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return DELETE call response
	 */
	public Response delete(String baseUrl,String endPoint, Map<String,String> querParams,Map<String,String> pathParams,AuthType authType,ContentType contentType ){
		RequestSpecification request = setUpRequest(baseUrl,authType, contentType);
		applyParams(request, querParams, pathParams);
		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}
	
}
