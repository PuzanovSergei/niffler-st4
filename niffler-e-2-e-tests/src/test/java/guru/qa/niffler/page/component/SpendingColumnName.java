package guru.qa.niffler.page.component;

public enum SpendingColumnName {
    DATE,
    AMOUNT,
    CURRENCY,
    CATEGORY,
    DESCRIPTION;
    
    public Integer getNumberCell() {
        return this.ordinal() + 1;
    }
}
