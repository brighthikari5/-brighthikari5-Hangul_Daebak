package valeriamoscoso.ioc.hanguldaebak.data.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Response;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizParamsDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizResponse;
import valeriamoscoso.ioc.hanguldaebak.data.entity.TeacherQuizDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserHistory;
import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.network.QuizApiRest;
import valeriamoscoso.ioc.hanguldaebak.data.network.RetrofitManager;
import valeriamoscoso.ioc.hanguldaebak.data.repository.mapper.MapperDomainToDto;
import valeriamoscoso.ioc.hanguldaebak.data.repository.mapper.MapperDtoToDomain;
import valeriamoscoso.ioc.hanguldaebak.data.storage.UserStorageManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;
import valeriamoscoso.ioc.hanguldaebak.domain.repository.QuizRepository;

/**
 * Implementation of Quiz/Teacher Quiz repository, this class makes the petition to server to post a quiz
 *
 * @author Valeria Moscoso León
 */
public class QuizRepositoryImpl implements QuizRepository {

    private QuizApiRest quizApiRest;

    private QuizRepositoryImpl() {
        this.quizApiRest = RetrofitManager.getRetrofit().create(QuizApiRest.class);
    }


    private static QuizRepository quizRepository = null;

    //singleton
    public static synchronized QuizRepository getInstance() {

        if (quizRepository == null) {
            return new QuizRepositoryImpl();
        }
        return quizRepository;
    }

    @Override
    public Boolean sendQuiz(Quiz quiz) {

        try {

            User user = UserStorageManager.getInstance().getUser();

            QuizDTO quizDTO = MapperDomainToDto.mapperQuiz(quiz, user);

            QuizParamsDTO quizParamsDTO = new QuizParamsDTO(quizDTO);

            Response<QuizResponse> response = quizApiRest.postQuiz(quizParamsDTO).execute();

            return response.code() == ConectionUtils.CODE_200;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Quiz getTeacherQuiz() {
        try {
            Response<UserHistory[]> response = quizApiRest.getUserHistory().execute();
            UserHistory[] userHistoryResponse = response.body();

            if (userHistoryResponse != null) {
                List<UserHistory> userHistoryList = Arrays.asList(userHistoryResponse);


                int quizIdPending = findFirstQuizPendingId(userHistoryList);

                Response<TeacherQuizDTO> teacherQuizDTOResponse = quizApiRest.getTeacherQuiz(quizIdPending).execute();

                if (teacherQuizDTOResponse.body() != null) {
                    return MapperDtoToDomain.teacherQuizToQuiz(teacherQuizDTOResponse.body());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int findFirstQuizPendingId(List<UserHistory> userHistoryList) {
        boolean found = false;
        int i = 0;

        UserHistory userHistoryPending = null;

        do {

            UserHistory userHistory = userHistoryList.get(i);

            if (userHistory.getTo_do() == 1) {
                found = true;
                userHistoryPending = userHistory;
            }

            i++;
        } while (!found && i < userHistoryList.size());

        if (userHistoryPending != null) {
            return userHistoryPending.getQuiz_id();

        } else {
            throw new RuntimeException("Error finding new Pending quiz to do.");
        }
    }


}
