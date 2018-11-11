package com.example.chefgiraffe.activities.main;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Table;
import com.example.chefgiraffe.repositories.DataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<DataRequest<Table>> table = new MutableLiveData<>();

    public LiveData<DataRequest<Table>> getTable() {
        return table;
    }

    public void selectedTableId(String tableId) {
        DataManager.getInstance().getTableRepository().getTable(tableId).enqueue(new Callback<Table>() {
            @Override
            public void onResponse(Call<Table> call, Response<Table> response) {
                DataRequest<Table> result;
                if (response.isSuccessful()) {
                    result = new DataRequest<>(response.body());
                } else {
                    result = new DataRequest<>(String.valueOf(response.code()));
                }
                table.setValue(result);
            }

            @Override
            public void onFailure(Call<Table> call, Throwable t) {
                t.printStackTrace();
                DataRequest<Table> result = new DataRequest<>("There was a problem with the network.");
                table.setValue(result);
            }
        });
    }
}
