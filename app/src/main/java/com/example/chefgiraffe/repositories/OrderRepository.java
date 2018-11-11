package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderRepository {
    @GET("/table/{tableId}/orders")
    Call<List<Order>> getOrders(@Path("tableId") String tableId);
}
