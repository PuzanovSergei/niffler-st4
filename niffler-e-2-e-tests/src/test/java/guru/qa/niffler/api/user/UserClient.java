package guru.qa.niffler.api.user;

import guru.qa.niffler.api.RestClient;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class UserClient extends RestClient {

    private final UserApi userApi;

    public UserClient() {
        super(Config.getInstance().userDataHost());
        this.userApi = retrofit.create(UserApi.class);
    }

    @Step("Get Current User")
    public UserJson getCurrentUser(String username) throws IOException {
        return userApi.currentUser(username).execute().body();
    }

    @Step("Get All Users")
    public List<UserJson> getAllUser(String username) throws IOException {
        return userApi.allUsers(username).execute().body();
    }

    @Step("Update user info")
    public UserJson updateUserInfo(UserJson user) throws IOException {
        return userApi.updateUserInfo(user).execute().body();
    }
}
