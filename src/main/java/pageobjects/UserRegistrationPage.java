package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class UserRegistrationPage {
    private static final Duration delay = Duration.ofSeconds(10);
    public static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    //локатор поля Имя
    @FindBy(how = How.XPATH, using = "//*[text()='Имя']/following-sibling::input")
    private SelenideElement nameRegistrationField;

    //локатор поля email
    @FindBy(how = How.XPATH, using = "//*[text()='Email']/following-sibling::input")
    private SelenideElement emailRegistrationField;

    //локатор поля Пароль
    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordRegistrationField;

    //локатор строки Некорректный пароль
    @FindBy(how = How.XPATH, using = "    //*[contains(@class, 'input__error text_type_main-default')]")
    private SelenideElement passwordErrorText;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'Auth_link__1fOlj')]")
    private SelenideElement logInRegFormButton;

    //ввод данных пользователя
    public void fillRegistrationForm(String name, String email, String password) {
        nameRegistrationField.shouldBe(Condition.visible, delay).setValue(name);
        emailRegistrationField.shouldBe(Condition.visible, delay).setValue(email);
        passwordRegistrationField.shouldBe(Condition.visible, delay).setValue(password);
    }

    //метод нажатия кнопки Зарегистрироваться
    public void registerButtonClick() {
        registrationButton.shouldBe(Condition.visible, delay).click();
    }

    //метод проверки отображения ошибки о коротком пароле
    public boolean isVisibleShortPasswordErrorMessage() {
        return passwordErrorText.shouldBe(Condition.visible, delay).isDisplayed();
    }

    //метод нажатия кнопки Войти
    public void logInButtonClick() {
        logInRegFormButton.shouldBe(Condition.visible, delay).click();
    }
}