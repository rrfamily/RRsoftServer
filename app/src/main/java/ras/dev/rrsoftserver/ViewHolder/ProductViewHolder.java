package ras.dev.rrsoftserver.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ras.dev.rrsoftserver.Common.Common;
import ras.dev.rrsoftserver.Interface.ItemClickListener;
import ras.dev.rrsoftserver.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnCreateContextMenuListener
{

    public TextView product_name;
    public ImageView  product_image;

    private ItemClickListener itemClickListener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        product_name = (TextView)itemView.findViewById(R.id.product_name);
        product_image = (ImageView)itemView.findViewById(R.id.product_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);



    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select the action");

        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(),Common.DELETE);


    }
}
