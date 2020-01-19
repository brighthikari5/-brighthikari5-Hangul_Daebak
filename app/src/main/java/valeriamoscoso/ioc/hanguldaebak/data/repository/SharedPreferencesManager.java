package valeriamoscoso.ioc.hanguldaebak.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {

    private static SharedPreferencesManager manager;

    private static SharedPreferences sharedPreferences;

    public static synchronized SharedPreferencesManager getInstance() {

        if (sharedPreferences == null) {
            throw new RuntimeException("Please initManager before using it");
        }

        if (manager == null) {
            return new SharedPreferencesManager();
        }

        return manager;
    }

    public static void initManager(Context context) {
        sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        manager = new SharedPreferencesManager();
    }

    public void saveString(String KEY, String value) {
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(KEY, value);
        myEdit.apply();
    }

    public String getString(String KEY) {
        return sharedPreferences.getString(KEY, "");
    }
}
