package br.com.appforge.kotlinhearthstonecards.data.remote

import br.com.appforge.kotlinhearthstonecards.data.dto.CardListResponse
import br.com.appforge.kotlinhearthstonecards.data.dto.InfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthstoneAPI {
    @GET("cards/sets/{name}")
    suspend fun getDataSetCards(@Path("name")setName:String): Response<CardListResponse>

    @GET("info")
    suspend fun getDataSets():Response<InfoResponse>

    @GET("cards/search/{name}")
    suspend fun searchCard(@Path("name")cardName:String): Response<CardListResponse>
}