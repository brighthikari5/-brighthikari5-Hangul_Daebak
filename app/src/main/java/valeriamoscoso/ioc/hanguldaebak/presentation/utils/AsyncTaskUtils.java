package valeriamoscoso.ioc.hanguldaebak.presentation.utils;

import android.os.AsyncTask;

/**
 * Async task utils
 * @author Valeria Moscoso León
 * */
public class AsyncTaskUtils {
//    public static void onPauseAsyncTask(AsyncTask asyncTask){
//        if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
//            asyncTask.cancel(true);
//        }
//    }


    public static void onPauseAsyncTask(AsyncTask... asyncTaskList){
        for (AsyncTask asyncTask: asyncTaskList) {
            if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
                asyncTask.cancel(true);
            }
        }
    }
}
