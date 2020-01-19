package valeriamoscoso.ioc.hanguldaebak.presentation.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;

public class UserProfileActivity extends AppCompatActivity implements UserProfilePresenter.View{

    private TextView profileNameResponese;
    private TextView profileSurnameResponse;
    private TextView profileMailResponse;
    private TextView profileCreationResponse;

    private UserProfilePresenter.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        presenter = new UserProfilePresenterImpl(this);
        presenter.getUserProfile();
        bindViews();
    }

    private void bindViews(){
        profileNameResponese = findViewById(R.id.profileNameResponse);
        profileSurnameResponse = findViewById(R.id.profileSurnameResponse);
        profileMailResponse = findViewById(R.id.profileMailResponse);
        profileCreationResponse = findViewById(R.id.profileCreationResponse);

    }

    @Override
    public void onUserProfileReceived(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String mail = user.getEmail();
        String dateCreated = user.getCreated_at();

        profileNameResponese.setText(name);
        profileSurnameResponse.setText(surname);
        profileMailResponse.setText(mail);
        profileCreationResponse.setText(dateCreated);
    }

    @Override
    public void onError(String errorMessage) {

    }
}
