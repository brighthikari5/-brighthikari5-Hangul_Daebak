package valeriamoscoso.ioc.hanguldaebak.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import valeriamoscoso.ioc.hanguldaebak.data.storage.TokenStorageManager;
import valeriamoscoso.ioc.hanguldaebak.presentation.menu.MenuActivity;
import valeriamoscoso.ioc.hanguldaebak.presentation.logIn.LogInActivity;

public class SplashActivity extends AppCompatActivity {

    private static  Long DELAY = 1000L;

    private static Boolean hasBeenPaused = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeMainWithDelay();
    }

    private void executeMainWithDelay(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!hasBeenPaused){
                    //Open Activity depending on token state
                    openNextActivityLogic();
                    finish();
                }
            }
        }, DELAY);

    }

    public void openNextActivityLogic (){

        if (TokenStorageManager.getInstance().hasToken()){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }else{
            Intent intent2 = new Intent(this, LogInActivity.class);
            startActivity(intent2);
        }

    }


    public void onStop(){

        super.onStop();
        hasBeenPaused=true;
    }

    public void onPause(){
        super.onPause();
        hasBeenPaused= true;

    }

    public void onResume(){

        super.onResume();
        if(hasBeenPaused){
            hasBeenPaused = false;
            DELAY = 0L;
            openNextActivityLogic();
        }
    }

}
