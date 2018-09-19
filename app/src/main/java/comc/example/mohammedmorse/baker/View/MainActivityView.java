package comc.example.mohammedmorse.baker.View;

import java.util.ArrayList;

import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;

/**
 * Created by Mohammed Morse on 15/09/2018.
 */

public interface MainActivityView {
    public void SetAdapter(ArrayList<TotalJsonDataModel> arrayList);
    public void MainRecyclerViewClicked(TotalJsonDataModel model);
    public void StartActivity(TotalJsonDataModel model);
}
