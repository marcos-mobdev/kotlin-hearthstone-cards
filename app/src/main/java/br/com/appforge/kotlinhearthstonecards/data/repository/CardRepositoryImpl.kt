package br.com.appforge.kotlinhearthstonecards.data.repository

import android.util.Log
import br.com.appforge.kotlinhearthstonecards.data.dto.getDataSetCards.toCardItem
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import br.com.appforge.kotlinhearthstonecards.domain.model.CardItem
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val hearthstoneAPI: HearthstoneAPI
): CardRepository {
    override suspend fun getAllCards(): List<CardItem> {
        try{
            val response = hearthstoneAPI.getDataSetCards()
            Log.i("info_card", "try to get all cards!")
            if(response.isSuccessful && response.body() != null){
                val cardListDTO = response.body()
                val cardList = mutableListOf<CardItem>()
                if(cardListDTO != null){
                    cardListDTO.map {
                        Log.i("info_card", "getAllCards: ${it.name}")
                        cardList.add(it.toCardItem())
                    }
                    return cardList
                }else{
                    Log.i("info_card", "Card list is null!")
                }
            }else{
                Log.i("info_card", "Response message:${response.message()}")
            }

        }catch(getUsersError:Exception){
            getUsersError.printStackTrace( )
            Log.e("info_card", "GetUsersError")
        }
        return emptyList()
    }
}