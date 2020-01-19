package valeriamoscoso.ioc.hanguldaebak.data.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import valeriamoscoso.ioc.hanguldaebak.BuildConfig;
import valeriamoscoso.ioc.hanguldaebak.data.storage.TokenStorageManager;

/**
 * @author Valeria Moscoso León
 * this class interceps the request and adds token to the heather
 * */

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder newBuilder = chain.request().newBuilder();

        newBuilder.addHeader("Authorization", "Bearer " + TokenStorageManager.getInstance().getToken());

        Request request = newBuilder.build();

        if(BuildConfig.DEBUG){
            Log.d("RequestInterceptor","URL -> " + request.url());
        }

        return chain.proceed(request);
    }
}
