package valeriamoscoso.ioc.hanguldaebak.presentation.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.data.storage.TokenStorageManager;
import valeriamoscoso.ioc.hanguldaebak.presentation.logIn.LogInActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.menuQuiz.MenuQuizActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.menuQuizTeacher.MenuQuizTeacherActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.profile.UserProfileActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.study.StudyActivity;

/**
 * Menu activity
 *
 * @author Valeria Moscoso León
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStudy;
    private Button buttonQuiz;
    private Button buttonQuizTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        bindViews();
        prepareViews();
    }

    private void bindViews(){

        buttonStudy = findViewById(R.id.buttonStudy);
        buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonQuizTeacher = findViewById(R.id.buttonQuizTeacher);
    }

    private void prepareViews(){

        buttonStudy.setOnClickListener(this);
        buttonQuiz.setOnClickListener(this);
        buttonQuizTeacher.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOut:

                TokenStorageManager.getInstance().deleteToken();
                Intent intent = new Intent(this, LogInActivity.class);
                finish();
                startActivity(intent);
                return true;

            case R.id.profile:
                Intent intent1 = new Intent(this, UserProfileActivity.class);
                startActivity(intent1);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonStudy:
                Intent intent = new Intent(this, StudyActivity.class);
                startActivity(intent);

                break;

            case R.id.buttonQuiz:
                Intent intent1 = new Intent(this, MenuQuizActivity.class);
                startActivity(intent1);

                break;

            case R.id.buttonQuizTeacher:
                Intent intent2 = new Intent(this, MenuQuizTeacherActivity.class);
                startActivity(intent2);
                break;



        }

    }
}
