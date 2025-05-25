package com.jbm.testapp.repository.network

import com.jbm.testapp.repository.storage.Fruit
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {
    @GET("all")
    fun getFruitData(): Single<List<Fruit>>
}
