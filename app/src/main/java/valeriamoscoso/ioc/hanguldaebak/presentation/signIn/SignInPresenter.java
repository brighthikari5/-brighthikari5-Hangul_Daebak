package valeriamoscoso.ioc.hanguldaebak.presentation.signIn;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BaseView;

/**
 * Sign in presenter for sign in
 * @author Valeria Moscoso León
 * */
abstract class SignInPresenter {

    interface Presenter extends BasePresenter {
        void signIn(UserParams userParams);
        SignInPresenterImpl.ValidatorState validateParams(UserParams userParams);
    }

    interface View extends BaseView {
        void onSignInExecuted();
    }


}
