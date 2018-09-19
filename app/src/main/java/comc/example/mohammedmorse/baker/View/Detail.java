package comc.example.mohammedmorse.baker.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import comc.example.mohammedmorse.baker.Adapters.IngrediantsAdapter;
import comc.example.mohammedmorse.baker.Adapters.StepsAdapter;
import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.Presenter.DetailFragmentPresenterImplementation;
import comc.example.mohammedmorse.baker.R;
public class Detail extends Fragment implements DetailFragmentView{
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer player;
    ImageButton Previous , Next;
    TextView Description ;
    ConstraintLayout scrollView , scrollView2;
    TotalJsonDataModel model;
    DetailActivityView view;
    DetailFragmentPresenterImplementation presenterImplementation;
    int StepPosition;
    int RotatePosition;
    long VideoPosition;
    Context context;
    String Uri=null;
    public Detail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        exoPlayerView=view.findViewById(R.id.SimpleExoPlayerView);
        Description=view.findViewById(R.id.StepDescription);
        scrollView=view.findViewById(R.id.StepsScrollView);
        scrollView2=view.findViewById(R.id.DetailFragmentLayout2);
        Previous=view.findViewById(R.id.Previous);
        Next=view.findViewById(R.id.Next);
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviousButtonAction();
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextButtonAction();
            }
        });
        model= (TotalJsonDataModel) getArguments().getSerializable("DataDetail");
        StepPosition=getArguments().getInt("Position");
        VideoPosition=getArguments().getLong("VideoPosition");
        if(StepPosition==0){
            Previous.setVisibility(View.INVISIBLE);
        }
        Uri=getUri(StepPosition);
        presenterImplementation=new DetailFragmentPresenterImplementation
                (context,exoPlayerView,this);
        presenterImplementation.InitExoplayer(Uri);
        UI(model , StepPosition);
    return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        view= (DetailActivityView) context;
    }

    @Override
    public void onPause() {
        super.onPause();
        player.release();
        player.stop();
    }
    public void UI(TotalJsonDataModel model , int stepPosition){
        if((model.getId()-1)==0){
            scrollView.setBackground(getResources().getDrawable(R.drawable.item1));
            scrollView2.setBackground(getResources().getDrawable(R.drawable.item1));
            Previous.setBackground(getResources().getDrawable(R.drawable.item1));
            Next.setBackground(getResources().getDrawable(R.drawable.item1));
        }
        else if((model.getId()-1)==1){
            scrollView.setBackground(getResources().getDrawable(R.drawable.item2));
            Previous.setBackground(getResources().getDrawable(R.drawable.item2));
            Next.setBackground(getResources().getDrawable(R.drawable.item2));
            scrollView2.setBackground(getResources().getDrawable(R.drawable.item2));
        }
        else if((model.getId()-1)==2){
            Previous.setBackground(getResources().getDrawable(R.drawable.item3));
            Next.setBackground(getResources().getDrawable(R.drawable.item3));
            scrollView.setBackground(getResources().getDrawable(R.drawable.item3));
            scrollView2.setBackground(getResources().getDrawable(R.drawable.item3));
        }
        else if((model.getId()-1)==3){
            Previous.setBackground(getResources().getDrawable(R.drawable.item4));
            Next.setBackground(getResources().getDrawable(R.drawable.item4));
            scrollView.setBackground(getResources().getDrawable(R.drawable.item4));
            scrollView2.setBackground(getResources().getDrawable(R.drawable.item4));
        }
        //Description.setText(model.getStepsList().get(stepPosition).getDescribtion());
    }

    @Override
    public Void DetailUI(SimpleExoPlayer player, MediaSource mediaSource) {
          this.player=player;
          exoPlayerView.setPlayer(player);
          player.prepare(mediaSource);
          player.setPlayWhenReady(true);
          if(StepPosition==RotatePosition) {
              player.seekTo(VideoPosition);
          }
        Description.setText(model.getStepsList().get(StepPosition).getDescribtion());
        return null;
    }

    @Override
    public void PreviousButtonAction() {
        if(StepPosition==1){
            Previous.setVisibility(View.INVISIBLE);
        }
        Uri=getUri(--StepPosition);
        presenterImplementation.InitExoplayer(Uri);
    }

    @Override
    public void NextButtonAction() {
        if (Previous.getVisibility() == View.INVISIBLE) {
            Previous.setVisibility(View.VISIBLE);
        }

            Uri = getUri(++StepPosition);
            presenterImplementation.InitExoplayer(Uri);

    }
    public String getUri(int Position){
        if(!model.getStepsList().get(Position).getVideoUrl().equals(null)){
            Uri= model.getStepsList().get(Position).getVideoUrl();
        }
        else if(model.getStepsList().get(Position).getVideoUrl().equals(null)&&
                !model.getStepsList().get(Position).getThumbnailUrl().equals(null)){
            Uri=model.getStepsList().get(Position).getThumbnailUrl();
        }
        else{
            Toast.makeText(context, "There is`t a Uri to attach to Exoplayerviewer", Toast.LENGTH_SHORT).show();
        }
        return Uri;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        view.SetPositionofVideo(player.getCurrentPosition());
        outState.putInt("RotatePosition",StepPosition);
        Log.i("Morse", "onSaveInstanceState: Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
        RotatePosition=savedInstanceState.getInt("RotatePosition");
    }                                }
}
