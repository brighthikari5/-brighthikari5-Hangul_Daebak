package valeriamoscoso.ioc.example.data.network;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import valeriamoscoso.ioc.hanguldaebak.BuildConfig;

class ExampleCustomOkHttpClient {

    private static final Integer NUM_RETRIES = 3;
    private static final Long TIMEOUT_CONNECTION_VALUE = 10L;
    private static final Long TIMEOUT_READ_VALUE = 10L;
    private static final Long TIMEOUT_WRITE_VALUE = 10L;

    static OkHttpClient get() {
        try {
            return new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Request request = chain.request();
                            Response response = chain.proceed(request);

                            int tryCount = 0;

                            while (!response.isSuccessful() && tryCount < NUM_RETRIES) {
                                tryCount++;
                                response = chain.proceed(request);
                            }

                            return response;
                        }
                    })
                    .addInterceptor(getLogInterceptor())
                    .connectTimeout(TIMEOUT_CONNECTION_VALUE, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_WRITE_VALUE, TimeUnit.SECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    })
                    .build();
        } catch (Exception e) {
            Log.e("Error", "CustomOkHttpClient", e);
            return new OkHttpClient.Builder().build();
        }
    }

    private static Interceptor getLogInterceptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }
}
