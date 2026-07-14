package api;

import utils.ConfigReader;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ContentApi {

    public static List<String> getContentTitlesByAudience(String audience, String email, String password) {
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
                .getList("[0].data.title");
    }

    public static void addContentToReadingList(String audience, int contentId, String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");
        String token = AuthApi.getAccessToken(email, password);

        given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .log().all()
                .contentType("application/json")
                .body("{ \"contentId\": " + contentId + " }")
                .when()
                .post("/api/reading-list")
                .then()
                .statusCode(201);
    }

    public static List<Integer> getReadingListContentIds(String audience, String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");
        String token = AuthApi.getAccessToken(email, password);

        return given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .queryParam("audience", audience)
                .when()
                .get("/api/reading-list")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data.contentId");
    }

    public static int getContentIdByTitle(String audience, String title, String email, String password) {
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
                .getInt("[0].data.find { it.title == '" + title + "' }.id");
    }

    public static void clearReadingList(String audience, String email, String password) {
        String baseUrl = ConfigReader.get("api.base.url");
        String token = AuthApi.getAccessToken(email, password);

        List<Integer> currentIds = getReadingListContentIds(audience, email, password);

        if (currentIds.isEmpty()) {
            System.out.println("Cleanup: Okuma listesi zaten boş, silinecek içerik yok → " + email);
            return;
        }

        for (int contentId : currentIds) {
            int deleteStatus = given()
                    .baseUri(baseUrl)
                    .header("Authorization", "Bearer " + token)
                    .contentType("application/json")
                    .body("{ \"contentId\": " + contentId + " }")
                    .when()
                    .delete("/api/reading-list")
                    .then()
                    .extract()
                    .statusCode();

            if (deleteStatus == 204) {
                System.out.println("Cleanup: Okuma listesinden başarıyla silindi (Content ID: " + contentId + ") → " + email);
            } else {
                System.out.println("Cleanup: Silinemedi. Status: " + deleteStatus + " (Content ID: " + contentId + ") → " + email);
            }
        }
    }
}