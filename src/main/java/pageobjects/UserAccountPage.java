package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class UserAccountPage {
    private static final Duration delay = Duration.ofSeconds(10);
    public static final String USER_ACCOUNT_PAGE_URL= "https://stellarburgers.nomoreparties.site/account/profile";

    //локатор кнопки Выход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logOutButton;

    //метод нажатия кнопки Выход
    public void logOutButtonClick() {
        logOutButton.shouldBe(Condition.visible, delay).click();
    }
}
