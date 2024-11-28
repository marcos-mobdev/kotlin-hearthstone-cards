package br.com.appforge.kotlinhearthstonecards.data.remote

import br.com.appforge.kotlinhearthstonecards.data.dto.getDataSetCards.DataSetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthstoneAPI {

    @GET("cards/sets/Path%20of%20Arthas")
    suspend fun getDataSetCards(): Response<DataSetResponse>

}