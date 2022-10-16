import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class ApiClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String USER_CREATE_ENDPOINT = BASE_URL + "api/auth/register";
    private static final String USER_DATA_ENDPOINT = BASE_URL + "api/auth/user";
    private static final String USER_LOGIN_ENDPOINT = BASE_URL + "api/auth/login";
    public static RequestSpecification getBaseSpecification() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Создание нового пользователя")
    public Response createNewUser(String email, String password, String name) {
        return given()
                .spec(getBaseSpecification())
                .body("{\"email\": \"" + email + "\"," +
                        "\"password\": \"" + password + "\"," +
                        "\"name\": \"" + name + "\"}")
                .post(USER_CREATE_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String token) {
        return given()
                .spec(getBaseSpecification())
                .auth().oauth2(token)
                .delete(USER_DATA_ENDPOINT);
    }

    @Step("Вход пользователя")
    public Response loginUser(String email, String password) {
        return given()
                .spec(getBaseSpecification())
                .body("{\"email\": \"" + email + "\"," +
                        "\"password\": \"" + password + "\"}")
                .post(USER_LOGIN_ENDPOINT);
    }
}