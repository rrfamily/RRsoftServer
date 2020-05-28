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

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
                    View.OnCreateContextMenuListener
        {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

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
