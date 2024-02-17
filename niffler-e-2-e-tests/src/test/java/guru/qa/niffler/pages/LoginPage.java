package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage<LoginPage> {
    private final SelenideElement filedUsername = $x("//input[@name='username']");
    private final SelenideElement filedPassword = $x("//input[@name='password']");
    private final SelenideElement buttonSubmit = $x("//button[@type='submit']");

    @Step("Авторизоваться в системе с userName = {userName} и password={password}")
    public MainPage authorize(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickButtonSubmit();
        return new MainPage();
    }

    @Step("Заполнить поле Username значением {userName}")
    public LoginPage setUserName(String userName) {
        filedUsername.setValue(userName);
        return this;
    }

    @Step("Заполнить поле Password значением {password}")
    public LoginPage setPassword(String password) {
        filedPassword.setValue(SetValueOptions.withText(password).sensitive());
        return this;
    }

    @Step("Нажать кнопку Sign up")
    public LoginPage clickButtonSubmit() {
        buttonSubmit.click();
        return this;
    }
}
