package valeriamoscoso.ioc.example.presentation.example;

import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;
import valeriamoscoso.ioc.example.domain.usecase.ExampleCallback;
import valeriamoscoso.ioc.example.domain.usecase.ExampleUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;

public class ExamplePresenterImpl implements ExamplePresenterContract.Presenter {

    private ExampleUseCase exampleUseCase;
    private ExamplePresenterContract.View view;

    public ExamplePresenterImpl(ExamplePresenterContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        //todo preparation if required. Create instance of your member variables
    }

    @Override
    public void getExampleList() {
        exampleUseCase = new ExampleUseCase(new ExampleCallback<ExampleContainer>() {
            @Override
            public void onResult(final ExampleContainer result) {
                view.onExampleListReceived(result);
            }

            @Override
            public void onError(String message) {
                view.onError(message);
            }
        });
        exampleUseCase.execute();
    }

    /**
     * Call this method on onDestroy() of your Activity/Fragment.
     * Stop any running Async task here.
     */
    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(exampleUseCase);
    }
}
