package comc.example.mohammedmorse.baker.Model.Retrofit;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammed Morse on 12/08/2018.
 */

public interface RetrofitInterface {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<ArrayList<TotalJsonDataModel>> GetDataFromUrl();
}
