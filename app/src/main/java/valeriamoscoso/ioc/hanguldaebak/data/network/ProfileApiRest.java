package valeriamoscoso.ioc.hanguldaebak.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import valeriamoscoso.ioc.hanguldaebak.data.entity.ProfileResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.StudentResponse;

/**
 * Api rest interface with the petitions to get User information from server
 * @author Valeria Moscoso León
 * */
public interface ProfileApiRest {

    @Headers("Content-Type: application/json")
    @GET ("api/profile")
    Call<ProfileResponse> getUserProfile();


    @Headers("Content-Type: application/json")
    @GET ("api/students")
    Call<StudentResponse> getStudentId();

}
