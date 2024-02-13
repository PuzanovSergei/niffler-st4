package guru.qa.niffler.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class WelcomePage extends BasePage<WelcomePage> {
    private final SelenideElement buttonLogin = $x("//*[@href='/redirect']");
    private final SelenideElement buttonRegister = $x("//a[text()='Register']");

    @Step("Нажать на кнопку Login")
    public LoginPage clickLoginBtn() {
        buttonLogin.click();
        return new LoginPage();
    }

    @Step("Нажать на кнопку Register")
    public RegisterPage clickLoginRegisterBtn() {
        buttonRegister.click();
        return new RegisterPage();
    }

    @Step("Открыть старовую старницу")
    public WelcomePage open() {
        Selenide.open("http://127.0.0.1:3000/main");
        return this;
    }

}
