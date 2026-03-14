package com.cartiq.ApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cartiq.api.ApiBase;

import io.restassured.response.Response;

public class UserApiTest {

	private static int userId;

	// Test 1 — GET all users
	@Test(priority = 1)
	public void getAllUsers() {
		Response response = ApiBase.getRequest().when().get("/users");

		System.out.println("GET All Users: " + response.getBody().asString());

		Assert.assertEquals(response.getStatusCode(), 200, "Status should be 200!");
		System.out.println("GET All Users Passed! ✅");
	}

	// Test 2 — GET single user
	@Test(priority = 2)
	public void getUserById() {
		Response response = ApiBase.getRequest().when().get("/users/2");

		System.out.println("GET User: " + response.getBody().asString());

		Assert.assertEquals(response.getStatusCode(), 200, "Status should be 200!");

		String name = response.jsonPath().getString("data.first_name");

		System.out.println("User Name: " + name);
		System.out.println("GET User Passed! ✅");
	}

	// Test 3 — POST create user
	@Test(priority = 3)
	public void createUser() {
		String body = "{" + "\"name\": \"Ruchith Kumar\"," + "\"job\": \"QA Engineer\"" + "}";

		Response response = ApiBase.getRequest().body(body).when().post("/users");

		System.out.println("POST Create User: " + response.getBody().asString());

		Assert.assertEquals(response.getStatusCode(), 201, "Status should be 201!");

		userId = response.jsonPath().getInt("id");

		System.out.println("User Created! ID: " + userId);
		System.out.println("POST User Passed! ✅");
	}

	// Test 4 — PUT update user
	@Test(priority = 4)
	public void updateUser() {
		String body = "{" + "\"name\": \"Ruchith Updated\"," + "\"job\": \"Senior QA\"" + "}";

		Response response = ApiBase.getRequest().body(body).when().put("/users/2");

		System.out.println("PUT Update User: " + response.getBody().asString());

		Assert.assertEquals(response.getStatusCode(), 200, "Status should be 200!");

		String updatedName = response.jsonPath().getString("name");

		Assert.assertEquals(updatedName, "Ruchith Updated", "Name should be updated!");
		System.out.println("PUT Update Passed! ✅");
	}

	// Test 5 — DELETE user
	@Test(priority = 5)
	public void deleteUser() {
		Response response = ApiBase.getRequest().when().delete("/users/2");

		System.out.println("DELETE Status: " + response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), 204, "Status should be 204!");
		System.out.println("DELETE User Passed! ✅");
	}
}
