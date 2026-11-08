package api;

import static io.restassured.RestAssured.given;

import utils.ConfigReader;

public class ForgotPasswordApi {

    private static String baseUrl() {
        return ConfigReader.get("api.base.url");
    }

    public static String getResetToken(String email) {

        return given().baseUri(baseUrl()).contentType("application/json").body("{ \"email\": \"" + email + "\" }").when()
                .post("/api/auth/forgot-password-token").then().statusCode(200).extract().jsonPath().getString("token");
    }

}