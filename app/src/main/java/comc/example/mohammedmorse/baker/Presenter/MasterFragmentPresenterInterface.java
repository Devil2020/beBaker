package comc.example.mohammedmorse.baker.Presenter;

import android.content.ContentValues;

/**
 * Created by Mohammed Morse on 17/09/2018.
 */

public interface MasterFragmentPresenterInterface {
    public void CheckisInDB();
    public void InsertContentValues(ContentValues[] values);
    public void Delet();
}
