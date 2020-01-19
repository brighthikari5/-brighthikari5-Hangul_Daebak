package valeriamoscoso.ioc.hanguldaebak.presentation.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.data.entity.UserParams;
import valeriamoscoso.ioc.hanguldaebak.presentation.menu.MenuActivity;

/**
 * Sign in activity
 *
 * @author Valeria Moscoso León
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener, SignInPresenter.View {


    private SignInPresenter.Presenter presenter;


    //View Declaration
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPassword;
    private TextView textViewWarning;
    private Button buttonSend;

    private String messageErrorEmail = "Please enter a valid e-mail!";
    private String messageErrorName = "Please enter your name!";
    private String messageErrorSurname = "Please enter your surname!";
    private String messageErrorPassword = "Please enter a valid password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        presenter = new SignInPresenterImpl(this);
        bindViews();
        prepareViews();
    }

    private void bindViews() {

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPassword = findViewById(R.id.editPassword);
        textViewWarning = findViewById(R.id.textViewWarning);
        buttonSend = findViewById(R.id.buttonSend);
    }

    private void prepareViews() {
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.buttonSend) {

            String email = editTextEmail.getText().toString();
            String name = editTextName.getText().toString();
            String surname = editTextSurname.getText().toString();
            String password = editTextPassword.getText().toString();

            UserParams userParams = new UserParams(name, surname, password, email);

            SignInPresenterImpl.ValidatorState validatorState = presenter.validateParams(userParams);

            switch (validatorState){
                case MAIL_ERROR:
                    textViewWarning.setText(messageErrorEmail);
                    break;
                case NAME_ERROR:
                    textViewWarning.setText(messageErrorName);
                    break;
                case SURNAME_ERROR:
                    textViewWarning.setText(messageErrorSurname);
                    break;
                case PASSWORD_ERROR:
                    textViewWarning.setText(messageErrorPassword);
                    break;
                case ALL_GOOD:
                    presenter.signIn(userParams);
                    break;
            }

        }

    }

    @Override
    public void onSignInExecuted() {
        Intent intent = new Intent(this, MenuActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
