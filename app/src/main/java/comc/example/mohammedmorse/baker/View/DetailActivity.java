package comc.example.mohammedmorse.baker.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.Presenter.MainActivityPresenterInterface;
import comc.example.mohammedmorse.baker.R;

public class DetailActivity extends AppCompatActivity implements DetailActivityView{
       TotalJsonDataModel model;
       ViewGroup Master , Detail;
       MainActivityPresenterInterface anInterface;
       FragmentManager manager;
       FragmentTransaction transaction;
       boolean isMaster ,isRun ,isDetail =false;
       int Position ;
       long VideoPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Master=findViewById(R.id.MasterFragment);
        Detail=findViewById(R.id.DetailFragment);
        Intent intent=getIntent();
        model= (TotalJsonDataModel) intent.getExtras().getSerializable("RecipeData");
        setTitle(model.getName());
        Log.i("Morse", "onCreate: Detail Activity");
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(Detail==null) {
            if (isDetail!=true) {
                StartMasterFragment(model);
                Log.i("Morse", "onResume: Start Master");
            }
            else{
                StartDetailFragment(model,Position);
                Log.i("Morse", "onResume: Start Detail");
            }
        }
        else{
            StartMasterFragment(model);
            if (isDetail==true) {
                StartDetailFragment(model,Position);
            }
        }
        Log.i("Morse", "onResume: DetailActivity");
    }
    @Override
    public void StartMasterFragment(TotalJsonDataModel model) {
        isMaster=true;
        Master master=new Master();
        Bundle bundle=new Bundle();
        bundle.putSerializable("DataMaster",model);
        master.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.MasterFragment ,master, master.getClass().getName() ).commit();
    }
    @Override
    public void StartDetailFragment(TotalJsonDataModel model , int Position) {
        this.Position=Position;
        isDetail=true;
        Detail detail=new Detail();
        Bundle bundle=new Bundle();
        bundle.putSerializable("DataDetail",model);
        bundle.putInt("Position",Position);
        bundle.putLong("VideoPosition",VideoPosition);
        bundle.putBoolean("isRun",isRun);
        detail.setArguments(bundle);
        if(Detail ==null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.MasterFragment, detail,detail.getClass().getName()).commit();
            Log.i("Morse", "StartDetailFragment: on MasterLayout");
        }else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.DetailFragment, detail,detail.getClass().getName()).commit();
            Log.i("Morse", "StartDetailFragment: on DetailLayout");
        }
    }
    @Override
    public void SetPositionofVideo(long Position , boolean r) {
        this.VideoPosition=Position;
        isRun=r;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    outState.putSerializable("Data",model);
    outState.putBoolean("isMaster",isMaster);
    outState.putBoolean("isDetail",isDetail);
    outState.putInt("Position",Position);
    outState.putLong("VideoPosition",VideoPosition);
    outState.putBoolean("isRun",isRun);
        Log.i("Morse", "onSaveInstanceState: Detail Activity");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        model= (TotalJsonDataModel) savedInstanceState.getSerializable("Data");
        isMaster= savedInstanceState.getBoolean("isMaster");
        isDetail=savedInstanceState.getBoolean("isDetail");
        Position=savedInstanceState.getInt("Position");
        VideoPosition=savedInstanceState.getLong("VideoPosition");
        isRun=savedInstanceState.getBoolean("isRun");
        Log.i("Morse", "onRestoreInstanceState: Detail Activity");
    }
}
