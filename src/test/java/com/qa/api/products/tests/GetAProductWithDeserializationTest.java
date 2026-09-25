package com.qa.api.products.tests;

import java.io.IOException;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.pojo.Product.Rating;
import com.qa.api.utils.JsonUtil;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAProductWithDeserializationTest extends BaseTest{
	
	
	@Test
	public void createUserTestWithJsonString() throws IOException {
		
		
		Rating rating = Product.Rating.builder()
						.rate(5.0).count(500).build();
		
		Product product = new Product().builder()
				.id(null)
				.title("Kids Wear")
				.price("99.99")
				.description("Kids wear for festival")
				.category("Traditional")
				.image("https://firstcry.com/img/kids.img")
				.rating(rating)
				.build();
		
	
		Response response =restClient.post(BASE_URL_FAKE_STORE, FAKE_STORE_ENDPOINT, product, null, null, AuthType.NO_AUTH, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.jsonPath().getString("title"), "Kids Wear");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
		
		String userId = response.jsonPath().getString("id");
		System.out.println("userId ===> " +userId);
		
		 //GetUser
		Response responseGet = restClient.get(BASE_URL_FAKE_STORE, FAKE_STORE_ENDPOINT+"/"+"2", null, null, AuthType.NO_AUTH, ContentType.JSON);
		responseGet.prettyPrint();
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		
		Product productResponse = JsonUtil.deserialize(responseGet, Product.class);
		Assert.assertEquals(productResponse.getTitle(), productResponse.getTitle());
		

}
}
