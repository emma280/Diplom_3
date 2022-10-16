package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class UserLoginPage {
    private static final Duration delay = Duration.ofSeconds(10);
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //локатор поля email
    @FindBy(how = How.XPATH, using = "//*[text()='Email']/following-sibling::input")
    private SelenideElement emailLoginField;

    //локатор поля Пароль
    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordLoginField;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement logInButtonLogInPage;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//*[text()='Зарегистрироваться']")
    private SelenideElement registerButtonLogInPage;

    //локатор кнопки Восстановить пароль
    @FindBy(how = How.XPATH, using = "//*[text()='Восстановить пароль']")
    private SelenideElement restorePasswordButtonLogInPage;

    //метод нажимающий на кнопку регистрации
    public void registrationButtonClick() {
        registerButtonLogInPage.shouldBe(Condition.visible).scrollTo().click();
    }

    //метод выполняющий вход
    public void fillFormAndLogIn(String email, String password) {
        emailLoginField.shouldBe(Condition.visible, delay).scrollTo().setValue(email);
        passwordLoginField.shouldBe(Condition.visible, delay).scrollTo().setValue(password);
        logInButtonLogInPage.shouldBe(Condition.visible, delay).click();
    }

    //метод нажимающий кнопку Восстановить пароль
    public void restorePasswordButtonClick() {
        restorePasswordButtonLogInPage.shouldBe(Condition.visible, delay).click();
    }
}
