package guru.qa.niffler.test.parallel;

import guru.qa.niffler.db.model.UserAuthEntity;
import guru.qa.niffler.jupiter.annotation.DbUser;
import guru.qa.niffler.page.message.ErrorMsg;
import guru.qa.niffler.page.message.SuccessMsg;
import guru.qa.niffler.test.BaseWebTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class FirstSet extends BaseWebTest {

    @DbUser
    @Test
    void oneFirstSet(UserAuthEntity userAuth) {
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
    void twoFirstSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickFriendsBtn();
    }

    @DbUser
    @Test
    void threeFirstSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickProfileBtn()
                .uploadAvatar("img/2.jpg");
    }

    @DbUser
    @Test
    void fourFirstSet(UserAuthEntity userAuth) {
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
    void fiveFirstSet(UserAuthEntity userAuth) {
        welcomePage.open()
                .clickLoginBtn()
                .authorize(userAuth.getUsername(), userAuth.getPassword())
                .clickProfileBtn()
                .uploadAvatar("img/3.jpg");
    }
}
