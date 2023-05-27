package ru.yandex.praktikum.stellarburgers.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String USER_CREATE = "/api/auth/register";
    static String accessToken = "";

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(UserRegistrationPojo userRegistrationPojo) {
        return given()
                .spec(RestClient.getBaseSpec())
                .when()
                .body(userRegistrationPojo).log().all()
                .post(USER_CREATE).then().log().all();
    }
    @Step("Удаление пользователя")
    public static Response deleteUser() {
        if (accessToken.equals("")) {
            return given()
                    .spec(getBaseSpec())
                    .auth().oauth2(accessToken)
                    .delete("/api/auth/user");
        }
        return null;

    }

}
