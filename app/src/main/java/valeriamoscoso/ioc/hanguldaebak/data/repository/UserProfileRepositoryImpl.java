package valeriamoscoso.ioc.hanguldaebak.data.repository;

import valeriamoscoso.ioc.hanguldaebak.data.storage.UserStorageManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;
import valeriamoscoso.ioc.hanguldaebak.domain.repository.UserProfileRepository;

public class UserProfileRepositoryImpl implements UserProfileRepository {


    private UserProfileRepositoryImpl() {
    }

    private static UserProfileRepository userProfileRepository = null;

    //singleton
    public static synchronized UserProfileRepository getInstance(){

        if (userProfileRepository == null){
            return new UserProfileRepositoryImpl();

        }
        return userProfileRepository;
    }


    @Override
    public User getUserProfile() {

        return UserStorageManager.getInstance().getUser();

    }
}
