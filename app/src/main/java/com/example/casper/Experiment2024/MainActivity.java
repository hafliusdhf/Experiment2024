package com.example.casper.Experiment2024;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> items;
    private ActivityResultLauncher<Intent> launcherAdd;
    private ActivityResultLauncher<Intent> launcherUpdate;
    private ShopItemAdapter shopItemAdapter;

    @Override
    public boolean onContextItemSelected(MenuItem itemMenu ) {
        //item.getOrder()
        switch (itemMenu.getItemId()) {
            case 0:
                Intent intentAdd = new Intent(MainActivity.this, ItemActivity.class);
                intentAdd.putExtra("position", itemMenu.getOrder());
                launcherAdd.launch(intentAdd);
                break;
            case 1:
                Intent intentUpdate = new Intent(MainActivity.this, ItemActivity.class);
                intentUpdate.putExtra("position", itemMenu.getOrder());
                Item itemObject= items.get(itemMenu.getOrder());
                intentUpdate.putExtra("item_name", itemObject.getTitle());
                intentUpdate.putExtra("item_price", itemObject.getPrice());
                launcherUpdate.launch(intentUpdate);
                break;
            case 2:
                break;
            default:
                return super.onContextItemSelected(itemMenu);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherAdd = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        String itemName=data.getStringExtra("item_name");
                        double itemPrice= data.getDoubleExtra("item_price",0);
                        int position = data.getIntExtra("position",0);
                        items.add(position,new Item(itemName, itemPrice, R.drawable.qingjiao));
                        shopItemAdapter.notifyItemInserted(position);
                    }
                });
        launcherUpdate = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        String itemName=data.getStringExtra("item_name");
                        double itemPrice= data.getDoubleExtra("item_price",0);
                        int position = data.getIntExtra("position",0);
                        items.add(position,new Item(itemName, itemPrice, R.drawable.qingjiao));
                        shopItemAdapter.notifyItemInserted(position);
                    }
                });

        EdgeToEdge.enable(this);
        //使用布局文件创建控件
        setContentView(R.layout.activity_main);

        RecyclerView mainRecyclerView = findViewById(R.id.recyclerview_items);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        items.add(new Item("青椒", 1.5, R.drawable.qingjiao));
        items.add(new Item("萝卜", 2.5, R.drawable.luobo));
        items.add(new Item("白菜", 3.5, R.drawable.baicai));


        shopItemAdapter = new ShopItemAdapter(items);
        mainRecyclerView.setAdapter(shopItemAdapter);

        registerForContextMenu(mainRecyclerView);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private class ShopItemAdapter extends RecyclerView.Adapter {
        private final List<Item> items;

        public ShopItemAdapter(List<Item> items) {
            this.items=items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder= (MyViewHolder) holder;
            myViewHolder.getTextViewName().setText(items.get(position).getTitle());
            myViewHolder.getTextViewPrice().setText("" + items.get(position).getPrice());
            myViewHolder.getImageViewPicture().setImageResource(items.get(position).getResourceId());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            public ImageView getImageViewPicture() {
                return imageViewPicture;
            }

            public TextView getTextViewName() {
                return textViewName;
            }

            public TextView getTextViewPrice() {
                return textViewPrice;
            }

            private final ImageView imageViewPicture;
            private final TextView textViewName;
            private final TextView textViewPrice;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageViewPicture= itemView.findViewById(R.id.imageview_item);
                this.textViewName= itemView.findViewById(R.id.textview_item_name);
                this.textViewPrice=itemView.findViewById(R.id.textview_item_price);

                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("具体操作");

                menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
                menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
                menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
            }
        }
    }
}