package valeriamoscoso.ioc.example.presentation.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;
import valeriamoscoso.ioc.example.presentation.example.adapter.ExampleAdapter;
import valeriamoscoso.ioc.hanguldaebak.R;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener, ExamplePresenterContract.View {


    private Button exampleButton;
    private ExamplePresenterContract.Presenter presenter;
    private ExampleAdapter exampleAdapter;
    private RecyclerView exampleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ExamplePresenterImpl(this);
        bindViews();
        prepareViews();
    }

    private void bindViews() {
        exampleRecyclerView = findViewById(R.id.exampleRecyclerView);
        exampleButton = findViewById(R.id.exampleButton);
    }

    private void prepareViews() {
        exampleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exampleButton:
                presenter.getExampleList();
                break;
        }
    }

    @Override
    public void onExampleListReceived(ExampleContainer exampleContainer) {
        initializeAdapter();
        exampleAdapter.setData(exampleContainer);
    }

    private void initializeAdapter() {
        if(exampleRecyclerView.getAdapter() == null) {
            exampleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            exampleAdapter = new ExampleAdapter(this);
            exampleRecyclerView.setAdapter(exampleAdapter);
        }
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
