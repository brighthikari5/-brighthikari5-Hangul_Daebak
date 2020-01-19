package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.QuizRepositoryImpl;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

/**
 * GetUserHistoryUseCase with the async task to make the petition to get user history.
 *
 * @author Valeria Moscoso León
 */
public class GetTeacherQuizUseCase extends AsyncTask<Void, Void, Quiz> {


    private Callback<Quiz> callback;

    public GetTeacherQuizUseCase(Callback<Quiz> callback) {
        this.callback = callback;
    }

    @Override
    protected Quiz doInBackground(Void... voids) {
        return QuizRepositoryImpl.getInstance().getTeacherQuiz();
    }

    @Override
    protected void onPostExecute(Quiz quiz) {
        if (quiz != null) {
            callback.onResult(quiz);

        } else {
            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }
    }
}
