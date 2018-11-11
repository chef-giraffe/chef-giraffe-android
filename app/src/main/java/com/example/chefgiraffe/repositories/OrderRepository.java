package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.Item;
import com.example.chefgiraffe.domains.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderRepository {
    @POST("/tables/{tableId}/orders")
    Call<Void> saveOrder(@Body Order order, @Path("tableId") String tableId);
}
