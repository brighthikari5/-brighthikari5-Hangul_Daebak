package valeriamoscoso.ioc.hanguldaebak.data.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Response;
import valeriamoscoso.ioc.hanguldaebak.data.network.LettersApiRest;
import valeriamoscoso.ioc.hanguldaebak.data.network.RetrofitManager;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.repository.LettersRepository;

/**
 * Implementation of Letters repository, this class makes the letters petition to get letters
 *
 * @author Valeria Moscoso León
 */
public class LettersRepositoryImpl implements LettersRepository {

    private LettersApiRest lettersApiRest;


    public LettersRepositoryImpl() {
        this.lettersApiRest = RetrofitManager.getRetrofit().create(LettersApiRest.class);
    }

    private static LettersRepository lettersRepository = null;

    //singleton
    public static synchronized LettersRepository getInstance() {
        if (lettersRepository == null) {
            return new LettersRepositoryImpl();
        }
        return lettersRepository;
    }

    @Override
    public List<Letter> allLetters() {

        try {
            Response<Letter[]> response = lettersApiRest.getAllLetters().execute();
            Letter[] letterResponse = response.body();

            if (letterResponse != null) {

                List<Letter> letterList = Arrays.asList(letterResponse);

                return letterList;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Letter> vowelLetters() {
        try {
            Response<Letter[]> response = lettersApiRest.getVowelLetters().execute();
            Letter[] letterResponse = response.body();

            if (letterResponse != null) {
                List<Letter> letterList = Arrays.asList(letterResponse);

                return letterList;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Letter> consonantLetters() {
        try {
            Response<Letter[]> response = lettersApiRest.getConsonantLetters().execute();
            Letter[] letterResponse = response.body();

            if (letterResponse != null) {
                List<Letter> letterList = Arrays.asList(letterResponse);

                return letterList;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
