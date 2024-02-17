package guru.qa.niffler.api.user;

import guru.qa.niffler.api.RestClient;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.FriendJson;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;

public class FriendsClient extends RestClient {

    private final FriendsApi friendsApi;

    public FriendsClient() {
        super(Config.getInstance().userDataHost());
        this.friendsApi = retrofit.create(FriendsApi.class);
    }

    @Step("Get all friends")
    public List<UserJson> getFriends(String username, boolean includePending) throws IOException {
        return friendsApi.friends(username, includePending).execute().body();
    }

    @Step("Get invitations")
    public List<UserJson> getInvitations(String username) throws IOException {
        return friendsApi.invitations(username).execute().body();
    }

    @Step("Add invitations")
    public List<UserJson> addInvitations(String username, FriendJson invitation) throws IOException {
        return friendsApi.acceptInvitation(username, invitation).execute().body();
    }

    @Step("Add decline invitations")
    public List<UserJson> addDeclineInvitations(String username, FriendJson invitation) throws IOException {
        return friendsApi.acceptInvitation(username, invitation).execute().body();
    }

    @Step("Add Friend")
    public UserJson addFriend(String username, FriendJson invitation) throws IOException {
        return friendsApi.addFriend(username, invitation).execute().body();
    }

    @Step("Remove Friend")
    public List<UserJson> removeFriend(String username, String friendUsername) throws IOException {
        return friendsApi.removeFriend(username, friendUsername).execute().body();
    }
}
