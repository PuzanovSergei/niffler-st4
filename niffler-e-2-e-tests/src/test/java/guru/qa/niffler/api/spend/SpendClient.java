package guru.qa.niffler.api.spend;

import guru.qa.niffler.api.RestClient;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.model.StatisticJson;
import io.qameta.allure.Step;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SpendClient extends RestClient {

    private final SpendApi spendApi;

    public SpendClient() {
        super(Config.getInstance().spendHost());
        this.spendApi = retrofit.create(SpendApi.class);
    }

    @Step("Get Spends")
    public List<SpendJson> getSpends(String username,
                                     CurrencyValues filterCurrency,
                                     Date from,
                                     Date to) throws IOException {
        return spendApi.getSpends(username, filterCurrency, from, to).execute().body();
    }

    @Step("Get Statistic")
    public List<StatisticJson> getStatistic(String username,
                                            CurrencyValues userCurrency,
                                            CurrencyValues filterCurrency,
                                            Date from,
                                            Date to) throws IOException {
        return spendApi.getStatistic(username, userCurrency, filterCurrency, from, to).execute().body();
    }

    @Step("Add Spend")
    public SpendJson addSpend(SpendJson spend) throws IOException {
        return spendApi.addSpend(spend).execute().body();
    }

    @Step("Edit Spend")
    public SpendJson editSpend(SpendJson spend) throws IOException {
        return spendApi.editSpend(spend).execute().body();
    }

    @Step("Delete Spend")
    public void deleteSpends(String username, List<String> ids) throws IOException {
        spendApi.deleteSpends(username, ids).execute();
    }
}
