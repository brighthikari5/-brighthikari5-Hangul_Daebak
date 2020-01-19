package valeriamoscoso.ioc.example.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;

public interface ExampleApiRest {

    @GET("/examples")
    Call<ExampleContainer> getListExample();
}
