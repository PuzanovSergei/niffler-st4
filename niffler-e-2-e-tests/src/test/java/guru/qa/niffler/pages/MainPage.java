package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.component.SpendingTable;
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
    private final SelenideElement table = $x("//table//tbody");
    private final SelenideElement deleteButton = $(byText("Delete selected"));
    private final SelenideElement addNewSpending = $x("//a[text()='Add new spending']");
    protected final SpendingTable spendingTable = new SpendingTable();
    private final SelenideElement amount = $x("//input[@name='amount']");
    private final SelenideElement description = $x("//input[@name='description']");
    private final SelenideElement spendDate = $x("//*[@class='calendar-wrapper']//input");
    private final SelenideElement categoryInput = $x("//*[text()='Choose spending category']/..//input");
    private final SelenideElement category = $x("//*[text()='Choose spending category']");

    @Step("В таблице выделить строку с текстом {expectText}")
    public MainPage selectRowInTableByText(String expectText) {
        table.$$("tr").find(text(expectText))
                .$("td")
                .scrollTo()
                .click();
        return this;
    }

    @Step("В таблице выделить строку с индексом {index}")
    public MainPage selectRowInTableByIndex(Integer index) {
        table.$$("tr").get(index)
                .$("td")
                .scrollTo()
                .click();
        return this;
    }

    @Step("Проскролить страницу до таблицы Spending")
    public SpendingTable getSpendingTable() {
        spendingTable.getSelf().scrollIntoView(true);
        return spendingTable;
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

    @Step("Заполнить поле category значением {categoryName}")
    public MainPage selectCategoryName(String categoryName) {
        category.click();
        categoryInput.setValue(categoryName).pressEnter();
        return this;
    }

    @Step("Заполнить поле amount значением {amount}")
    public MainPage setAmount(String amountValue) {
        amount.setValue(amountValue).pressEnter();
        return this;
    }

    @Step("Заполнить поле date значением {date}")
    public MainPage setDate(String date) {
        spendDate.setValue(date).pressEnter();
        return this;
    }

    @Step("Заполнить поле date значением {description}")
    public MainPage setDescription(String descriptionValue) {
        description.setValue(descriptionValue).pressEnter();
        return this;
    }

    @Step("Проверить что таблица не содержит записей")
    public MainPage checkTableIsEmpty() {
        table.$$("tr").shouldHave(sizeLessThanOrEqual(1));
        return this;
    }
}
