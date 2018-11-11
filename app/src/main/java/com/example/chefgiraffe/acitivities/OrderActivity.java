package com.example.chefgiraffe.acitivities;

import androidx.lifecycle.ViewModelProviders;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chefgiraffe.OrderViewModel;
import com.example.chefgiraffe.R;
import com.example.chefgiraffe.activities.BaseActivity;
import com.example.chefgiraffe.domains.Item;
import com.example.chefgiraffe.domains.Order;

public class OrderActivity extends BaseActivity {
    private OrderViewModel viewModel;
    static final int PICK_ITEM_REQUEST = 1;  // The request code
    Order order;
    String tableId;

    @Override
    protected int contentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        Intent intent = getIntent();
        tableId = intent.getStringExtra("tableId");
        viewModel.getOrder().observe(this, order -> {
            this.order = order;
        });
    }

    @OnClick(R.id.fabAddItem)
    public void addItem(View view) {
        //TODO Kick off ForResult Activity and retrieve item
        Intent pickContactIntent = new Intent(OrderActivity.this, ItemListSelectionActivity.class);
        startActivityForResult(pickContactIntent, PICK_ITEM_REQUEST);
        viewModel.addItem(new Item());
    }

    @OnClick(R.id.btnPlaceOrder)
    public void placeOrder(View view) {
        //use viewmodel to save order
        viewModel.save(order);
        //navigate user back to main activity
    }
}
