package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.db.repository.SpendRepository;
import guru.qa.niffler.db.repository.SpendRepositoryHibernate;
import guru.qa.niffler.jupiter.annotation.GenerateSpendDb;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.db.model.CategoryEntity;
import guru.qa.niffler.db.model.SpendEntity;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.Date;
import java.util.Optional;

public class DatabaseSpendExtension extends SpendExtension implements BeforeEachCallback {

    SpendRepository spendRepository = new SpendRepositoryHibernate();

    @Override
    SpendJson create(SpendJson spend) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(spend.category());
        categoryEntity.setUsername(spend.username());

        spendRepository.createCategory(categoryEntity);

        SpendEntity spendEntity = new SpendEntity();
        spendEntity.setAmount(spend.amount());
        spendEntity.setCategory(categoryEntity);
        spendEntity.setUsername(spend.username());
        spendEntity.setCurrency(spend.currency());
        spendEntity.setDescription(spend.description());
        spendRepository.createSpend(spendEntity);
        return spend;
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Optional<GenerateSpendDb> spend = AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                GenerateSpendDb.class
        );

        if (spend.isPresent()) {
            GenerateSpendDb spendData = spend.get();
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
}
