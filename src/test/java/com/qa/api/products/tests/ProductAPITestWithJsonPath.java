package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPath extends BaseTest{
	
	@Test
	public void getProductTest() {
		Response response = restClient.get(BASE_URL_FAKE_STORE, FAKE_STORE_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertTrue(response.statusLine().contains("OK"));
		response.prettyPrint();
		
		List<Number> ids = JsonPathValidatorUtil.readList(response, "$.[*].id");
		System.out.println(ids);	
		
		System.out.println("=======================================");
		
		List<Number> price = JsonPathValidatorUtil.readList(response, "$[?(@.price > 50)].price");
		System.out.println(price);	
		
		System.out.println("=======================================");
		
		List<Number> rates = JsonPathValidatorUtil.readList(response, "$[?(@.price > 50)].rating.rate");
		System.out.println(rates);	
		
		System.out.println("=======================================");

		List<Map<String, Number>> idTitleList = JsonPathValidatorUtil.readListOfMaps(response, "$.[*].['id','title']");
		System.out.println(idTitleList);
		
		System.out.println("=======================================");
		
		List<Map<String, Number>> idTitleWomenClothesList = JsonPathValidatorUtil.readListOfMaps(response, "$.[?(@.category == \"women\'s clothing\")].['id','title','category']");
		System.out.println(idTitleWomenClothesList);
		
		System.out.println("=======================================");
		
		List<Map<String, Number>> idTitleCatList = JsonPathValidatorUtil.readListOfMaps(response, "$.[*].['id','title','category']");
		System.out.println(idTitleCatList);
		
		for(Map<String, Number> e: idTitleList) {
			System.out.println("Id: "+e.get("id"));
			System.out.println("Title: "+e.get("title"));

		}
		
		System.out.println("=======================================");
		
		Double minPrice = JsonPathValidatorUtil.read(response, "min($[*].price)");
		System.out.println(minPrice);

	}
}
