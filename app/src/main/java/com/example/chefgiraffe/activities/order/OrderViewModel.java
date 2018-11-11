package com.example.chefgiraffe.activities.order;

import com.example.chefgiraffe.domains.CreateOrder;
import com.example.chefgiraffe.domains.Item;
import com.example.chefgiraffe.repositories.DataManager;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<List<Item>> items = new MutableLiveData<>();

    public OrderViewModel() {
        items.setValue(new ArrayList<Item>());
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public void addItem(String id, String name) {
        Item item = new Item(id, name);
        List<Item> items = this.items.getValue();
        items.add(item);
        this.items.setValue(items);
    }

    public void createOrder(String tableId) {
        ArrayList<String> itemIds = new ArrayList<>();
        for (Item item : items.getValue()) {
            itemIds.add(item.getId());
        }
        CreateOrder createOrder = new CreateOrder(tableId, itemIds);

        DataManager.getInstance().getOrderRepository().saveOrder(createOrder).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
