package comc.example.mohammedmorse.baker.Model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mohammed Morse on 14/09/2018.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DbName="Ingrediants";
    Context context;
    public Database(Context context) {
        super(context, DbName, null, 33);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+DatabaseContrct.TableName+" ( "+DatabaseContrct.ColumeName+" Text );");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("Drop Table if Exists Ingrediant");
          onCreate(sqLiteDatabase);
    }
}
