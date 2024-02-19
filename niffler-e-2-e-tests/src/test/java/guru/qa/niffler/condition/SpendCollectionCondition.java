package guru.qa.niffler.condition;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Driver;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.component.SpendingColumnName;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SpendCollectionCondition {

    public static CollectionCondition spends(SpendJson... expectedSPends) {
        return new CollectionCondition() {

            @Nonnull
            @Override
            public CheckResult check(Driver driver, List<WebElement> elements) {
                if (elements.size() != expectedSPends.length) {
                    return CheckResult.rejected("Incorrect table size", elements);
                }
                for (WebElement element : elements) {
                    List<WebElement> tds = element.findElements(By.cssSelector("td"));
                    for (SpendJson expectedSPend : expectedSPends) {
                        checkByCellName(SpendingColumnName.DATE, convertDateToUiValue(expectedSPend.spendDate()), tds);
                        checkByCellName(SpendingColumnName.AMOUNT, convertAmountToUiValue(expectedSPend.amount()), tds);
                        checkByCellName(SpendingColumnName.CURRENCY, String.valueOf(expectedSPend.currency()), tds);
                        checkByCellName(SpendingColumnName.CATEGORY, expectedSPend.category(), tds);
                        checkByCellName(SpendingColumnName.DESCRIPTION, expectedSPend.description(), tds);
                    }
                }
                return super.check(driver, elements);
            }

            private void checkByCellName(SpendingColumnName columnName, String expectValue, List<WebElement> tds) {
                Assertions.assertEquals(tds.get(columnName.getNumberCell()).getText(),
                        expectValue,
                        "Field with name " + columnName + " contains invalid value");
            }

            private String convertDateToUiValue(Date jsonDate) {
                SimpleDateFormat sdt = new SimpleDateFormat("d MMM yy", Locale.US);
                return sdt.format(jsonDate);
            }

            private String convertAmountToUiValue(Double amount) {
                String penny = StringUtils.substringAfter(amount.toString(), ".");
                return "0".equals(penny)
                        ? StringUtils.substringBefore(amount.toString(), ".")
                        : amount.toString();
            }

            @Override
            public boolean missingElementSatisfiesCondition() {
                return false;
            }
        };
    }
}