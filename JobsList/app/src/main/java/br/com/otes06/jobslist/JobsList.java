package br.com.otes06.jobslist;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class JobsList extends Activity {
    public static final String TAG = "JobsList";

    public static final String AUTHORITY = "br.com.otes06.jobslist.provider";
    public static final String ACCOUNT_TYPE = "otes02.herokuapp.com";
    public static final String ACCOUNT = "dummyaccount";
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = CreateSyncAccount(this);

        setContentView(R.layout.activity_jobs_list);

        Button buttonListaDeTarefas = (Button) findViewById(R.id.buttonListaDeTarefas);
        buttonListaDeTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "buttonListaDeTarefas");
                Intent intent = new Intent(v.getContext(), ListaDeTarefas.class);
                startActivity(intent);
            }
        });

        Button buttonRefreshData = (Button) findViewById(R.id.buttonRefreshData);
        buttonRefreshData.setOnClickListener(onRefreshDataButtonClick);
    }

    public static Account CreateSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);

        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            Log.i(TAG, "newAccount");
            return newAccount;
        } else {
            Log.i(TAG, "nullAccount");
            return null;
        }
    }

    View.OnClickListener onRefreshDataButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(TAG, "onRefreshDataButtonClick");
            Bundle settingsBundle = new Bundle();
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
            ContentResolver.requestSync(account, AUTHORITY, settingsBundle);
        }
    };

}
