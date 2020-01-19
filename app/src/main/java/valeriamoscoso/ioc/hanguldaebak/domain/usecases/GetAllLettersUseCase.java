package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

import android.os.AsyncTask;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;
import valeriamoscoso.ioc.hanguldaebak.data.repository.LettersRepositoryImpl;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

/**
 * GetAllLettersUseCase with the async task to make the petition to get all the letters.
 * @author Valeria Moscoso León
 * */

public class GetAllLettersUseCase extends AsyncTask< Void, Void, List<Letter>> {

    private Callback<List<Letter>> callback;

    public GetAllLettersUseCase(Callback<List<Letter>> callback) {
        this.callback = callback;
    }

    @Override
    protected List<Letter> doInBackground(Void... voids) {
        return LettersRepositoryImpl.getInstance().allLetters();




    }

    @Override
    protected void onPostExecute(List<Letter> letters) {
        if (letters !=null){
            callback.onResult(letters);
        }else {

            callback.onError(ConectionUtils.DEFAULT_ERROR_MESSAGE);
        }

    }
}
