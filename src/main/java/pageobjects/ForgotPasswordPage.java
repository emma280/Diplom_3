package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class ForgotPasswordPage {
    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final Duration delay = Duration.ofSeconds(10);

    //локатор поля email
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text input__textfield text_type_main-default')]")
    private SelenideElement emailForgotPasswordField;

    //локатор кнопки Восстановить
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa')]")
    private SelenideElement restoreForgotPasswordField;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'Auth_link__1fOlj')]")
    private SelenideElement logInForgotPasswordPage;

    //метод для нажатия кнопки Войти
    public void logInButtonClick() {
        logInForgotPasswordPage.shouldBe(Condition.visible, delay).click();
    }
}