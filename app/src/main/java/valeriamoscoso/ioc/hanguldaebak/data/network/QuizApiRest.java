package valeriamoscoso.ioc.hanguldaebak.data.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizParamsDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.TeacherQuizDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserHistory;

/**
 * Api rest interface with the petition to send to server
 *
 * @author Valeria Moscoso León
 */
public interface QuizApiRest {

    @Headers("Content-Type: application/json")
    @POST("api/quizzes/save")
    Call<QuizResponse> postQuiz(@Body QuizParamsDTO quizParamsDTO);

    @Headers("Content-Type: application/json")
    @GET("api/students/history")
    Call<UserHistory[]> getUserHistory();

    @Headers("Content-Type: application/json")
    @GET("api/quizzes/{id}")
    Call<TeacherQuizDTO> getTeacherQuiz(@Path("id") int id);

}
