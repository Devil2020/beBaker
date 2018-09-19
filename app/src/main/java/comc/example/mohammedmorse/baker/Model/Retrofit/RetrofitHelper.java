package comc.example.mohammedmorse.baker.Model.Retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohammed Morse on 15/09/2018.
 */

public class RetrofitHelper {
    private static volatile RetrofitHelper retrofit;
    private Retrofit Custretrofit;
    Observable<ArrayList<TotalJsonDataModel>> call;
    private RetrofitHelper(){
        if(retrofit!=null){
            throw new RuntimeException("You Can`t make from it another Object");
        }
    }
    public synchronized static RetrofitHelper getInstance(){
        if(retrofit==null){
            retrofit=new RetrofitHelper();
        }
        return retrofit;
    }
    public void InitRetrofit(String Url){
        Custretrofit=new Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public Observable<ArrayList<TotalJsonDataModel>> MakeaCall(){
        RetrofitInterface helper=Custretrofit.create(RetrofitInterface.class);
        Observable<ArrayList<TotalJsonDataModel>> call=helper.GetDataFromUrl();
        return call;
    }
    public void CloseRetrofitCall(){
          call.unsubscribeOn(Schedulers.newThread());
    }
}
