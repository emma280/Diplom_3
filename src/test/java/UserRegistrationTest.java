import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.Header;
import pageobjects.MainPage;
import pageobjects.UserLoginPage;
import pageobjects.UserRegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRegistrationTest {
    private String email = RandomStringUtils.randomAlphabetic(2, 12) + "@gmail.com";
    private String password = RandomStringUtils.randomAlphabetic(8);
    private String wrongPassword = RandomStringUtils.randomAlphabetic(4);
    private String name = RandomStringUtils.randomAlphabetic(8);
    private String authToken;
    private static ApiClient apiClient;

    @Before
    public void openMainPage() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void cleanUp() {
        apiClient = new ApiClient();
        Response response = apiClient.loginUser(email, password);
        if (response.statusCode() == SC_OK) {
            authToken = response.path("accessToken");
            apiClient.deleteUser(authToken.substring(7));
            System.out.println("Созданный пользователь удален");
        } else System.out.println("Пользователь не был создан");
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    public void shouldBePossibleToRegisterWithValidData() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        loginPage.registrationButtonClick();
        UserRegistrationPage registrationPage = page(UserRegistrationPage.class);
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.registerButtonClick();
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидным паролем")
    public void shouldBeImpossibleToRegisterWithWrongPassword() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        loginPage.registrationButtonClick();
        UserRegistrationPage registrationPage = page(UserRegistrationPage.class);
        registrationPage.fillRegistrationForm(name, email, wrongPassword);
        registrationPage.registerButtonClick();
        assertThat("Ошибка регистрации пользователя: при вводе пароля короче 6 символов нет сообщения об ошибке длины пароля", registrationPage.isVisibleShortPasswordErrorMessage());
    }
}