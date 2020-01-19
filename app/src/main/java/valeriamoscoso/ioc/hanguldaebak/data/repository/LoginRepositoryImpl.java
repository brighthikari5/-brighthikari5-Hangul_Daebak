package valeriamoscoso.ioc.hanguldaebak.data.repository;

import java.io.IOException;

import retrofit2.Response;
import valeriamoscoso.ioc.hanguldaebak.data.entity.ProfileResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.StudentResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.TokenResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;
import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.network.LoginApiRest;
import valeriamoscoso.ioc.hanguldaebak.data.network.ProfileApiRest;
import valeriamoscoso.ioc.hanguldaebak.data.network.RetrofitManager;
import valeriamoscoso.ioc.hanguldaebak.data.storage.TokenStorageManager;
import valeriamoscoso.ioc.hanguldaebak.data.storage.UserStorageManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;
import valeriamoscoso.ioc.hanguldaebak.domain.repository.LoginRepository;


/**
 * Implementation of login repository, this class makes the login petition and signin/login petition.
 *
 * @author Valeria Moscoso León
 */
public class LoginRepositoryImpl implements LoginRepository {

    private LoginApiRest loginApiRest;
    private ProfileApiRest profileApiRest;

    private LoginRepositoryImpl() {
        this.loginApiRest = RetrofitManager.getRetrofit().create(LoginApiRest.class);
        this.profileApiRest = RetrofitManager.getRetrofit().create(ProfileApiRest.class);

    }

    private static LoginRepository loginRepository = null;

    //singleton
    public static synchronized LoginRepository getInstance() {
        if (loginRepository == null) {
            return new LoginRepositoryImpl();
        }
        return loginRepository;
    }

    @Override
    public Boolean signIn(UserParams userParam) {
        try {
            Response<TokenResponse> response = loginApiRest.userSignIn(userParam).execute();
            TokenResponse tokenResponse = response.body();

            if (tokenResponse != null) {
                TokenStorageManager.getInstance().saveToken(tokenResponse.getToken());
                getAndSaveUserProfile();
            }

            return response.code() == ConectionUtils.CODE_201;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean logIn(UserParamsLogIn userParamsLogIn) {

        try {
            Response<TokenResponse> response = loginApiRest.userLogIn(userParamsLogIn).execute();
            TokenResponse tokenResponse = response.body();

            if (tokenResponse != null) {
                TokenStorageManager.getInstance().saveToken(tokenResponse.getToken());
                getAndSaveUserProfile();
            }

            return response.code() == ConectionUtils.CODE_200;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getAndSaveUserProfile() throws IOException {
        Response<ProfileResponse> responseUser = profileApiRest.getUserProfile().execute();

        ProfileResponse profileResponse = responseUser.body();

        Response<StudentResponse> responseStudent = profileApiRest.getStudentId().execute();

        StudentResponse studentResponse = responseStudent.body();

        if (profileResponse != null && studentResponse != null) {

            User user = profileResponse.getUser();

            if(studentResponse.getId() != -1) {

                user.setStudentId(studentResponse.getId());
            }

            UserStorageManager.getInstance().saveUser(user);

        } else {
            throw new RuntimeException("Error missing user cannot login or sign in.");
        }

    }
}
