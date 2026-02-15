package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class AuthApi {

	private static final String BASE_URL = System.getProperty("baseUrl");

	String email = "test" + System.currentTimeMillis() + "@gmail.com";
	
	public static String getAccessToken() {

		return given().baseUri(BASE_URL).contentType("application/json").body("""
				  {
				    "email": "validuser@gmail.com",
				    "password": "Valid123"
				  }
				""").when().post("/api/auth/login").then().statusCode(200).extract().jsonPath()
				.getString("accessToken");
	}

}