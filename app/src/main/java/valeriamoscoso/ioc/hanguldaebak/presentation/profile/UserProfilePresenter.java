package valeriamoscoso.ioc.hanguldaebak.presentation.profile;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BaseView;

abstract class UserProfilePresenter {

    interface Presenter extends BasePresenter{

        void getUserProfile();
    }

    interface View extends BaseView {

        void onUserProfileReceived(User user);

    }

}
