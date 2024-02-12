package guru.qa.niffler.db.repository;

import guru.qa.niffler.db.EmfProvider;
import guru.qa.niffler.db.jpa.JpaService;
import guru.qa.niffler.db.model.CategoryEntity;
import guru.qa.niffler.db.model.SpendEntity;

import static guru.qa.niffler.db.Database.SPEND;

public class SpendRepositoryHibernate extends JpaService implements SpendRepository {

    public SpendRepositoryHibernate() {
        super(SPEND, EmfProvider.INSTANCE.emf(SPEND).createEntityManager());
    }

    @Override
    public SpendEntity createSpend(SpendEntity spendEntity) {
        persist(SPEND, spendEntity);
        return spendEntity;
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        persist(SPEND, categoryEntity);
        return categoryEntity;
    }

}

