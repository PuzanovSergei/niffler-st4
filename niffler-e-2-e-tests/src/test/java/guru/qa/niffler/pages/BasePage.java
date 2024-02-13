package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.message.Msg;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage<T extends BasePage> {
    protected final SelenideElement toaster = $(".Toastify__toast-body");
    protected final SelenideElement friendsBtn = $x("//a[@href='/friends']");
    protected final SelenideElement peopleBtn = $x("//a[@href='/people']");
    protected final SelenideElement profileBtn = $x("//a[@href='/profile']");
    protected final SelenideElement mainPageBtn = $x("//a[@href='/main']");
    protected final SelenideElement logoutBtn = $x("//button[contains(@class,'logout')]");

    @SuppressWarnings("unchecked")
    @Step("На странице появилось всплывающее сообщение с текстом {msg}")
    public T checkMessage(Msg msg) {
        toaster.shouldHave(text(msg.getMessage()));
        return (T) this;
    }

    @Step("Нажать кнопку Friends")
    public FriendsPage clickFriendsBtn() {
        friendsBtn.click();
        return new FriendsPage();
    }

    @Step("Нажать кнопку All people")
    public AllPeoplePage clickAllPeopleBtn() {
        peopleBtn.click();
        return new AllPeoplePage();
    }

    @Step("Нажать кнопку Main page")
    public MainPage clickMainPageBtn() {
        mainPageBtn.click();
        return new MainPage();
    }

    @Step("Нажать кнопку Аватар профиля")
    public ProfilePage clickProfileBtn() {
        profileBtn.click();
        return new ProfilePage();
    }

    @Step("Нажать кнопку Logout")
    public WelcomePage clickLogoutBtn() {
        logoutBtn.click();
        return new WelcomePage();
    }
}

