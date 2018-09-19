package comc.example.mohammedmorse.baker.Widget;

import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import comc.example.mohammedmorse.baker.Model.DataBase.ContentProviderContract;
import comc.example.mohammedmorse.baker.Model.DataBase.DatabaseContrct;

/**
 * Created by Mohammed Morse on 17/09/2018.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetServiceFactory();
    }
    public class WidgetServiceFactory implements RemoteViewsFactory{
        Cursor cursor;
        String [] strings;
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            String [] strings=new String[1];
            strings[0]=DatabaseContrct.ColumeName;
           cursor= getContentResolver().query(ContentProviderContract.FinalUrl,strings
            ,null,null,null,null);
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            strings=getStrings(cursor);
            RemoteViews remoteViews=new RemoteViews(getApplicationContext().getPackageName()
                    , android.R.layout.simple_list_item_1);
            if(strings.length>0) {
                remoteViews.setTextViewText(android.R.id.text1, strings[i]);

            }

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
        public String[] getStrings(Cursor cursor){
            String[] strings=new String[cursor.getCount()];
            int m=cursor.getColumnIndex(DatabaseContrct.ColumeName);
           cursor.moveToFirst();
            for(int i=0;i<strings.length;i++){
                strings[i]= cursor.getString(m);
                cursor.moveToNext();
            }
            return strings;
        }
    }
}
