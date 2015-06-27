package br.com.otes06.jobslist;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import br.com.otes06.jobslist.GatewayRealm.Realms.AlarmeRealm;
import br.com.otes06.jobslist.GatewayRealm.Realms.GrupoRealm;
import br.com.otes06.jobslist.GatewayRealm.Realms.TarefaRealm;
import io.realm.Realm;


public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    public static final String TAG = "MainActivity";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    public static final String AUTHORITY = "br.com.otes06.jobslist.provider";
    public static final String ACCOUNT_TYPE = "otes02.herokuapp.com";
    public static final String ACCOUNT = "dummyaccount";
    Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = CreateSyncAccount(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ListaDeTarefasFragment.newInstance();
                break;
            case 1:
                fragment = ListaDeGruposFragment.newInstance();
                break;
            case 2:
                Toast.makeText(this, "Sincronizando", Toast.LENGTH_SHORT).show();
                sync();
                break;
            case 3:
                Toast.makeText(this, "Drop DB", Toast.LENGTH_SHORT).show();
                dropTarefas();
                break;
        }


        if (fragment != null)
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_activity_lista_de_tarefas);
                break;
            case 2:
                mTitle = getString(R.string.title_activity_lista_de_grupos);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            int position = mNavigationDrawerFragment.getmCurrentSelectedPosition();
            switch (position){
                case 0:
                    getMenuInflater().inflate(R.menu.menu_lista_de_tarefas, menu);
                    break;
                case 1:
                    getMenuInflater().inflate(R.menu.menu_lista_de_grupos, menu);
                    break;
                default:
                    getMenuInflater().inflate(R.menu.main, menu);
                    break;
            }

            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (item.getItemId() == R.id.action_nova_tarefa) {
            Intent intent = new Intent(this, EdicaoTarefaActivity.class);
            intent.putExtra("tarefaID", 0);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dropTarefas() {
        Realm realm = Realm.getInstance(this.getBaseContext());
        realm.beginTransaction();
        realm.clear(AlarmeRealm.class);
        realm.clear(TarefaRealm.class);
        realm.clear(GrupoRealm.class);
        realm.commitTransaction();
        realm.close();
    }

    private void sync() {
        Log.i(TAG, "onSyncClick");
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(account, AUTHORITY, settingsBundle);
    }

}
