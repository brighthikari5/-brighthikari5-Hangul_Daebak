package valeriamoscoso.ioc.example.domain.usecase;

import android.os.AsyncTask;

import valeriamoscoso.ioc.example.data.repository.ExampleRepositoryImpl;
import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;

public class ExampleUseCase extends AsyncTask<Void, String, ExampleContainer> {

    ExampleCallback<ExampleContainer> callback;

    public ExampleUseCase(ExampleCallback<ExampleContainer> callback){
        this.callback = callback;
    }

    @Override
    protected ExampleContainer doInBackground(Void... params) {

        ExampleContainer exampleList = new ExampleContainer();

        try {

            exampleList = ExampleRepositoryImpl.getInstance().getExampleList();

        } catch (Exception e) {
            e.printStackTrace();
            publishProgress(e.getMessage());
        } finally {

            if(exampleList.getExamples().isEmpty()){
                publishProgress("Empty List!");
            }
        }

        return exampleList;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        callback.onError(values[0]);
    }

    @Override
    protected void onPostExecute(ExampleContainer result) {
        if(result != null) {
            callback.onResult(result);
        }
    }
}