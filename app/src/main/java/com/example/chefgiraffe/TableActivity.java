package com.example.chefgiraffe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Table;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.OnClick;

public class TableActivity extends BaseActivity {
    public static final String VAR_TABLE_ID = "table-id";
    @BindView(R.id.tableList)
    ListView orderList;
    @BindView(R.id.viewPlaceOrder)
    FloatingActionButton viewPlaceOrder;
    private ArrayAdapter<String> orderListAdapter;
    private TableViewModel viewModel;
    private List<String> orderItems;

    @Override
    protected int contentView() {
        return R.layout.activity_table;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String tableId = intent.getStringExtra(VAR_TABLE_ID);

        orderItems = new ArrayList<>();
        orderListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, orderItems);
        orderList.setAdapter(orderListAdapter);

        viewModel = ViewModelProviders.of(this).get(TableViewModel.class);
        startObservingTable(tableId);
    }

    @OnClick(R.id.viewPlaceOrder)
    void viewPlaceOrderClicked() {
        // TODO: Mitch's activity
    }

    private void startObservingTable(String tableId) {
        viewModel.getTable(tableId).observe(this, new Observer<DataRequest<Table>>() {
            @Override
            public void onChanged(DataRequest<Table> value) {
                if (value.isSuccessful()) {
                    getSupportActionBar().setTitle(value.getData().getFriendlyName());
                    orderItems.clear();
                    orderItems.addAll(value.getData().commaSeperatedOrderItems());
                    orderListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
