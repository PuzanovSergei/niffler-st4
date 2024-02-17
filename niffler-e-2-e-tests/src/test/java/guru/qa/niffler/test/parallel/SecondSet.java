package guru.qa.niffler.test.parallel;

import guru.qa.niffler.db.model.UserAuthEntity;
import guru.qa.niffler.jupiter.annotation.DbUser;
import guru.qa.niffler.page.message.ErrorMsg;
import guru.qa.niffler.page.message.SuccessMsg;
import guru.qa.niffler.test.BaseWebTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class SecondSet extends BaseWebTest {

    @DbUser
    @Test
    void oneSecondSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickAllPeopleBtn()
                .clickLogoutBtn();
    }

    @DbUser
    @Test
    void twoSecondSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickProfileBtn()
                .uploadAvatar("img/1.jpg");
    }

    @DbUser
    @Test
    void threeSecondSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickProfileBtn()
                .setCategory(RandomStringUtils.randomAlphabetic(10))
                .clickCreateBtn()
                .checkMessage(SuccessMsg.NEW_CATEGORY_ADD);
    }

    @DbUser
    @Test
    void fourSecondSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickProfileBtn()
                .setFirstname(RandomStringUtils.randomAlphabetic(10))
                .setSurname(RandomStringUtils.randomAlphabetic(10))
                .clickSubmitBtn()
                .checkMessage(SuccessMsg.PROFILE_MSG);
    }

    @DbUser
    @Test
    void fiveSecondSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickFriendsBtn()
                .clickAllPeopleBtn()
                .clickMainPageBtn()
                .clickLogoutBtn();
    }
}
