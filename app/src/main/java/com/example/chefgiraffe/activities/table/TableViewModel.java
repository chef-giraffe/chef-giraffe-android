package com.example.chefgiraffe.activities.table;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Table;
import com.example.chefgiraffe.repositories.DataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableViewModel extends ViewModel {
    private MutableLiveData<DataRequest<Table>> table;

    public LiveData<DataRequest<Table>> getTable(String tableId) {
        if (table == null) {
            table = new MutableLiveData<>();
            refreshTable(tableId);
        }
        return table;
    }

    public void refreshTable(String tableId) {
        DataManager.getInstance().getTableRepository().getTableWithPreparingOrders(tableId).enqueue(new Callback<Table>() {
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
}
