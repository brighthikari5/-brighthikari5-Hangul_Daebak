package valeriamoscoso.ioc.hanguldaebak.presentation.logIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParamsLogIn;
import valeriamoscoso.ioc.hanguldaebak.presentation.menu.MenuActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.signIn.SignInActivity;

/**
 * Login activity
 * @author Valeria Moscoso León
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener, LogInPresenter.View {

    private LogInPresenter.Presenter presenter;

    //View Declaration
    private Button loginButton;
    private Button signInButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewWarningLogin;

    //Error Messages
    private String messageErrorEmail = "Please enter a valid email!";
    private String messageErrorPassword = "Not a valid password! Password must contain at least 8 letters, " +
            "one lowecase letter, one uppercase letter and one number!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LogInPresenterImpl(this);
        bindViews();
        prepareViews();
    }

    private void bindViews() {
        loginButton = findViewById(R.id.buttonLogIn);
        signInButton = findViewById(R.id.buttonSignIn);
        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);
        textViewWarningLogin = findViewById(R.id.textViewWarninglogin);
    }

    private void prepareViews() {
        loginButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonLogIn:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                UserParamsLogIn userParamsLogIn = new UserParamsLogIn(email, password);
                LogInPresenterImpl.ValidatorState validatorState = presenter.validateParams(userParamsLogIn);

                switch (validatorState){
                    case MAIL_ERROR:
                        textViewWarningLogin.setText(messageErrorEmail);
                        break;
                    case PASSWORD_ERROR:
                        textViewWarningLogin.setText(messageErrorPassword);
                        break;
                    case ALL_GOOD:
                        presenter.logIn(userParamsLogIn);
                }

        }
    }


    @Override
    public void onLogInExecuted() {
        Intent intent = new Intent(this, MenuActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
