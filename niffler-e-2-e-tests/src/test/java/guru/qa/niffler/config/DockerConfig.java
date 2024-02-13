package guru.qa.niffler.config;

import com.codeborne.selenide.Configuration;

public class DockerConfig implements Config {

    static final DockerConfig instance = new DockerConfig();

    private DockerConfig() {
    }

    static {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
    }

    @Override
    public String frontUrl() {
        return "http://frontend.niffler.dc";
    }

    @Override
    public String jdbcHost() {
        return "niffler-all-db";
    }

    @Override
    public String userDataHost() {
        return "http://userdata.niffler.dc:8089";
    }

    @Override
    public String currencyHost() {
        return "http://currency.niffler.dc:8091";
    }

    @Override
    public String spendHost() {
        return "http://spend.niffler.dc:8093";
    }
}
