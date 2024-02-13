package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ConfirmRegisterPage extends BasePage<ConfirmRegisterPage> {

    private final SelenideElement buttonSignIn = $x("//a[text()='Sign in!']");

    @Step("Нажать кнопку Sign up")
    public LoginPage clickSignUpBtn() {
        buttonSignIn.click();
        return new LoginPage();
    }

}
