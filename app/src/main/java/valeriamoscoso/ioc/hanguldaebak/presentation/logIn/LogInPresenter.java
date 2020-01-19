package valeriamoscoso.ioc.hanguldaebak.presentation.logIn;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BaseView;

abstract class LogInPresenter {

    interface Presenter extends BasePresenter{
        void logIn(UserParamsLogIn userParamsLogIn);

        LogInPresenterImpl.ValidatorState validateParams (UserParamsLogIn userParamsLogIn);
    }

    interface View extends BaseView{
       void onLogInExecuted();

    }

}
