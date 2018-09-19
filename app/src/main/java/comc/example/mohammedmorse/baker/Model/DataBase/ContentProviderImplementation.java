package comc.example.mohammedmorse.baker.Model.DataBase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import comc.example.mohammedmorse.baker.Model.DataBase.Database;
import comc.example.mohammedmorse.baker.Model.DataBase.DatabaseContrct;

/**
 * Created by Mohammed Morse on 06/07/2018.
 */

public class ContentProviderImplementation extends ContentProvider {
    public Database dataBase;
   public UriMatcher Urimatcher;
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
         dataBase.getWritableDatabase().delete(DatabaseContrct.TableName,null,null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        dataBase=new Database(getContext());
        return true;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
     Cursor cursor= dataBase.getReadableDatabase().query(DatabaseContrct.TableName,projection , null,null,null,null,null);
      return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
       dataBase.getWritableDatabase().insert(DatabaseContrct.TableName,null,values);
        return null;
    }

}
