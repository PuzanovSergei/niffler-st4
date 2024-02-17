package guru.qa.niffler.api.spend;

import guru.qa.niffler.api.RestClient;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.CategoryJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class CategoriesClient extends RestClient {

    private final CategoriesApi categoriesApi;

    public CategoriesClient() {
        super(Config.getInstance().spendHost());
        this.categoriesApi = retrofit.create(CategoriesApi.class);
    }

    @Step("Get categories")
    public List<CategoryJson> getCategories(String username) throws IOException {
        return categoriesApi.getCategories(username).execute().body();
    }

    @Step("Add category")
    public CategoryJson addCategory(CategoryJson category) throws IOException {
        return categoriesApi.addCategory(category).execute().body();
    }
}
