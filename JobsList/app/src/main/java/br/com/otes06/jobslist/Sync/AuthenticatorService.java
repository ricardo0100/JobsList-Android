package br.com.otes06.jobslist.Sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class AuthenticatorService extends Service {
    public static final String TAG = "AuthenticatorService";

    private Authenticator authenticator;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        authenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return authenticator.getIBinder();
    }
}
