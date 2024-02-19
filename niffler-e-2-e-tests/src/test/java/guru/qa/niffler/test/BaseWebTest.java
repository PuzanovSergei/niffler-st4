package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.pages.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserExtension.class})
public abstract class BaseWebTest {

  protected static final Config CFG = Config.getInstance();
  protected final AllPeoplePage allPeoplePage = new AllPeoplePage();
  protected final FriendsPage friendsPage = new FriendsPage();
  protected final LoginPage loginPage = new LoginPage();
  protected final MainPage mainPage = new MainPage();
  protected final WelcomePage welcomePage = new WelcomePage();
}
