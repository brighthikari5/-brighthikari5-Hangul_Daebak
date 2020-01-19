package valeriamoscoso.ioc.hanguldaebak.presentation.profile;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.Callback;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.UserProfileUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;

public class UserProfilePresenterImpl implements UserProfilePresenter.Presenter {

    private UserProfileUseCase userProfileUseCase;


    private UserProfilePresenter.View view;

    public UserProfilePresenterImpl(UserProfilePresenter.View view) {
        this.view = view;
    }

    @Override
    public void getUserProfile() {

        userProfileUseCase = new UserProfileUseCase(new Callback<User>() {
            @Override
            public void onResult(User result) {
                if (result != null){
                    view.onUserProfileReceived(result);
                }else{
                    view.onError("Error General Servidor");
                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);

            }
        });
        userProfileUseCase.execute();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(userProfileUseCase);
    }
}
