package valeriamoscoso.ioc.hanguldaebak.presentation.quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

public class QuizActivity extends AppCompatActivity implements QuizPresenter.View, View.OnClickListener {

    private final static String IS_TEACHER_QUIZ = "IS_TEACHER_QUIZ";

    public static Intent createIntent(Context context, boolean isTeacherQuiz) {
        Intent intent = new Intent(context, QuizActivity.class);

        intent.putExtra(IS_TEACHER_QUIZ, isTeacherQuiz);

        return intent;
    }

    private QuizPresenter.Presenter presenter;

    private TextView tvQuestion;
    private Button buttonAnswer0;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private ConstraintLayout container;
    private ConstraintLayout loadingProgressLayout;

    private String messageSnackBar;
    private TextView textViewScore;
    private Button buttonGoBack;

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Boolean isTeacherQuiz = getIsTeacherQuizFromActivity();
        presenter = new QuizPresenterImpl(this, isTeacherQuiz);

        bindViews();
        prepareViews();

    }

    private Boolean getIsTeacherQuizFromActivity() {
        Intent intent = getIntent();
        return intent.getBooleanExtra(IS_TEACHER_QUIZ, false);
    }


    private void bindViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        buttonAnswer0 = findViewById(R.id.buttonAnswer0);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        container = findViewById(R.id.container);
        textViewScore = findViewById(R.id.textViewScore);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        loadingProgressLayout = findViewById(R.id.loadingProgressLayout);
        buttons = new Button[]{buttonAnswer0, buttonAnswer1, buttonAnswer2};
    }

    private void prepareViews() {

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        buttonGoBack.setOnClickListener(this);
    }


    @Override
    public void onQuestionRecieved(Question question) {

        enableDisableButtons(true);

        paintButtonsGray();

        Letter letterCorrect = question.getCorrectAnswer();
        String koreanString = letterCorrect.getLetter();

        String answer0 = question.getAnswers().get(0).getTranslation();
        String answer1 = question.getAnswers().get(1).getTranslation();
        String answer2 = question.getAnswers().get(2).getTranslation();

        //pintar preguntas

        tvQuestion.setText(koreanString);
        buttonAnswer0.setText(answer0);
        buttonAnswer1.setText(answer1);
        buttonAnswer2.setText(answer2);


    }

    private void enableDisableButtons(boolean onOff) {
        for (Button button : buttons) {
            button.setEnabled(onOff);
        }
    }

    private void paintButtonsGray() {
        for (Button button : buttons) {
            button.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void showLoading() {
        loadingProgressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onQuizEnded(Quiz quiz) {
        loadingProgressLayout.setVisibility(View.GONE);
        String messageScore = "Quiz Score:" + quiz.getScore();
        textViewScore.setText(messageScore);

        buttonGoBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void showQuestionResolution(boolean isCorrect, String answer, String correctAnswer) {

        paintResultOnButtons(isCorrect, answer, correctAnswer);

        if (isCorrect) {
            messageSnackBar = "YOUR ANSWER IS CORRECT";
        } else {
            messageSnackBar = "YOU ANSWER IS INCORRECT";
        }

        Snackbar.make(container, messageSnackBar, Snackbar.LENGTH_LONG).addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                presenter.getQuestion();
                super.onDismissed(transientBottomBar, event);
            }
        }).show();
    }

    private void paintResultOnButtons(boolean isCorrect, String answer, String correctAnswer) {


        if (isCorrect) {

            for (Button button : buttons) {
                if (button.getText().equals(correctAnswer)) {
                    button.setTextColor(Color.GREEN);
                }
            }

        } else {
            for (Button button : buttons) {
                if (button.getText().equals(answer)) {
                    button.setTextColor(Color.RED);
                }

                if (button.getText().equals(correctAnswer)) {
                    button.setTextColor(Color.GREEN);
                }
            }
        }

    }


    @Override
    public void onError(String errorMessage) {
        loadingProgressLayout.setVisibility(View.GONE);
        buttonGoBack.setVisibility(View.VISIBLE);
        Toast.makeText(this, "No teachers quiz available", Toast.LENGTH_LONG). show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonAnswer0:
                handleUserSelection(0);
                break;
            case R.id.buttonAnswer1:
                handleUserSelection(1);
                break;
            case R.id.buttonAnswer2:
                handleUserSelection(2);
                break;
            case R.id.buttonGoBack:
                finish();
                break;
        }
    }

    private void handleUserSelection(int position) {

        //desabilitar los botones para que no haga mas clicks mientras pasan los dos segundos
        enableDisableButtons(false);
        presenter.setAnswer(position);
    }

}
