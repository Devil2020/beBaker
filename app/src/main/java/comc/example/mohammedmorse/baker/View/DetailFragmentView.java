package comc.example.mohammedmorse.baker.View;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;

import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;

/**
 * Created by Mohammed Morse on 16/09/2018.
 */

public interface DetailFragmentView {
    public Void DetailUI(SimpleExoPlayer player, MediaSource mediaSource);
    public void PreviousButtonAction();
    public void NextButtonAction();
}
