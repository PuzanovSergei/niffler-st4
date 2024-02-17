package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.CategoryApi;
import guru.qa.niffler.api.SpendApi;
import guru.qa.niffler.jupiter.annotation.GenerateSpendApi;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.SpendJson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RestSpendExtension extends SpendExtension implements BeforeEachCallback {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder().build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl("http://127.0.0.1:8093")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final SpendApi spendApi = retrofit.create(SpendApi.class);
    private final CategoryApi categoryApi = retrofit.create(CategoryApi.class);

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Optional<GenerateSpendApi> spend = AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                GenerateSpendApi.class
        );

        if (spend.isPresent()) {
            GenerateSpendApi spendData = spend.get();
            SpendJson spendJson = new SpendJson(
                    null,
                    new Date(),
                    spendData.category(),
                    spendData.currency(),
                    spendData.amount(),
                    spendData.description(),
                    spendData.username()
            );

            SpendJson created = create(spendJson);
            extensionContext.getStore(NAMESPACE)
                    .put(extensionContext.getUniqueId(), created);
        }
    }

    @Override
    SpendJson create(SpendJson spend) {
        CategoryJson categoryJson = new CategoryJson(
                null,
                spend.category(),
                spend.username());
        List<CategoryJson> categoriesUser = getCategories(spend.username());
        if (!categoriesUser.stream().anyMatch(x -> x.category().equals(spend.category()))) {
            addCategory(categoryJson);
        }
        return createSpend(spend);
    }

    private SpendJson createSpend(SpendJson spend) {
        try {
            return spendApi.addSpend(spend).execute().body();
        } catch (IOException e) {
            throw new RuntimeException("Error when create spend use api " + e);
        }
    }

    private CategoryJson addCategory(CategoryJson categoryJson) {
        try {
            return categoryApi.addCategory(categoryJson).execute().body();
        } catch (IOException e) {
            throw new RuntimeException("Error when create category use api " + e);
        }
    }

    private List<CategoryJson> getCategories(String userName) {
        try {
            return categoryApi.getCategories(userName).execute().body();
        } catch (IOException e) {
            throw new RuntimeException("Error when get category for user use api " + e);
        }
    }
}
