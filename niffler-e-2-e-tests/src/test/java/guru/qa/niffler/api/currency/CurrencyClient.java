package guru.qa.niffler.api.currency;

import guru.qa.niffler.api.RestClient;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.CurrencyCalculateJson;
import guru.qa.niffler.model.CurrencyJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class CurrencyClient extends RestClient {

    private final CurrencyApi currencyApi;

    public CurrencyClient() {
        super(Config.getInstance().currencyHost());
        this.currencyApi = retrofit.create(CurrencyApi.class);
    }

    @Step("Get all Currencies")
    public List<CurrencyJson> getAllCurrencies() throws IOException {
        return currencyApi.getAllCurrencies().execute().body();
    }

    @Step("Calculate")
    public CurrencyCalculateJson getAllCurrencies(CurrencyCalculateJson currencyCalculate) throws IOException {
        return currencyApi.getAllCurrencies(currencyCalculate).execute().body();
    }
}

