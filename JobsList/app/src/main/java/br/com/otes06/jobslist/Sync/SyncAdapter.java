package br.com.otes06.jobslist.Sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import br.com.otes06.jobslist.GatewayRealm.Realms.TarefaRealm;

import io.realm.Realm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String TAG = "SyncAdapter";

    ContentResolver contentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        Log.i(TAG, "Constructor");
        contentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        Log.i(TAG, "Constructor");
        contentResolver = context.getContentResolver();
    }


    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.i(TAG, "onPerformSync");
        Realm realm = Realm.getInstance(getContext());

        realm.beginTransaction();
        realm.clear(TarefaRealm.class);
        realm.commitTransaction();

        try {
            String response = HttpRequest.get("http://otes02.herokuapp.com/api-rest/tarefas/").body();
            JSONObject json = new JSONObject(response);
            JSONArray results = json.optJSONArray("results");

            if (results == null) {
                realm.close();
                return;
            }
            realm.beginTransaction();

            Log.i(TAG, "GET response: " + response);
            Log.i(TAG, "GET json: " + results.toString());
            Log.i(TAG, "GET json len: " + results.length());

            int len = results.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonRegistro = results.getJSONObject(i);
                Log.i(TAG, "Obj id: " + jsonRegistro.getInt("id"));
                Log.i(TAG, "Obj titulo: " + jsonRegistro.getString("titulo"));
                Log.i(TAG, "Obj descricao: " + jsonRegistro.getString("descricao"));
                Log.i(TAG, "Obj concluida: " + jsonRegistro.getBoolean("concluida"));

                TarefaRealm tarefa = realm.createObject(TarefaRealm.class);
                tarefa.setId(jsonRegistro.getInt("id"));
                tarefa.setTitulo(jsonRegistro.getString("titulo"));
                tarefa.setDescricao(jsonRegistro.getString("descricao"));
            }

            realm.commitTransaction();
        } catch (JSONException e) {
            Log.e(TAG, "Erro ao decodificar JSON: " + e.getMessage());
        } finally {
            realm.close();
        }

//        int size = ((int) realm.where(TarefaRealm.class).count());
//        int nextID = (int) (realm.where(TarefaRealm.class).maximumInt("id") + 1);

//        Log.i(TAG, "nextID: " + Integer.toString(nextID));
//        Log.i(TAG, "Tarefas: " + Integer.toString(size));

//        TarefaRealm tarefa = realm.createObject(TarefaRealm.class);
//        tarefa.setId(size + 1);
//        tarefa.setTitulo("Titulo " + Integer.toString(size + 1));
//        tarefa.setDescricao("Descricao");

//        realm.commitTransaction();
//        realm.close();
    }
}
