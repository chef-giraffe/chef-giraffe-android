package com.example.chefgiraffe.activities.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.chefgiraffe.R;
import com.example.chefgiraffe.activities.BaseActivity;
import com.example.chefgiraffe.activities.itemlistselection.ItemListSelectionActivity;
import com.example.chefgiraffe.domains.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {
    public static final String VAR_TABLE_ID = "table-id";
    public static final int PICK_ITEM_REQUEST = 1;  // The request code
    @BindView(R.id.fabAddItem)
    FloatingActionButton vwAddItem;
    @BindView(R.id.vwItems)
    ListView vwItems;
    @BindView(R.id.btnPlaceOrder)
    Button vwPlaceOrder;
    private String tableId;
    private ArrayAdapter<String> itemsAdapter;
    private List<String> itemNames;
    private OrderViewModel viewModel;

    @Override
    protected int contentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        Intent intent = getIntent();
        tableId = intent.getStringExtra(VAR_TABLE_ID);

        itemNames = new ArrayList<>();
        itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
        vwItems.setAdapter(itemsAdapter);

        viewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                ArrayList<String> inItemNames = new ArrayList<>();
                for (Item item : items) {
                    inItemNames.add(item.getName());
                }
                itemNames.clear();
                itemNames.addAll(inItemNames);
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String itemId = data.getStringExtra("itemId");
                String itemName = data.getStringExtra("itemName");
                viewModel.addItem(itemId, itemName);
            }
        }
    }

    @OnClick(R.id.fabAddItem)
    public void addItem(View view) {
        Intent pickContactIntent = new Intent(OrderActivity.this, ItemListSelectionActivity.class);
        startActivityForResult(pickContactIntent, PICK_ITEM_REQUEST);
    }

    @OnClick(R.id.btnPlaceOrder)
    public void placeOrder(View view) {
        viewModel.createOrder(tableId);
        finish();
    }
}
