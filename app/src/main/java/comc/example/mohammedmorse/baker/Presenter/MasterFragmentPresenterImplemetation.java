package comc.example.mohammedmorse.baker.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import comc.example.mohammedmorse.baker.Model.DataBase.ContentProviderContract;
import comc.example.mohammedmorse.baker.Model.DataBase.Database;
import comc.example.mohammedmorse.baker.View.MasterFragmentView;

/**
 * Created by Mohammed Morse on 17/09/2018.
 */

public class MasterFragmentPresenterImplemetation implements MasterFragmentPresenterInterface {
  Database database;
  Context context;
  MasterFragmentView view;
  public MasterFragmentPresenterImplemetation(Context context,MasterFragmentView view){
      this.context=context;
      database=new Database(context);
      this.view=view;
  }
      @Override
    public void CheckisInDB() {
      Cursor b=context.getContentResolver().query(ContentProviderContract.FinalUrl,
              new String[]{"IngrediantDesc"},null,null,null,null);
      boolean x=false;
       if(b.getCount()==0){
          x=true;
       }
        view.ChangeImageButton(x);
    }

    @Override
    public void InsertContentValues(ContentValues[] values) {
        for (int i = 0; i < values.length; i++) {
            context.getContentResolver().insert(ContentProviderContract.FinalUrl,values[i]);
        }
    }

    @Override
    public void Delet() {
        context.getContentResolver().delete(ContentProviderContract.FinalUrl,null,null);
    }
}
