package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;
import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.LoginRepositoryImpl;

/**
 * Log In UseCause with the async task to make the petition.
 * @author Valeria Moscoso León
 * */
public class LogInUseCase extends AsyncTask <UserParamsLogIn, String, Boolean> {

    private Callback<Boolean> callback;

    public LogInUseCase (Callback<Boolean> callback){
        this.callback=callback;
    }

    @Override
    protected Boolean doInBackground(UserParamsLogIn... userParamsLogIns) {
        return LoginRepositoryImpl.getInstance().logIn(userParamsLogIns[0]);
    }

    @Override
    protected void onPostExecute(Boolean isSuccessful ) {
        if (isSuccessful != null){
            callback.onResult(isSuccessful);
        }else {
            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }

    }
}
