package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.Table;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TableRepository {
    @GET
    Call<Table>
}
