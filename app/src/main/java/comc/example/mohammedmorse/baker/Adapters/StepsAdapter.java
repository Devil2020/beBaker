package comc.example.mohammedmorse.baker.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.R;
import comc.example.mohammedmorse.baker.View.DetailActivityView;
import comc.example.mohammedmorse.baker.View.MasterFragmentView;

/**
 * Created by Mohammed Morse on 22/08/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.CustomIngrediantHolder> {
    private TotalJsonDataModel list;
    private Context context;
    private DetailActivityView masterFragmentView;
    public StepsAdapter(TotalJsonDataModel list, Context context,DetailActivityView view){
        this.list=list;
        this.context=context;
        masterFragmentView =view;
    }
    @Override
    public CustomIngrediantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.stepsitem,parent,false);
        CustomIngrediantHolder holder=new CustomIngrediantHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomIngrediantHolder holder, final int position) {
        holder.ReciepeDesc.setText(list.getStepsList().get(position).getShortDesc());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masterFragmentView.StartDetailFragment(list,position);
                Log.i("Morse", "onClick: to open Detil Fragment "+position);
            }
        });
        Log.i("Morse","RecyclerViewOnBindViewHolder Step Adapter "+list.getStepsList().get(position).getId())    ;
    }
    @Override
    public int getItemCount() {
        return list.getStepsList().size();
    }

    class CustomIngrediantHolder extends RecyclerView.ViewHolder{
        TextView ReciepeDesc;
        ViewGroup constraintLayout;
        public CustomIngrediantHolder(View itemView) {
            super(itemView);
            ReciepeDesc=itemView.findViewById(R.id.StepsRecipe);
            constraintLayout=itemView.findViewById(R.id.StepsLayout);
        }
    }
}
