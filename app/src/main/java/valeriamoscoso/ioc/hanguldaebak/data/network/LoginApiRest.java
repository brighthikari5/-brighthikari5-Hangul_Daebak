package valeriamoscoso.ioc.hanguldaebak.data.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import valeriamoscoso.ioc.hanguldaebak.data.entity.TokenResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;

/**
 * Api rest interface with the petitions to signin/login to server
 * @author Valeria Moscoso León
 * */
public interface LoginApiRest {

    @Headers("Content-Type: application/json")
    @POST("/api/register")
    Call<TokenResponse> userSignIn(@Body UserParams userParams);

    @Headers("Content-Type: application/json")
    @POST("/api/login")
    Call<TokenResponse> userLogIn(@Body UserParamsLogIn userParamsLogIn);


}
