package valeriamoscoso.ioc.hanguldaebak.domain.usecases;

/**
 * Callback interface with the declaration of the methods
 * @author Valeria Moscoso León
 * */
public interface Callback<T> {
    void onResult(T result);
    void onError(String e);
}
