package guru.qa.niffler.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage extends BasePage<ProfilePage> {
    private final SelenideElement filedFirstname = $x("//input[@name='firstname']");
    private final SelenideElement filedSurname = $x("//input[@name='surname']");
    private final SelenideElement filedCategory = $x("//input[@name='category']");
    private final SelenideElement createBtn = $x("//button[text()='Create']");
    private final SelenideElement submitBtn = $x("//button[text()='Submit']");
    private final SelenideElement editAvatar = $x("//button[@class='profile__avatar-edit']");
    private final SelenideElement inputAvatar = $x("//input[@type='file']");

    @Step("Нажать кнопку Create")
    public ProfilePage clickCreateBtn() {
        createBtn.click();
        return this;
    }

    @Step("Нажать кнопку Submit")
    public ProfilePage clickSubmitBtn() {
        submitBtn.shouldBe(Condition.visible, Condition.enabled).click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Заполнить поле Firstname значением {firstname}")
    public ProfilePage setFirstname(String firstname) {
        filedFirstname.setValue(firstname);
        return this;
    }

    @Step("Заполнить поле Surname значением {surname}")
    public ProfilePage setSurname(String surname) {
        filedSurname.setValue(surname);
        return this;
    }

    @Step("Заполнить поле Category значением {category}")
    public ProfilePage setCategory(String category) {
        filedCategory.setValue(category);
        return this;
    }

    @Step("Загрузить аватар с иеменем {category}")
    public ProfilePage uploadAvatar(String imgName) {
        editAvatar.click();
        inputAvatar.uploadFromClasspath(imgName);
        return this;
    }
}
