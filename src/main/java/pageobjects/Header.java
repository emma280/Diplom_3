package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class Header {
    private static final Duration delay = Duration.ofSeconds(10);

    //локатор кнопки Конструктор
    @FindBy(how = How.LINK_TEXT, using = "Конструктор")
    private SelenideElement constructorButton;

    //локатор логотипа
    @FindBy(how = How.XPATH,using = ".//*[(@class ='AppHeader_header__logo__2D0X2')]")
    private SelenideElement logoButton;

    //локатор кнопки Личный кабинет
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement userAccountButton;

    //метод нажатия кнопки Личный кабинет
    public void userAccountButtonClick() {
        userAccountButton.shouldBe(Condition.visible, delay).click();
    }

    //метод нажатия на логотип
    public void logoButtonClick() {
        logoButton.shouldBe(Condition.visible, delay).click();
    }

    //метод нажатия на Конструктор
    public void constructorButtonClick() {
        constructorButton.shouldBe(Condition.visible, delay).click();
    }
}
