package comc.example.mohammedmorse.baker.Model.DataBase;

import android.net.Uri;
/**
 * Created by Mohammed Morse on 06/07/2018.
 */

public class ContentProviderContract {
    static final public String SCHEMA="content://";
    static final public String AUTHONTCATION="comc.example.mohammedmorse.baker";
    static final public String TABLEPATH="Ingrediant" ;
    static final public Uri URITOTABLE=Uri.parse(SCHEMA+AUTHONTCATION);
    static final public Uri FinalUrl=URITOTABLE.buildUpon().appendPath(TABLEPATH).build();
}
