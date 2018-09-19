package comc.example.mohammedmorse.baker.Presenter;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import comc.example.mohammedmorse.baker.Model.Exoplayer.ExoplayerHelper;
import comc.example.mohammedmorse.baker.View.DetailFragmentView;

/**
 * Created by Mohammed Morse on 16/09/2018.
 */

public class DetailFragmentPresenterImplementation implements DetailFragmentPresenterInterface {
    public SimpleExoPlayerView exoPlayerView;
    Context context;
    ExoplayerHelper helper;
    SimpleExoPlayer player;
    MediaSource mediaSource;
    DetailFragmentView view;
    public DetailFragmentPresenterImplementation(Context context ,SimpleExoPlayerView view , DetailFragmentView fragmentView){
              this.context=context;
              exoPlayerView=view ;
              this.view=fragmentView;
    }
    @Override
    public void InitExoplayer(String Url) {
        helper=new ExoplayerHelper(context,exoPlayerView);
       player= helper.CreateExoPlayer();
       mediaSource= helper.PrepareMediaSource(Uri.parse(Url));
       view.DetailUI(player , mediaSource);
    }
}
