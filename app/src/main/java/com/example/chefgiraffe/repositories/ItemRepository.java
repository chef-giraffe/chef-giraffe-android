package com.example.chefgiraffe.repositories;

import com.example.chefgiraffe.domains.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemRepository {
    @GET("/items")
    Call<List<Item>> getItems();
}
