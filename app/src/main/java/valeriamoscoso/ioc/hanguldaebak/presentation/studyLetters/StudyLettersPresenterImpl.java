package valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.Callback;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.GetAllConsonantsUseCase;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.GetAllLettersUseCase;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.GetAllVowelsUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;

/**
 * Implementation of Presenter to get all letters, consonants and vowels
 *
 * @author Valeria Moscoso León
 */
public class StudyLettersPresenterImpl implements StudyLettersPresenter.Presenter {

    private GetAllLettersUseCase getAllLettersUseCase;
    private GetAllVowelsUseCase getAllVowelsUseCase;
    private GetAllConsonantsUseCase getAllConsonantsUseCase;

    private StudyLettersPresenter.View view;

    public StudyLettersPresenterImpl(StudyLettersPresenter.View view) {
        this.view = view;
    }



    private void getALlLetters() {

        getAllLettersUseCase = new GetAllLettersUseCase(new Callback<List<Letter>>() {
            @Override
            public void onResult(List<Letter> result) {
                if (result != null) {
                    view.onLettersReceived(result);
                } else {
                    view.onError("Error General Servidor");
                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);
            }
        });
        getAllLettersUseCase.execute();
    }


    private void getVowels() {
        getAllVowelsUseCase = new GetAllVowelsUseCase(new Callback<List<Letter>>() {
            @Override
            public void onResult(List<Letter> result) {
                if (result != null) {
                    view.onLettersReceived(result);
                } else {
                    view.onError("Error General Servidor");
                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);
            }
        });
        getAllVowelsUseCase.execute();
    }

    private void getConsonants() {
        getAllConsonantsUseCase = new GetAllConsonantsUseCase(new Callback<List<Letter>>() {
            @Override
            public void onResult(List<Letter> result) {
                if(result != null){
                    view.onLettersReceived(result);

                }else{
                    view.onError("Error General Servidor");


                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);

            }
        });
        getAllConsonantsUseCase.execute();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(getAllLettersUseCase, getAllVowelsUseCase, getAllConsonantsUseCase);

    }

    @Override
    public void getLettersByType(String type) {
        switch (type){

            case "VOWEL_TYPE":

                getVowels();
                break;

            case "CONSONANT_TYPE":
                getConsonants();
                break;

            case "ALL_TYPE":
                getALlLetters();
                break;
        }

    }
}
