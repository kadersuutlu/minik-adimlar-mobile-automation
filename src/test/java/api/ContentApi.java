package api;

import static io.restassured.RestAssured.given;

public class ContentApi {

	private static final String BASE_URL = System.getProperty("baseUrl");

	public static String getFirstContentTitleByAudience(String audience) {

		String token = AuthApi.getAccessToken();
		
		return given().baseUri(BASE_URL).header("Authorization", "Bearer " + token).queryParam("audience", audience)
				.when().get("/api/content/by-audience").then().statusCode(200).extract().jsonPath()
				.getString("[0].data[0].title");
	}
}