package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.LettersRepositoryImpl;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.domain.utils.QuizUtils;

/**
 * CreateQuizUseCase with the async task to make the petition to get all the letters and create a quiz.
 *
 * @author Valeria Moscoso León
 */

public class CreateQuizAllLettersUseCase extends AsyncTask<Integer, Void, Quiz> {

    private Callback<Quiz> callback;

    public CreateQuizAllLettersUseCase(Callback<Quiz> callback) {
        this.callback = callback;
    }

    @Override
    protected Quiz doInBackground(Integer... quantity) {

        List<Letter> allLetters = LettersRepositoryImpl.getInstance().allLetters();// todas las letras

        List<Letter> questionsLetterList = QuizUtils.getRandomLettersForQuiz(allLetters, quantity[0]); //son las 10 preguntas


        return QuizUtils.createQuiz(questionsLetterList, allLetters);
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
