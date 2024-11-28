package br.com.appforge.kotlinhearthstonecards.data.remote

import br.com.appforge.kotlinhearthstonecards.data.dto.getAllCards.AllCardsResponse
import br.com.appforge.kotlinhearthstonecards.data.dto.getSingleCard.SingleCardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthstoneAPI {

    @GET("cards")
    suspend fun getAllCards(): Response<AllCardsResponse>

    @GET("cards/{id}")
    suspend fun getSingleCard(
        @Path("id") id:String
    ):Response<SingleCardResponse>
}