package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.storage.UserStorageManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;

public class UserProfileUseCase extends AsyncTask<Void, Void, User> {

    private Callback<User> callback;

    public UserProfileUseCase(Callback<User> callback) {
        this.callback = callback;
    }

    @Override
    protected User doInBackground(Void... voids) {
        return UserStorageManager.getInstance().getUser();
    }

    @Override
    protected void onPostExecute(User user) {

        if (user !=null){
            callback.onResult(user);

        }else{
            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }
    }
}
