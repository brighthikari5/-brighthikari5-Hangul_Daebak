package valeriamoscoso.ioc.hanguldaebak.presentation.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters.StudyLettersActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.utils.Constants;

public class StudyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonVowels;
    private Button buttonConsonants;
    private Button buttonAllLetters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        bindViews();
        prepareViews();
    }

    private void bindViews(){
        buttonVowels = findViewById(R.id.buttonVowels);
        buttonConsonants = findViewById(R.id.buttonConsonants);
        buttonAllLetters = findViewById(R.id.buttonAll);
    }

    private void prepareViews(){
        buttonVowels.setOnClickListener(this);
        buttonConsonants.setOnClickListener(this);
        buttonAllLetters.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String type = "";

        switch (view.getId()){

            case R.id.buttonVowels:
                type = Constants.VOWEL_TYPE;

                break;

            case R.id.buttonConsonants:
                type = Constants.CONSONANT_TYPE;

                break;

            case R.id.buttonAll:
                type = Constants.ALL_TYPE;

                break;
        }

        //hacer intent
        Intent intent = new Intent(this, StudyLettersActivity.class);
        intent.putExtra("TYPE", type);
        startActivity(intent);


    }
}
