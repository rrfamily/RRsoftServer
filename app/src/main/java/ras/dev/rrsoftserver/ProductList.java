package ras.dev.rrsoftserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import ras.dev.rrsoftserver.Interface.ItemClickListener;
import ras.dev.rrsoftserver.Model.Product;
import ras.dev.rrsoftserver.ViewHolder.ProductViewHolder;

public class ProductList extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;

    //Firebase
    FirebaseDatabase db;
    DatabaseReference productList;
    FirebaseStorage storage;
    StorageReference storageReference;

    String categoryId = "";

    FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //Fireabase
        db = FirebaseDatabase.getInstance();
        productList = db.getReference("Products");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //init
        recyclerView = (RecyclerView) findViewById(R.id.recycler_product);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code later
            }
        });


        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryID");
       //if (!categoryId.isEmpty() && categoryId !=null) {
            loadListProduct(categoryId);
        //}

    }

    private void loadListProduct(String categoryId) {
        Query searchByName = productList.orderByChild("menuID").equalTo(categoryId); //Compare Name

        FirebaseRecyclerOptions<Product> productOptions = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(searchByName, Product.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(productOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {

                holder.product_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(holder.prodcut_image);


                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //code later
                    }
                });


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

}
