package valeriamoscoso.ioc.hanguldaebak.domain.repository;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;

/**
 * Login repository interface with the declaration of the possible server responses
 * @author Valeria Moscoso León
 * */
public interface LoginRepository {
    Boolean signIn(UserParams userParam);

    Boolean logIn(UserParamsLogIn userParamsLogIn);
}
