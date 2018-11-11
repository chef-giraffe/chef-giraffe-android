package com.example.chefgiraffe.activities.itemlistselection;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Item;
import com.example.chefgiraffe.repositories.DataManager;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListSelectionViewModel extends ViewModel {
   private  MutableLiveData<DataRequest<List<Item>>> items = new MutableLiveData<>();

    public LiveData<DataRequest<List<Item>>> getItems() {
        return items;
    }

    public void loadItems(){
        DataManager.getInstance().getItemRepository().getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                DataRequest<List<Item>> result;
                if (response.isSuccessful()) {
                    result = new DataRequest<>(response.body());
                } else {
                    result = new DataRequest<>(String.valueOf(response.code()));
                }
                items.setValue(result);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
                DataRequest<List<Item>> result = new DataRequest<>("There was a problem with the network.");
                items.setValue(result);
            }
        });
    }
}

