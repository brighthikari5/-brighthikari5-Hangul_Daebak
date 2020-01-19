package valeriamoscoso.ioc.hanguldaebak.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import valeriamoscoso.ioc.example.data.network.server.MockServer;

/**
 * Class to build retrofit
 * @author Valeria Moscoso León
 * */
public class RetrofitManager {

    private static final boolean USE_MOCKED_SERVER = false;
    private static final String BASE_HOST = "https://work.maximilianofernandez.net";

    public static Retrofit getRetrofit(){
        if(USE_MOCKED_SERVER){
            return buildRetrofitMock();
        }else{
            return buildRetrofit();
        }
    }

    private static Retrofit buildRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_HOST)
                .client(CustomOkHttpClient.get())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private static Retrofit buildRetrofitMock() {

        return new Retrofit.Builder()
                .baseUrl(MockServer.getUrl())
                .client(CustomOkHttpClient.get())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
