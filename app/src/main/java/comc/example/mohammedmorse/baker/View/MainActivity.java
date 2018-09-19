package comc.example.mohammedmorse.baker.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import comc.example.mohammedmorse.baker.Adapters.MasterRecyclerViewAdapter;
import comc.example.mohammedmorse.baker.IdlingTest.SimpleIdlingResources;
import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.Presenter.MainActivityMainActivityPresenterImplementation;
import comc.example.mohammedmorse.baker.Presenter.MainActivityPresenterInterface;
import comc.example.mohammedmorse.baker.R;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    RecyclerView recyclerView;
    MasterRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager manager;
    MainActivityPresenterInterface mainActivityPresenterInterface;
    ArrayList<TotalJsonDataModel> list;
    boolean isRotate=false;
    @Nullable private SimpleIdlingResources idlingResources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.Recyclerview);
        manager=new LinearLayoutManager(this);
        mainActivityPresenterInterface =new MainActivityMainActivityPresenterImplementation(this,idlingResources);
        Log.i("Morse", "onCreate: MainActivity");
    }
    @Override
    public void SetAdapter(ArrayList<TotalJsonDataModel> arrayList) {
      list=arrayList;
       adapter=new MasterRecyclerViewAdapter(list , this);
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(manager);
        Log.i("Morse", "SetAdapter: Called");
    }

    @Override
    public void MainRecyclerViewClicked(TotalJsonDataModel model) {
        this.StartActivity(model);
        Log.i("Morse", "MainRecyclerViewClicked: Action");
    }

    @Override
    public void StartActivity(TotalJsonDataModel model) {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("RecipeData",model);
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
        list= (ArrayList<TotalJsonDataModel>) savedInstanceState.getSerializable("NetworkData");
        isRotate=savedInstanceState.getBoolean("ifRotate");
            Log.i("Morse", "onRestoreInstanceState: Main Activity");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
       if(isRotate==false){
           mainActivityPresenterInterface.onCreateMainActivity();
           Log.i("Morse", "onResume: Network");
       }
       else{
           this.SetAdapter(list);
           Log.i("Morse", "onResume: Not Network");
       }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         outState.putSerializable("NetworkData",list);
         outState.putBoolean("ifRotate",true);
        Log.i("Morse", "onSaveInstanceState: MainActiviy");
    }
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResources == null) {
            idlingResources = new SimpleIdlingResources();
        }
        return idlingResources;
    }
}
