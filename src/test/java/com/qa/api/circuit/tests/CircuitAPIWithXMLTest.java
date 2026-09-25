package com.qa.api.circuit.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.XMLPathUtil;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CircuitAPIWithXMLTest extends BaseTest{
	
	@Test
	public void GetCircuitInfoTest() {
		Response response =restClient.get(BASE_URL_BASIC_AUTH, AMADEUS_FLIGHT_DEST_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.XML);
		List<String> circuitNames = XMLPathUtil.readList(response, "MRData.Circuit.CircuitName");
		for(String e : circuitNames) {
			Assert.assertNotNull(e);
		}
		
		String americaLoc = XMLPathUtil.read(response, "**.find{it.@circuitId == 'americas' }.Location.Locality");
		System.out.println("Americas Location --->" + americaLoc);
		Assert.assertEquals(americaLoc, "Austin");
	}
		

}
