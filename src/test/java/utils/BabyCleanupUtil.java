package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BabyCleanupUtil {
    public static void deleteTestBaby(String email, String password, String babyName) {
        String baseUrl = ConfigReader.get("api.base.url");

        // 1. Login yap, token al
        String loginBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        Response loginResponse = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("/api/auth/login")
                .then()
                .extract()
                .response();

        if (loginResponse.statusCode() != 200) {
            System.out.println("Cleanup: Login başarısız, bebek silinemedi. Status: " + loginResponse.statusCode());
            return;
        }

        String token = loginResponse.jsonPath().getString("accessToken");
        int userId = extractUserIdFromToken(token);

        // 2. Kullanıcının bebeklerini listele
        Response babiesResponse = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/baby/user/" + userId)
                .then()
                .extract()
                .response();

        if (babiesResponse.statusCode() != 200) {
            System.out.println("Cleanup: Bebek listesi alınamadı. Status: " + babiesResponse.statusCode());
            return;
        }

        // 3. İsme göre bebeği bul
        Integer babyId = babiesResponse.jsonPath().getInt("find { it.name == '" + babyName + "' }.id");

        if (babyId == null) {
            System.out.println("Cleanup: '" + babyName + "' isimli bebek bulunamadı, silinecek bir şey yok.");
            return;
        }

        // 4. Bebeği sil
        int deleteStatus = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/api/baby/" + babyId)
                .then()
                .extract()
                .statusCode();

        if (deleteStatus == 204) {
            System.out.println("Cleanup: Bebek başarıyla silindi → " + babyName + " (id: " + babyId + ")");
        } else {
            System.out.println("Cleanup: Bebek silinemedi. Status: " + deleteStatus);
        }
    }

    private static int extractUserIdFromToken(String jwt) {
        String[] parts = jwt.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        // payload örn: {"roles":["ROLE_USER"],"tokenType":"access","userId":47,"sub":"...",...}
        return Integer.parseInt(payload.replaceAll(".*\"userId\":(\\d+).*", "$1"));
    }
}
