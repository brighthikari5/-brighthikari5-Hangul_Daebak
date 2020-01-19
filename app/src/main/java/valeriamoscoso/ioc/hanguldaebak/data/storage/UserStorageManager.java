package valeriamoscoso.ioc.hanguldaebak.data.storage;

import com.google.gson.Gson;

import valeriamoscoso.ioc.hanguldaebak.data.repository.SharedPreferencesManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;

/**
 * UserStorageManager is the only class that is in charge of handling the User storage through Shared Preferences Manager
 * */
public class UserStorageManager {

    private static final String USER = "USER";
    private static UserStorageManager userDAO = null;

    private Gson gson = new Gson();

    private UserStorageManager() {
    }

    //singleton
    public static synchronized UserStorageManager getInstance() {
        if (userDAO == null) {
            return new UserStorageManager();
        }
        return userDAO;
    }

    public User getUser() {
        String userJson = SharedPreferencesManager.getInstance().getString(USER);
        return gson.fromJson(userJson,User.class);
    }

    public void saveUser(User user) {
        String userJsonString = gson.toJson(user);
        SharedPreferencesManager.getInstance().saveString(USER, userJsonString);
    }
}
