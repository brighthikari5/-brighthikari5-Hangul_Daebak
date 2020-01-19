package valeriamoscoso.ioc.hanguldaebak.presentation.logIn;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.Callback;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.LogInUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.UtilsLogin;

/**
 * Implementation of log in presenter
 * @author Valeria Moscoso León
 * */
public class LogInPresenterImpl implements LogInPresenter.Presenter {

    private LogInUseCase logInUseCase;
    private LogInPresenter.View view;

    public LogInPresenterImpl (LogInPresenter.View view){
        this.view=view;
    }


    @Override
    public void logIn(UserParamsLogIn userParamsLogIn) {

        logInUseCase = new LogInUseCase(new Callback<Boolean>() {
            @Override
            public void onResult(Boolean isSuccessFul) {

                if (isSuccessFul){
                    view.onLogInExecuted();
                } else {
                    view.onError("Error General Servidor"); //error que viene del servidor
                }

            }

            @Override
            public void onError(String e) {
                view.onError(e);// error controlado en un catch
            }
        });
        logInUseCase.execute(userParamsLogIn);

    }

    enum ValidatorState{
        MAIL_ERROR,
        PASSWORD_ERROR,
        ALL_GOOD
    }

    public ValidatorState validateParams (UserParamsLogIn userParamsLogIn){

        if (!UtilsLogin.isValidEmail(userParamsLogIn.getEmail())){
            return ValidatorState.MAIL_ERROR;
        }

        if (!UtilsLogin.isValidLenght(userParamsLogIn.getPassword())){
            if(!UtilsLogin.isValidPassword(userParamsLogIn.getPassword())){
                return ValidatorState.PASSWORD_ERROR;
            }

        }


        return ValidatorState.ALL_GOOD;
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(logInUseCase);

    }
}
