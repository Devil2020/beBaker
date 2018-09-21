package comc.example.mohammedmorse.baker.View;

import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;

/**
 * Created by Mohammed Morse on 15/09/2018.
 */

public interface DetailActivityView {
    public void StartMasterFragment(TotalJsonDataModel model);
    public void StartDetailFragment(TotalJsonDataModel model , int Position);
    public void SetPositionofVideo(long Position , boolean isRun);
}
