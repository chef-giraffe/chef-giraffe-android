package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.CreateOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderRepository {
    @POST("/orders")
    Call<Void> saveOrder(@Body CreateOrder order);
}
