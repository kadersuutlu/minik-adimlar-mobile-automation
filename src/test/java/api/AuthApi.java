package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import utils.ConfigReader;

public class AuthApi {

    public static String getAccessToken(String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");

        String body = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        return given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("accessToken");
    }
}