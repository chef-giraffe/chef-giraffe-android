package com.example.chefgiraffe.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;

    private DataManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://chef-giraffe.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tableRepository = retrofit.create(TableRepository.class);
        orderRepository = retrofit.create(OrderRepository.class);
    }

    public static DataManager getInstance() {
        return ourInstance;
    }

    public TableRepository getTableRepository() {
        return tableRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}
