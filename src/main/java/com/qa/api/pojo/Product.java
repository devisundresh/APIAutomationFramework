package com.qa.api.pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Product {

	private Integer id;
	private String title;
	private String price;
	private String description;
	private String category;
	private String image;
	private Rating rating; // Non-primitive

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Rating {
		private Double rate;
		private Integer count;
	}
}
	