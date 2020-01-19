package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.LoginRepositoryImpl;

/**
 * Sign In UseCause with the async task to make the petition.
 * @author Valeria Moscoso León
 * */
public class SignInUseCase extends AsyncTask <UserParams, String, Boolean> {

    private Callback<Boolean> callback;

    public SignInUseCase (Callback<Boolean> callback){
        this.callback = callback;
    }

    /**
     * method to use an asyncronous thread and execute the petition to server
     * @author Valeria Moscoso León
     * @return intance of LoginRepositoryImpl
     * */
    @Override
    protected Boolean doInBackground(UserParams... userParams) {
        return LoginRepositoryImpl.getInstance().signIn(userParams[0]);
    }

    @Override
    protected void onPostExecute(Boolean isSuccessful) {
        if (isSuccessful != null){
            callback.onResult(isSuccessful);
        }else {
            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }
    }
}
