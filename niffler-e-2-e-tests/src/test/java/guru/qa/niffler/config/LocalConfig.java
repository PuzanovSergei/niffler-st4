package guru.qa.niffler.config;

public class LocalConfig implements Config {

    static final LocalConfig instance = new LocalConfig();
    static final String localHostUrl = "http://127.0.0.1";

    private LocalConfig() {
    }

    @Override
    public String frontUrl() {
        return localHostUrl + ":3000";
    }

    @Override
    public String jdbcHost() {
        return "localhost";
    }

    @Override
    public String userDataHost() {
        return localHostUrl + ":8089";
    }

    @Override
    public String currencyHost() {
        return localHostUrl + ":8091";
    }

    @Override
    public String spendHost() {return localHostUrl + ":8093";
    }
}
