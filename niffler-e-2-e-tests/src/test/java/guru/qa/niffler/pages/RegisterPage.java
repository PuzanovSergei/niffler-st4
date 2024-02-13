package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage extends BasePage<RegisterPage> {

    private final SelenideElement filedUsername = $x("//input[@id='username']");
    private final SelenideElement filedPassword = $x("//input[@id='password']");
    private final SelenideElement filedSubmitPassword = $x("//input[@id='passwordSubmit']");
    private final SelenideElement buttonSignUp = $x("//button[@type='submit']");

    @Step("Заполнить поле Username значением {userName}")
    public RegisterPage setUserName(String userName) {
        filedUsername.setValue(userName);
        return this;
    }

    @Step("Заполнить поле Password значением {password}")
    public RegisterPage setPassword(String password) {
        filedPassword.setValue(SetValueOptions.withText(password).sensitive());
        return this;
    }

    @Step("Заполнить поле Submit Password значением {submitPassword}")
    public RegisterPage setSubmitPassword(String submitPassword) {
        filedSubmitPassword.setValue(SetValueOptions.withText(submitPassword).sensitive());
        return this;
    }

    @Step("Нажать кнопку Sign up")
    public ConfirmRegisterPage clickSignUpBtn() {
        buttonSignUp.click();
        return new ConfirmRegisterPage();
    }
}
