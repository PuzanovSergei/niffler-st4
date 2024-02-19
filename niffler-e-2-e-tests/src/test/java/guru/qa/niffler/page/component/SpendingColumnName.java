package guru.qa.niffler.page.component;

public enum SpendingColumnName {
    DATE(1),
    AMOUNT(2),
    CURRENCY(3),
    CATEGORY(4),
    DESCRIPTION(5);


    private final Integer columnNumber;

    SpendingColumnName(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Integer getNumberCell() {
        return columnNumber;
    }
}
