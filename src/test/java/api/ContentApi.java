package api;

import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class ContentApi {

    public static String getFirstContentTitleByAudience(String audience, String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");
        String token = AuthApi.getAccessToken(email, password);

        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .queryParam("audience", audience)
                .when()
                .get("/api/content/by-audience")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("[0].data[0].title");
    }
}