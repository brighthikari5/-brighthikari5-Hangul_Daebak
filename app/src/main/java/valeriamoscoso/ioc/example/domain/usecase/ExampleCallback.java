package valeriamoscoso.ioc.example.domain.usecase;

public interface ExampleCallback<T> {
    void onResult(T result);
    void onError(String e);
}
