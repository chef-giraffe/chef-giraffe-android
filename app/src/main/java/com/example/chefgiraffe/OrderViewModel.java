package com.example.chefgiraffe;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Item;
import com.example.chefgiraffe.domains.Order;
import com.example.chefgiraffe.repositories.DataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<Order> order;
    private OrderRepository orderRepository;
    private MutableLiveData<DataRequest<Void>> postResult;

    public OrderViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addItem(Item item) {
        order.getValue().getItems().add(item);
        order.setValue(order.getValue());
    }

    public LiveData<Order> getOrder() {
        return this.order;
    }

    public LiveData<DataRequest<Void>> getPostResult() {
        return this.postResult;
    }

    public void save(Order order, String tableId) {
        DataManager.getInstance().getOrderRepository().saveOrder(order, tableId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                DataRequest<Void> result;
                if (response.isSuccessful()) {
                    result = new DataRequest<>(response.body());
                } else {
                    result = new DataRequest<>(String.valueOf(response.code()));
                }
                postResult.setValue(result);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                DataRequest<Void> result = new DataRequest<>("There was a problem with the network.");
                postResult.setValue(result);
            }
        });
    }
}
