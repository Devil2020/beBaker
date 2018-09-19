package comc.example.mohammedmorse.baker.Presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import comc.example.mohammedmorse.baker.IdlingTest.SimpleIdlingResources;
import comc.example.mohammedmorse.baker.Model.Retrofit.RetrofitHelper;
import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.View.MainActivityView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohammed Morse on 15/09/2018.
 */

public class MainActivityMainActivityPresenterImplementation implements MainActivityPresenterInterface {
    RetrofitHelper helper;
    MainActivityView view;
    @Nullable private SimpleIdlingResources idlingResources;
    Observable<ArrayList<TotalJsonDataModel>> arrayListObservable;
    public MainActivityMainActivityPresenterImplementation(MainActivityView view , @Nullable SimpleIdlingResources simpleIdlingResources){
        this.view=view;
        idlingResources=simpleIdlingResources;
    }
    @Override
    public void onCreateMainActivity() {
        if(idlingResources!=null){
            idlingResources.setIdleState(false);
        }
          helper=RetrofitHelper.getInstance();
          helper.InitRetrofit("https://d17h27t6h515a5.cloudfront.net");
        arrayListObservable=helper.MakeaCall();
        Observer<ArrayList<TotalJsonDataModel>> observer=new Observer<ArrayList<TotalJsonDataModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<TotalJsonDataModel> arrayList) {
                Log.i("Morse", "onNext: "+arrayList.size());
                view.SetAdapter(arrayList);
                if(idlingResources!=null){
                idlingResources.setIdleState(true);
            }                             }

            @Override
            public void onError(Throwable e) {

                Log.i("Morse", "onError: "+e.getCause());
            }

            @Override
            public void onComplete() {

            }
        }                                  ;
        arrayListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
        Log.i("Morse", "onCreateMainActivity: Called");
    }

    @Override
    public void StopRequest() {
        arrayListObservable.unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
