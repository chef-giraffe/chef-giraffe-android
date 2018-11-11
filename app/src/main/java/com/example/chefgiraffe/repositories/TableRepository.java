package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.Table;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TableRepository {
    @GET("/tables/{tableId}")
    Call<Table> getTable(@Path("tableId") String tableId);

    @GET("/tables/{tableId}/orders")
    Call<Table> getTableWithPreparingOrders(@Path("tableId") String tableId);
}
