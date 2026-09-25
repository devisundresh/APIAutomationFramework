package com.qa.api.products.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest{
	
	@Test
	public void getProductsTest(){
		
		Response response = restClient.get(BASE_URL_FAKE_STORE, FAKE_STORE_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);
		Product[] product = JsonUtil.deserialize(response, Product[].class);
		
		for(Product p: product) {
			System.out.println(p.getId());
			System.out.println(p.getTitle());
			System.out.println(p.getPrice());
			System.out.println(p.getImage());
			System.out.println(p.getCategory());
			
			System.out.println(p.getRating().getRate());
			System.out.println(p.getRating().getCount());
			
		}
		
		
	}

}
