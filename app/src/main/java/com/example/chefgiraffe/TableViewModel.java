package com.example.chefgiraffe;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Order;
import com.example.chefgiraffe.domains.Table;
import com.example.chefgiraffe.repositories.DataManager;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableViewModel extends ViewModel {
    private MutableLiveData<DataRequest<Table>> table = new MutableLiveData<>();
    private MutableLiveData<DataRequest<List<Order>>> orders = new MutableLiveData<>();

    public LiveData<DataRequest<Table>> getTable() {
        return table;
    }

    public LiveData<DataRequest<List<Order>>> getOrders() {
        return orders;
    }

    public void init(String tableId) {
        loadTable(tableId);
        loadOrders(tableId);
    }

    private void loadTable(String tableId) {
        DataManager.getInstance().getTableRepository().getTable(tableId).enqueue(new Callback<Table>() {
            @Override
            public void onResponse(Call<Table> call, Response<Table> response) {
                if (response.isSuccessful()) {
                    table.setValue(new DataRequest<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Table> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadOrders(String tableId) {
        DataManager.getInstance().getOrderRepository().getOrders(tableId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orders.setValue(new DataRequest<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
