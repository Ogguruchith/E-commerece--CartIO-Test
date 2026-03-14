package com.cartiq.api;

import com.cartiq.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiBase {

	public static final String BASE_URL = ConfigReader.getProperty("api.base.url");

	// ✅ Add token!
	public static final String TOKEN = ConfigReader.getProperty("reqres.token");

	public static RequestSpecification getRequest() {

		// ✅ Debug print!
		System.out.println("URL: " + BASE_URL);
		System.out.println("Token: " + TOKEN);

		return RestAssured.given().baseUri(BASE_URL)
				// ✅ Add token header!
				.header("x-api-key", TOKEN).header("Content-Type", "application/json").log().all();
	}
}