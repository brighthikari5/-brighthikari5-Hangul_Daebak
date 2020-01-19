package valeriamoscoso.ioc.example.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import valeriamoscoso.ioc.example.data.network.server.MockServer;

public class ExampleRetrofitManager {

    private static final String EXAMPLE_BASE_HOST = MockServer.getUrl();

    public static ExampleApiRest buildRetrofitExampleAPI() {

        return new Retrofit.Builder()
                .baseUrl(EXAMPLE_BASE_HOST)
                .client(ExampleCustomOkHttpClient.get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExampleApiRest.class);
    }
}
