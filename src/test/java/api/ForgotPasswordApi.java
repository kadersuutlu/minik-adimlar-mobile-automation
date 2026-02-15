package api;

import static io.restassured.RestAssured.given;

public class ForgotPasswordApi {

	private static final String BASE_URL = System.getProperty("baseUrl");

	public static String getResetToken(String email) {

		return given().baseUri(BASE_URL).contentType("application/json").body("{ \"email\": \"" + email + "\" }").when()
				.post("/api/auth/forgot-password-token").then().statusCode(200).extract().jsonPath().getString("token");
	}

}