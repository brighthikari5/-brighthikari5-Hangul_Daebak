package valeriamoscoso.ioc.hanguldaebak.presentation.quiz;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.Callback;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.CreateQuizAllLettersUseCase;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.GetTeacherQuizUseCase;
import valeriamoscoso.ioc.hanguldaebak.domain.usecases.SendQuizUseCase;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.AsyncTaskUtils;

public class QuizPresenterImpl implements QuizPresenter.Presenter {

    private GetTeacherQuizUseCase getTeacherQuizUseCase;
    private CreateQuizAllLettersUseCase createQuizAllLettersUseCase;
    private SendQuizUseCase sendQuizUseCase;
    private QuizPresenter.View view;

    private Quiz quiz;

    private int iteratorQuestions = 0;

    public QuizPresenterImpl(QuizPresenter.View view, Boolean isTeacherQuiz) {
        this.view = view;

        if (isTeacherQuiz) {
            getTeacherQuiz();
        } else {
            getQuiz();
        }
    }

    private void getTeacherQuiz() {


        getTeacherQuizUseCase = new GetTeacherQuizUseCase(new Callback<Quiz>() {

            @Override
            public void onResult(Quiz quiz) {
                if (quiz != null) {
                    setQuiz(quiz);
                } else {
                    view.onError("Error General Servidor");
                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);
            }
        });

        getTeacherQuizUseCase.execute();

    }

    private void getQuiz() {

        createQuizAllLettersUseCase = new CreateQuizAllLettersUseCase(new Callback<Quiz>() {

            @Override
            public void onResult(Quiz quiz) {
                if (quiz != null) {
                    setQuiz(quiz);
                } else {
                    view.onError("Error General Servidor");
                }
            }

            @Override
            public void onError(String e) {
                view.onError(e);
            }
        });

        //enviamos la cantidad de preguntas al useCase
        createQuizAllLettersUseCase.execute(10);
    }

    private void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        getQuestion();
    }


    @Override
    public void getQuestion() {

        if (iteratorQuestions < quiz.getQuestions().size()) {

            Question question = quiz.getQuestions().get(iteratorQuestions);

            view.onQuestionRecieved(question);
        } else {

            view.showLoading();
            sendQuizUseCase();

        }
    }

    private void sendQuizUseCase() {

        setQuizScore();

        sendQuizUseCase = new SendQuizUseCase(new Callback<Boolean>() {
            @Override
            public void onResult(Boolean result) {
                view.onQuizEnded(quiz);//para mostrar luego tot el quiz quan haya terminado
            }

            @Override
            public void onError(String e) {
                view.onError(e);
            }
        });

        sendQuizUseCase.execute(quiz);
    }

    private void setQuizScore() {
        for (Question question : quiz.getQuestions()) {
            if (question.isResponseCorrect()) {
                int score = quiz.getScore();
                quiz.setScore(score + 10);
            }
        }
    }

    @Override
    public void setAnswer(int positionUserAnswer) {

        Question question = quiz.getQuestions().get(iteratorQuestions);

        Letter letter = question.getAnswers().get(positionUserAnswer);

        question.setUserSelection(letter);

        boolean result = letter.getLetter().equals(question.getCorrectAnswer().getLetter());

        question.setResponseCorrect(result);

        view.showQuestionResolution(result, letter.getTranslation(), question.getCorrectAnswer().getTranslation());

        iteratorQuestions++;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onPause() {
        AsyncTaskUtils.onPauseAsyncTask(createQuizAllLettersUseCase);
        AsyncTaskUtils.onPauseAsyncTask(sendQuizUseCase);
    }

}
