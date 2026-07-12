package utils;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AccountCleanupUtil {
    public static void deleteTestAccount(String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");

        // 1. Login yap, token al
        String body = String.format(
                "{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        Response loginResponse = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/auth/login")
                .then()
                .extract()
                .response();

        if (loginResponse.statusCode() != 200) {
            String warning = "Cleanup: Login başarısız, hesap silinemedi. Status: " + loginResponse.statusCode() + " Email: " + email;
            System.out.println(warning);
            Allure.addAttachment("Cleanup Warning", warning);
            return;
        }

        String token = loginResponse.jsonPath().getString("accessToken");

        // 2. Token ile hesabı sil
        int deleteStatus = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/auth/account")
                .then()
                .extract()
                .statusCode();

        if (deleteStatus == 204) {
            System.out.println("Cleanup: Test hesabı başarıyla silindi → " + email);
        } else {
            System.out.println("Cleanup: Hesap silinemedi. Status: " + deleteStatus);
        }
    }
}