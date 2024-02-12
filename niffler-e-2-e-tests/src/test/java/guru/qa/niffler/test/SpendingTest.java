package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.GenerateSpendApi;
import guru.qa.niffler.jupiter.annotation.GenerateSpendDb;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.pages.WelcomePage;
import org.junit.jupiter.api.Test;

public class SpendingTest extends BaseWebTest {

    private final String userName = "duck";

    @GenerateSpendApi(
            username = userName,
            category = "Клуб",
            description = "От заката до рассвета",
            amount = 96500.00,
            currency = CurrencyValues.RUB
    )
    @Test
    void spendingShouldBeDeletedByButtonDeleteSpending(SpendJson spend) {
        Selenide.open("http://127.0.0.1:3000/main", WelcomePage.class)
                .clickLoginButton()
                .authorize(userName, "12345")
                .selectRowInTableByText(spend.category())
                .clickDeleteButton()
                .checkTableIsEmpty();
    }

    @GenerateSpendDb(
            username = userName,
            category = "Ресторан",
            description = "Плакучая Ива",
            amount = 227501.00,
            currency = CurrencyValues.RUB
    )
    @Test
    void createSpendingByDbAnfDeletedByButtonDelete(SpendJson spend) {
        Selenide.open("http://127.0.0.1:3000/main", WelcomePage.class)
                .clickLoginButton()
                .authorize(userName, "12345")
                .selectRowInTableByText(spend.category())
                .clickDeleteButton()
                .checkTableIsEmpty();
    }
}
