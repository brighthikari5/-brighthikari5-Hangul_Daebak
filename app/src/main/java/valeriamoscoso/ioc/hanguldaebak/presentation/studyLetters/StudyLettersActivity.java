package valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters.adapter.LettersAdapter;

public class StudyLettersActivity extends AppCompatActivity implements StudyLettersPresenter.View {

    private List<Letter> lettes = new ArrayList<>();
    private StudyLettersPresenter.Presenter presenter;
    private LettersAdapter lettersAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_letters);
        presenter = new StudyLettersPresenterImpl(this);

        recyclerView = findViewById(R.id.recyclerViewLetters);
        String type = getTypeFromActivity();
        presenter.getLettersByType(type);

    }

    private String getTypeFromActivity() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
        return type;
    }


    @Override
    public void onLettersReceived(List<Letter> letters) {
        //recycler view

        instantiateAdapter();
        lettersAdapter.setData(letters);

    }

    @Override
    public void onError(String errorMessage) {

    }

    public void instantiateAdapter() {

        if (lettersAdapter == null) {
            lettersAdapter = new LettersAdapter(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
        recyclerView.setAdapter(lettersAdapter);

    }
}
