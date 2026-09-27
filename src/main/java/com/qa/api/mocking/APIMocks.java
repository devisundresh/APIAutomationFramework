package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;;

public class APIMocks {
	
	//*****************Create Mock/Stub for GET CALL******************//
	public static void defineGetUserMock() {
		
		//http://localhost:8089/api/users
		stubFor(get(urlEqualTo("/api/users")) //stubFor -> create fake Mock..
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "application/json")
				.withBody("{\n"
						+ " \"name\": \"Iphone 18\",\n"
						+ " \"price\": 999,\n"
						+ "  \"qty\": 1\n"
						+ "  }")
				)
		);
		
	}
	
public static void defineGetUserMockWithJsonFile() {
		
		//http://localhost:8089/api/users
		stubFor(get(urlEqualTo("/api/users"))
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "application/json")
				.withHeader("server-name", "bankserver")
				.withBodyFile("mockuser.json") //all folder should be starts with __
				
				)
		);
		
	}


public static void defineGetUserMockWithQueryParam() {
	
	//http://localhost:8089/api/users}name=tom
	stubFor(get(urlPathEqualTo("/api/users"))
			.withQueryParam("name", equalTo("Iphone 18"))
			.willReturn(aResponse()
			.withStatus(200)
			.withHeader("Content-Type", "application/json")
			.withHeader("server-name", "bankserver")
			.withBody("{\n"
					+ " \"name\": \"Iphone 18\",\n"
					+ " \"price\": 999,\n"
					+ "  \"qty\": 1\n"
					+ "  }") //all folder should be starts with __
			
			)
	);
	
}

//*********************Create mock.stub for POST CALL****************************??

public static void defineCreateUserMock() {
	
	//http://localhost:8089/api/users}name=tom
	stubFor(post(urlPathEqualTo("/api/users"))
			.withHeader("Content-Type", equalTo("application/json"))
			.willReturn(aResponse()
			.withStatus(201)
			.withHeader("Content-Type", "application/json")
			.withHeader("server-name", "bankserver")
			.withBody("{\n"
					+ "\"name\": \"apple\"\n"
					+ "}") //Fake APIs never expects exact body.. give anything in request.. Response will be fixed..
			
			)
			);

}

//*********************Create mock.stub for DELETE CALL****************************??

public static void defineDeleteUserMock() {
	
	//http://localhost:8089/api/users}name=tom
	stubFor(delete(urlPathEqualTo("/api/users"))
			.withHeader("Content-Type", equalTo("application/json"))
			.willReturn(aResponse()
			.withStatus(204)
			.withHeader("Content-Type", "application/json")
			.withHeader("server-name", "bankserver")
			));

}

}
