package valeriamoscoso.ioc.example.data.repository;

import java.io.IOException;

import retrofit2.Response;
import valeriamoscoso.ioc.example.data.network.ExampleApiRest;
import valeriamoscoso.ioc.example.data.network.ExampleRetrofitManager;
import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;
import valeriamoscoso.ioc.example.domain.repository.ExampleRepository;

public class ExampleRepositoryImpl implements ExampleRepository {

    private ExampleApiRest apiRest;

    private ExampleRepositoryImpl(){
        this.apiRest = ExampleRetrofitManager.buildRetrofitExampleAPI();
    }

    private static ExampleRepository exampleRepository = null;

    public static synchronized ExampleRepository getInstance() {
        if (exampleRepository == null) {
            return new ExampleRepositoryImpl();
        }
        return exampleRepository;
    }

    @Override
    public ExampleContainer getExampleList() {
        try {
            Response<ExampleContainer> response =  apiRest.getListExample().execute();
            ExampleContainer exampleContainer = response.body();
            return exampleContainer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExampleContainer(); //emptyList
    }

}
