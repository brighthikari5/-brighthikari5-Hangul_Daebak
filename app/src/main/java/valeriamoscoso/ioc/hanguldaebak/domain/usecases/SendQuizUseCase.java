package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.QuizRepositoryImpl;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

public class SendQuizUseCase extends AsyncTask<Quiz, String, Boolean> {

    private Callback<Boolean> callback;

    public SendQuizUseCase (Callback<Boolean> callback){
        this.callback=callback;
    }

    @Override
    protected Boolean doInBackground(Quiz... quiz) {
        return QuizRepositoryImpl.getInstance().sendQuiz(quiz[0]);
    }

    @Override
    protected void onPostExecute(Boolean isSuccessful ) {
        if (isSuccessful != null){
            callback.onResult(isSuccessful);
        }else {
            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }

    }
}
