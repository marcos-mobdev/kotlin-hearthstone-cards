package br.com.appforge.kotlinhearthstonecards.data.repository

import android.util.Log
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardSetRepository
import javax.inject.Inject

class CardSetRepositoryImpl@Inject constructor(
    private val hearthstoneAPI: HearthstoneAPI
):CardSetRepository{

    override suspend fun getCardSets(): List<String> {
        try {
            val response = hearthstoneAPI.getDataSets()
            if (response.isSuccessful && response.body() != null) {
                val cardSetList = response.body()?.sets
                if (cardSetList != null) {
                    //
                    // Log.i("info_cardset", "getCardSets: $cardSetList ")
                    return cardSetList
                } else {
                    //Log.e("info_cardset", "CardSetList is null!")
                    //throw IllegalArgumentException("Empty cardSet")
                }
            } else {
                //Log.e("info_cardset", "Response error: ${response.errorBody().toString()}")
                //throw IllegalArgumentException("Response error: ${response.errorBody().toString()}")
            }
        } catch (getCardSetsError: Exception) {
            getCardSetsError.printStackTrace()
            //throw getCardSetsError
        }
        return emptyList()

    }
}