package com.jbm.testapp.repository.network

import com.jbm.testapp.repository.storage.Country
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    fun getCountryData(): Single<List<Country>>
}
