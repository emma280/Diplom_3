import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.Header;
import pageobjects.MainPage;
import pageobjects.UserAccountPage;
import pageobjects.UserLoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserNavigationTest {
    private String email = RandomStringUtils.randomAlphabetic(2, 12) + "@gmail.com";
    private String password = RandomStringUtils.randomAlphabetic(8);
    private String name = RandomStringUtils.randomAlphabetic(8);
    private String authToken;
    MainPage mainPage;
    private static ApiClient apiClient;

    @Before
    public void openMainPage() {
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
    @DisplayName("Переход в личный кабинет с главной страницы по кнопке в хедере")
    public void shouldBePossibleToOpenAccountPageFromHeader() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор бургеров")
    public void shouldBePossibleToOpenConstructorFromAccountPage() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
        header.constructorButtonClick();
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Переход в конструкторе к разделу Булки")
    public void shouldBePossibleToSwitchToBunsSection() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
        header.constructorButtonClick();
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        mainPage.saucesSectionClick();
        mainPage.bunsSectionClick();
        assertThat("Ошибка при переключении секции конструктора: при выборе категории Булки не отображаются продукты из выбранной категории", mainPage.isVisibleBunsSection());
    }

    @Test
    @DisplayName("Переход в конструкторе к разделу Соусы")
    public void shouldBePossibleToSwitchToSaucesSection() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
        header.constructorButtonClick();
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        mainPage.saucesSectionClick();
        assertThat("Ошибка при переключении секции конструктора: при выборе категории Соусы не отображаются продукты из выбранной категории", mainPage.isVisibleSaucesSection());
    }
    @Test
    @DisplayName("Переход в конструкторе к разделу Начинки")
    public void shouldBePossibleToSwitchToFillingsSection() {
        Header header = page(Header.class);
        header.userAccountButtonClick();
        UserLoginPage loginPage = page(UserLoginPage.class);
        webdriver().shouldHave(url(loginPage.LOGIN_PAGE_URL));
        loginPage.fillFormAndLogIn(email, password);
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        header.userAccountButtonClick();
        UserAccountPage accountPage = page(UserAccountPage.class);
        webdriver().shouldHave(url(accountPage.USER_ACCOUNT_PAGE_URL));
        header.constructorButtonClick();
        webdriver().shouldHave(url(mainPage.MAIN_PAGE_URL));
        mainPage.fillingsSectionClick();
        assertThat("Ошибка при переключении секции конструктора: при выборе категории Булки не отображаются продукты из выбранной категории", mainPage.isVisibleFillingsSection());
    }
}
