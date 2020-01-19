package valeriamoscoso.ioc.hanguldaebak.presentation.quiz;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BaseView;

abstract class QuizPresenter {

    interface Presenter extends BasePresenter {

        void getQuestion();

        void setAnswer(int positionUserAnswer);
    }

    interface View extends BaseView {
        void onQuestionRecieved(Question question);

        void onQuizEnded(Quiz quiz);

        void showQuestionResolution(boolean isCorrect, String answer, String correctAnswer);

        void showLoading();
    }

}
