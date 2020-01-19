package valeriamoscoso.ioc.hanguldaebak.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

/**
 * Api rest interface with the petitions to get letters from the server
 * @author Valeria Moscoso León
 * */
public interface LettersApiRest {


    @Headers("Content-Type: application/json")
    @GET("api/hangul")
    Call<Letter[]> getAllLetters();

    @Headers("Content-Type: application/json")
    @GET("api/hangul/vowels")
    Call<Letter[]> getVowelLetters();

    @Headers("Content-Type: application/json")
    @GET("api/hangul/consonants")
    Call<Letter[]> getConsonantLetters();


}
