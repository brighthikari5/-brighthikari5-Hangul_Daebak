package valeriamoscoso.ioc.example.presentation.example;

import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;

abstract class ExamplePresenterContract {

    interface Presenter extends BasePresenter {
        void getExampleList();
    }

    interface View {
        void onExampleListReceived(ExampleContainer exampleContainer);
        void onError(String errorMessage);
    }
}
