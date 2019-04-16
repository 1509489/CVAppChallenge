package com.pixelart.cvappchallenge.network

import com.pixelart.cvappchallenge.common.RELATIVE_URL
import com.pixelart.cvappchallenge.model.Cv
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService{
    @GET(RELATIVE_URL)
    fun getCV(): Single<Cv>
}