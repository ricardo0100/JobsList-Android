package br.com.otes06.jobslist.Sync;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


public class StubProvider extends ContentProvider {
    public static final String TAG = "StubProvider";

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate");
        return true;
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG, "getType: " + uri.toString());
        return "";
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "query: " + uri.toString());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "insert: " + uri.toString());
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "delete: " + uri.toString());
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(TAG, "update: " + uri.toString());
        return 0;
    }
}
