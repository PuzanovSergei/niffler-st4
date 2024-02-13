package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;
import org.hibernate.annotations.Comment;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BasePage<MainPage> {
    private final SelenideElement table = $x("//table");
    private final SelenideElement deleteButton = $(byText("Delete selected"));
    private final SelenideElement addNewSpending = $x("//a[text()='Add new spending']");

    @Step("В таблице выделить строку с текстом {expectText}")
    public MainPage selectRowInTableByText(String expectText) {
        table.$$("tr").find(text(expectText))
                .$("td")
                .scrollTo()
                .click();
        return this;
    }

    @Step("Нажать кнопку Delete selected")
    public MainPage clickDeleteButton() {
        deleteButton.click();
        return this;
    }

    @Step("Нажать кнопку Add new spending")
    public MainPage clickaddNewSpendingBtn() {
        addNewSpending.click();
        return this;
    }

    @Step("Проверить что таблица не содержит записей")
    public MainPage checkTableIsEmpty() {
        table.$$("tr").shouldHave(sizeLessThanOrEqual(1));
        return this;
    }


}
