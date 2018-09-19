package comc.example.mohammedmorse.baker.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.baker.Model.Retrofit.IngrediantsModel;
import comc.example.mohammedmorse.baker.Model.Retrofit.TotalJsonDataModel;
import comc.example.mohammedmorse.baker.R;


/**
 * Created by Mohammed Morse on 22/08/2018.
 */

public class IngrediantsAdapter extends RecyclerView.Adapter<IngrediantsAdapter.CustomIngrediantHolder> {
    private TotalJsonDataModel list;
    private Context context;
    public IngrediantsAdapter(TotalJsonDataModel list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public CustomIngrediantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.ingrediantitem,parent,false);
        CustomIngrediantHolder holder=new CustomIngrediantHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomIngrediantHolder holder, final int position) {
        String Data=position+"- We Need "+list.getIngrediantList().get(position).getQuantity()+" "+list.getIngrediantList().get(position).getMeasure()+" of "+
                list.getIngrediantList().get(position).getIngrediant()+".";
        holder.SweetName.setText(Data);
        Log.i("Morse","RecyclerViewOnBindViewHolder Ingrdiants "+list.getIngrediantList().get(position).getIngrediant())   ;
    }
    @Override
    public int getItemCount() {
        return list.getIngrediantList().size();
    }

    class CustomIngrediantHolder extends RecyclerView.ViewHolder{
        TextView SweetName;
        public CustomIngrediantHolder(View itemView) {
            super(itemView);
            SweetName=itemView.findViewById(R.id.IngrediantRecipe);

        }
    }
}
