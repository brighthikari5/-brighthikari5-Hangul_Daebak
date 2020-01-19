package valeriamoscoso.ioc.hanguldaebak.presentation.menuQuizTeacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.presentation.quiz.QuizActivity;

public class MenuQuizTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quiz_teacher);

        Button quizButton = findViewById(R.id.buttonStartQuizTeacher);
        quizButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonStartQuizTeacher){
            startActivity(QuizActivity.createIntent(this, true ));

        }
    }

}
