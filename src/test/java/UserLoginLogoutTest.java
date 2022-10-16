import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserLoginLogoutTest {
    private String email = RandomStringUtils.randomAlphabetic(2, 12) + "@gmail.com";
    private String password = RandomStringUtils.randomAlphabetic(8);
    private String name = RandomStringUtils.randomAlphabetic(8);
    private String authToken;
    MainPage mainPage;
    private static ApiClient apiClient;

    @Before
    public void openMainPage() {
        // для запуска в Яндекс Браузере
/*    ChromeOptions options = new ChromeOptions();
    options.setBinary("C:\\WebDriver\\yandexdriver");
    WebDriverRunner.setWebDriver(new ChromeDriver(options));*/

        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        apiClient = new ApiClient();
        Response response = apiClient.createNewUser(email, password, name);
        authToken = response.path("accessToken");
    }

    @After
    public void cleanUp() {
        Response response = apiClient.loginUser(email, password);
        if (response.statusCode() == SC_OK) {
            apiClient.deleteUser(authToken.substring(7));
            System.out.println("Созданный пользователь удален");
        } else System.out.println("Пользователь не был создан");
    }

    @Test
    @DisplayName("Вход пользователя по кнопке Войти в аккаунт на главной")
    public void shouldBePossibleToLoginFromMainPage() {
        mainPage.logInButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        assertThat("Ошибка авторизации пользователя: вход не выполнен, кнопка оформления заказа недоступна", mainPage.isVisibleMakeOrderButton());
    }

    @Test
    @DisplayName("Вход пользователя по кнопке Личный кабинет в хедере")
    public void shouldBePossibleToLoginFromHeader() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        assertThat("Ошибка авторизации пользователя: вход не выполнен, кнопка оформления заказа недоступна", mainPage.isVisibleMakeOrderButton());
    }

    @Test
    @DisplayName("Вход пользователя через форму регистрации")
    public void shouldBePossibleToLoginFromRegistrationForm() {
        mainPage.logInButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.registrationButtonClick();
        UserRegistrationPage registrationPage = page(UserRegistrationPage.class);
        webdriver().shouldHave(url(registrationPage.REGISTRATION_PAGE_URL));
        registrationPage.logInButtonClick();
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        assertThat("Ошибка авторизации пользователя: вход не выполнен, кнопка оформления заказа недоступна", mainPage.isVisibleMakeOrderButton());
    }

    @Test
    @DisplayName("Вход пользователя через форму восстановления пароля")
    public void shouldBePossibleToLoginFromForgotPasswordForm() {
        mainPage.logInButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.restorePasswordButtonClick();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        webdriver().shouldHave(url(forgotPasswordPage.FORGOT_PASSWORD_PAGE_URL));
        forgotPasswordPage.logInButtonClick();
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        assertThat("Ошибка авторизации пользователя: вход не выполнен, кнопка оформления заказа недоступна", mainPage.isVisibleMakeOrderButton());
    }

    @Test
    @DisplayName("Выход пользователя из аккаунта")
    public void shouldBePossibleToLogOut() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
        accountPage.logOutButtonClick();
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
    }
}
