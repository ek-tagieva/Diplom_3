package ru.yandex.praktikum.stellarburgers.client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
public class UserClient extends RestClient {
    private static final String USER_CREATE = "/api/auth/register";
    public static final String USER_UPDATE = "/api/auth/user";
    @Step("Создание пользователя")
    public static ValidatableResponse createUser(UserRegistrationPojo userRegistrationPojo){
        return given()
                .spec(RestClient.getBaseSpec())
                .when()
                .body(userRegistrationPojo).log().all()
                .post(USER_CREATE).then().log().all();
    }
    @Step ("Удаление пользователя")
    public static ValidatableResponse deleteUser(String accessToken){
        if ((accessToken != null) && (!accessToken.equals(""))){
            return given()
                    .header("Authorization", accessToken)
                    .spec(RestClient.getBaseSpec())
                    .when().log().all()
                    .delete(USER_UPDATE)
                    .then().assertThat().statusCode(SC_ACCEPTED);
        }
        return null;
    }
}
