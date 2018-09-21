package comc.example.mohammedmorse.baker.View;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import comc.example.mohammedmorse.baker.Adapters.IngrediantsAdapter;
import comc.example.mohammedmorse.baker.Adapters.StepsAdapter;
import comc.example.mohammedmorse.baker.Model.DataBase.DatabaseContrct;
import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.Presenter.MasterFragmentPresenterImplemetation;
import comc.example.mohammedmorse.baker.R;
import comc.example.mohammedmorse.baker.Widget.ListWidget;
import comc.example.mohammedmorse.baker.Widget.WidgetService;

public class Master extends Fragment implements MasterFragmentView ,View.OnClickListener{
    Context context;
    ScrollView scrollView      ;
    ImageView RecipeImage;
    RecyclerView Ingrediants , Steps;
    TotalJsonDataModel model;
    IngrediantsAdapter ingrediantsAdapter;
  ImageButton imageButton;
    StepsAdapter stepsAdapter;
    RecyclerView.LayoutManager IngrediantLayoutManager , StepsLayoutManager;
    DetailActivityView detailActivity;
    MasterFragmentPresenterImplemetation masterFragmentPresenterImplemetation;
    AppWidgetManager appWidgetManager ;

    public Master() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        detailActivity= (DetailActivityView) context;
        masterFragmentPresenterImplemetation=new MasterFragmentPresenterImplemetation(context,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_master, container, false);
        RecipeImage=view.findViewById(R.id.MasterSweetImage);
        scrollView=view.findViewById(R.id.Scrollview1);
        Ingrediants=view.findViewById(R.id.MasterIngrediantsRecyclerView);
        Steps=view.findViewById(R.id.MasterStepsRecyclerView);
        imageButton=view.findViewById(R.id.addDatabase);
        imageButton.setOnClickListener(this);
        model= (TotalJsonDataModel) getArguments().getSerializable("DataMaster");
        IngrediantLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        StepsLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        SetStepsAdapter();
        SetIngrediantsAdapter();
        Log.i("Morse", "onCreateView: "+model.getName());
        UI(model);
        masterFragmentPresenterImplemetation.CheckisInDB();
        return view;
    }
     public void UI(TotalJsonDataModel model){
         if((model.getId()-1)==0){
             RecipeImage.setImageResource(R.drawable.notilapie);
             scrollView.setBackground(getResources().getDrawable(R.drawable.item1));
             Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item1));
             Steps.setBackgroundColor(getResources().getColor(R.color.Item1));
             imageButton.setBackgroundColor(getResources().getColor(R.color.Item1));
         }
         else if((model.getId()-1)==1){
             RecipeImage.setImageResource(R.drawable.praoniz);
             scrollView.setBackground(getResources().getDrawable(R.drawable.item2));
             Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item2));
             Steps.setBackgroundColor(getResources().getColor(R.color.Item2));
             imageButton.setBackgroundColor(getResources().getColor(R.color.Item2));
         }
         else if((model.getId()-1)==2){
             RecipeImage.setImageResource(R.drawable.yellowcake);
             scrollView.setBackground(getResources().getDrawable(R.drawable.item3));
             Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item3));
             Steps.setBackgroundColor(getResources().getColor(R.color.Item3));
             imageButton.setBackgroundColor(getResources().getColor(R.color.Item3));
         }
         else if((model.getId()-1)==3){
             RecipeImage.setImageResource(R.drawable.cheesecake);
             scrollView.setBackground(getResources().getDrawable(R.drawable.item4));
             Ingrediants.setBackgroundColor(getResources().getColor(R.color.Item4));
             Steps.setBackgroundColor(getResources().getColor(R.color.Item4));
             imageButton.setBackgroundColor(getResources().getColor(R.color.Item4));
         }
     }
     public void SetStepsAdapter(){
         stepsAdapter=new StepsAdapter(model,context,detailActivity);
         Steps.setLayoutManager(StepsLayoutManager);
         Steps.setAdapter(stepsAdapter);
     }
    public void SetIngrediantsAdapter(){
        ingrediantsAdapter=new IngrediantsAdapter(model,context);
        Ingrediants.setLayoutManager(IngrediantLayoutManager);
        Ingrediants.setAdapter(ingrediantsAdapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void ChangeImageButton(boolean b) {
        if(b==true){
            imageButton.setImageResource(R.drawable.success);
            imageButton.setTag("Success");
        }
        else {
            imageButton.setImageResource(R.drawable.error);
            imageButton.setTag("Error");
        }
        Log.i("Morse", "ChangeImageButton: Called");
    }

    @Override
    public void onClick(View view) {
        imageButton= (ImageButton) view;
        appWidgetManager = AppWidgetManager.getInstance(context);
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
                new ComponentName(context, ListWidget.class));
        if(view.getTag()=="Success"){
            //Insert
            ContentValues[] values=getContentValues();
            Toast.makeText(context, ""+values[0].get("data0"), Toast.LENGTH_SHORT).show();
            masterFragmentPresenterImplemetation.InsertContentValues(values);
            imageButton.setImageResource(R.drawable.error);
            imageButton.setTag("Error");

        }else{
            //Delet
            masterFragmentPresenterImplemetation.Delet();
            imageButton.setImageResource(R.drawable.success);
            imageButton.setTag("Success");
        }

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ListView);
    }
    public ContentValues[] getContentValues(){
        ContentValues[] values=new ContentValues[model.getIngrediantList().size()];
        String Data;
        for (int i=0;i<values.length;i++){
            ContentValues contentValues=new ContentValues();
            Data=i+1+"- "+model.getIngrediantList().get(i).getQuantity()+" "+model.getIngrediantList().get(i).getMeasure()+" of "+
                    model.getIngrediantList().get(i).getIngrediant()+".";
           contentValues.put(DatabaseContrct.ColumeName,Data);
           values[i]=contentValues;
        }
        return values;
    }
}
