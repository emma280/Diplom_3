package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class MainPage {
    private static final Duration delay = Duration.ofSeconds(10);
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH,using = ".//button[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg\"]")
    private SelenideElement logInButton;

    //локатор раздела Булки
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Булки')]")
    private SelenideElement bunsSection;

    //локатор раздела Соусы
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Соусы')]")
    private SelenideElement saucesSection;

    //локатор раздела Начинки
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement fillingsSection;

    //локатор Флюоресцентной булки
    @FindBy(how = How.XPATH,using = "//*[contains(text(), 'Флюоресцентная булка')]")
    private SelenideElement fluorescentBun;

    //локатор соуса Spicy-x
    @FindBy(how = How.XPATH,using = "//*[contains(text(), 'Соус Spicy-X')]")
    private SelenideElement spicySauce;

    //локатор мяса бессмертных моллюсков
    @FindBy(how = How.XPATH,using = "//*[contains(text(), 'Мясо бессмертных моллюсков Protostomia')]")
    private SelenideElement molluskMeat;

    //локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH,using = ".//button[contains(text(),'Оформить заказ')]")
    private SelenideElement makeOrderButton;

    //метод, нажимающий кнопку Войти
    public void logInButtonClick() {
        logInButton.shouldBe(Condition.visible, delay).scrollTo().click();
    }

    //метод для клика по разделу Булки
    public void bunsSectionClick() {
        bunsSection.shouldBe(Condition.visible, delay).click();
    }

    //метод для клика по разделу Соусы
    public void saucesSectionClick() {
        saucesSection.shouldBe(Condition.visible, delay).click();
    }

    //метод для клика по разделу Начинки
    public void fillingsSectionClick() {
        fillingsSection.shouldBe(Condition.visible, delay).click();
    }

    //метод для проверки отображения кнопки Сделать заказ
    public boolean isVisibleMakeOrderButton() {
        return makeOrderButton.shouldBe(Condition.visible, delay).isDisplayed();
    }

    //метод для проверки видимости содержимого раздела Булки
    public boolean isVisibleBunsSection() {
        return fluorescentBun.shouldBe(Condition.visible, delay).isDisplayed();
    }

    //метод для проверки видимости содержимого раздела Соусы
    public boolean isVisibleSaucesSection() {
        return spicySauce.shouldBe(Condition.visible, delay).isDisplayed();
    }

    //метод для проверки видимости содержимого раздела Начинки
    public boolean isVisibleFillingsSection() {
        return molluskMeat.shouldBe(Condition.visible, delay).isDisplayed();
    }
}
