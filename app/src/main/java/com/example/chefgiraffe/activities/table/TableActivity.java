package com.example.chefgiraffe.activities.table;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chefgiraffe.R;
import com.example.chefgiraffe.activities.BaseActivity;
import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Order;
import com.example.chefgiraffe.domains.Table;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

public class TableActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String VAR_TABLE_ID = "table-id";
    @BindView(R.id.tableList)
    ListView orderList;
    @BindView(R.id.vwRefresh)
    SwipeRefreshLayout vwRefresh;
    @BindView(R.id.viewPlaceOrder)
    FloatingActionButton viewPlaceOrder;
    private String tableId;
    private OrderAdapter orderListAdapter;
    private TableViewModel viewModel;
    private List<Order> orders;

    @Override
    protected int contentView() {
        return R.layout.activity_table;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        tableId = intent.getStringExtra(VAR_TABLE_ID);

        orders = new ArrayList<>();
        orderListAdapter = new OrderAdapter(this, orders);
        orderList.setAdapter(orderListAdapter);

        vwRefresh.setOnRefreshListener(this);

        viewModel = ViewModelProviders.of(this).get(TableViewModel.class);
        startObservingTable(tableId);
    }

    @Override
    public void onRefresh() {
        viewModel.refreshTable(tableId);
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
                    orders.clear();
                    orders.addAll(value.getData().getOrderDetails());
                    orderListAdapter.notifyDataSetChanged();
                    vwRefresh.setRefreshing(false);
                }
            }
        });
    }
}
