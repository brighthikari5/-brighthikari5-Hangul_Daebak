package valeriamoscoso.ioc.hanguldaebak.presentation.signIn;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.Callback;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.SignInUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.UtilsLogin;

/**
 * Implementation of sign in presenter
 * @author Valeria Moscoso León
 * */
public class SignInPresenterImpl implements SignInPresenter.Presenter {

    private SignInUseCase signInUseCase;
    private SignInPresenter.View view;

    public SignInPresenterImpl(SignInPresenter.View view) {
        this.view = view;
    }

    @Override
    public void signIn(UserParams userParams) {

        signInUseCase = new SignInUseCase(new Callback<Boolean>() {
            @Override
            public void onResult(Boolean isSuccessFul) {

                if (isSuccessFul){
                    view.onSignInExecuted();
                } else {
                    view.onError("Error General Servidor"); //error que viene del servidor
                }

            }

            @Override
            public void onError(String e) {
                view.onError(e);// error controlado en un catch
            }
        });
        signInUseCase.execute(userParams);
    }

    enum ValidatorState{
        MAIL_ERROR,
        NAME_ERROR,
        SURNAME_ERROR,
        PASSWORD_ERROR,
        ALL_GOOD
    }

    /**
     * @author Valeria Moscoso León
     * Method to validate if user params are correct
     * @param userParams
     * @return state
     * */
    @Override
    public ValidatorState validateParams(UserParams userParams) {

        if (!UtilsLogin.isValidEmail(userParams.getEmail())) {
            return ValidatorState.MAIL_ERROR;
        }
        if (userParams.getName().isEmpty()){
            return ValidatorState.NAME_ERROR;
        }
        if (userParams.getSurname().isEmpty()){
            return ValidatorState.SURNAME_ERROR;
        }
        if (!UtilsLogin.isValidLenght(userParams.getPassword())){
            if (!UtilsLogin.isValidPassword(userParams.getPassword())){
                return  ValidatorState.PASSWORD_ERROR;
            }
        }

        return ValidatorState.ALL_GOOD;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(signInUseCase);

    }
}
