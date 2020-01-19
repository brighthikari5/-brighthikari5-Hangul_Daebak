package valeriamoscoso.ioc.hanguldaebak.presentation;

import android.app.Application;

import valeriamoscoso.ioc.example.data.network.server.MockServer;
import valeriamoscoso.ioc.hanguldaebak.data.repository.SharedPreferencesManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MockServer mockServer = new MockServer(this);
        mockServer.start();

        SharedPreferencesManager.initManager(getApplicationContext());
    }
}
