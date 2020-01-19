package valeriamoscoso.ioc.hanguldaebak.data.storage;

import valeriamoscoso.ioc.hanguldaebak.data.repository.SharedPreferencesManager;

/**
 * TokenStorageManager is the only class that is in charge of handling the Token storage through Shared Preferences Manager
 * */
public class TokenStorageManager {

    private static final String TOKEN = "TOKEN";
    private static TokenStorageManager tokenRepository = null;

    private TokenStorageManager() {
    }

    //singleton
    public static synchronized TokenStorageManager getInstance() {
        if (tokenRepository == null) {
            return new TokenStorageManager();
        }
        return tokenRepository;
    }

    public String getToken() {
        return SharedPreferencesManager.getInstance().getString(TOKEN);
    }

    public void saveToken(String token) {
        SharedPreferencesManager.getInstance().saveString(TOKEN, token);
    }

    public Boolean hasToken (){

        return !getToken().isEmpty();

    }

    public void deleteToken() {

        saveToken("");
    }
}
