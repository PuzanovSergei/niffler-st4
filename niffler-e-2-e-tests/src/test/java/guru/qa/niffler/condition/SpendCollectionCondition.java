package guru.qa.niffler.condition;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Driver;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.component.SpendingColumnName;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SpendCollectionCondition {

    public static CollectionCondition spends(SpendJson... expectedSpends) {
        return new CollectionCondition() {

            @Nonnull
            @Override
            public CheckResult check(Driver driver, List<WebElement> elements) {
                if (elements.size() != expectedSpends.length) {
                    return CheckResult.rejected("Incorrect table size", elements);
                }
                boolean accepted = true;
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("Incorrect value in column ");
                for (int i = 0; i < expectedSpends.length; i++) {
                    WebElement row = elements.get(i);
                    SpendJson expectedSpending = expectedSpends[i];
                    List<WebElement> cells = row.findElements(By.cssSelector("td"));

                    if (!cells.get(SpendingColumnName.DATE.getNumberCell()).getText().equals(convertDateToUiValue(expectedSpending.spendDate()))) {
                        accepted = false;
                        errorMessage.append(SpendingColumnName.DATE)
                                .append(", expect: ")
                                .append(convertDateToUiValue(expectedSpending.spendDate()))
                                .append("\n");
                    }
                    if (!cells.get(SpendingColumnName.AMOUNT.getNumberCell()).getText().equals(convertAmountToUiValue(expectedSpending.amount()))) {
                        accepted = false;
                        errorMessage.append(SpendingColumnName.AMOUNT).append(", expect: ")
                                .append(convertAmountToUiValue(expectedSpending.amount()))
                                .append("\n");
                    }
                    if (!cells.get(SpendingColumnName.CURRENCY.getNumberCell()).getText().equals(expectedSpending.currency().name())) {
                        accepted = false;
                        errorMessage.append(SpendingColumnName.CURRENCY).append(", expect: ")
                                .append(expectedSpending.currency().name())
                                .append("\n");
                    }
                    if (!cells.get(SpendingColumnName.CATEGORY.getNumberCell()).equals(expectedSpending.category())) {
                        accepted = false;
                        errorMessage.append(SpendingColumnName.CATEGORY).append(", expect: ")
                                .append(expectedSpending.category())
                                .append("\n");
                    }
                    if (!cells.get(SpendingColumnName.DESCRIPTION.getNumberCell()).equals(expectedSpending.description())) {
                        accepted = false;
                        errorMessage.append(SpendingColumnName.DESCRIPTION).append(", expect: ")
                                .append(expectedSpending.description())
                                .append("\n");
                    }
                }
                if (accepted) {
                    return CheckResult.accepted();
                } else {
                    return CheckResult.rejected("Incorrect spends content: ", errorMessage);
                }
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