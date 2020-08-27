package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    List<String> items;
MainActivity.OnLongClickListener longClickListener;
    public ItemsAdapter(List<String> items, MainActivity.OnLongClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // Use layout inflator to inflate a view
        // Wrap it inside a
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return 0;
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem.setText (item);
            tvItem.setOnLongClickListener(new View.OnLongClickListner() {
                @Override
                public boolean onLongClick (View v) {
                    //Remove the item from the recycler view
                    //Notify the listener which position was long
                    longClickListener.OnItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }

    }
}
