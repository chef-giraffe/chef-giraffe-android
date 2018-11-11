package com.example.chefgiraffe.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private final TableRepository tableRepository;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tableRepository = retrofit.create(TableRepository.class);
    }

    public TableRepository getTableRepository() {
        return tableRepository;
    }
}
