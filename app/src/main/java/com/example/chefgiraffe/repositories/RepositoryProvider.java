package com.example.chefgiraffe.repositories;

import dagger.Provides;
import retrofit2.Retrofit;

public class RepositoryProvider {
    @Provides static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("TODO")
                .build();
    }

    @Provides static
}
