package br.com.appforge.kotlinhearthstonecards.data.repository

import android.util.Log
import br.com.appforge.kotlinhearthstonecards.data.dto.toCardDetail
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val hearthstoneAPI: HearthstoneAPI
): CardRepository {
    override suspend fun getAllCards(): List<CardDetail> {
        try{
            val response = hearthstoneAPI.getDataSetCards()
            if(response.isSuccessful && response.body() != null){
                val cardListDTO = response.body()
                val cardList = mutableListOf<CardDetail>()
                if(cardListDTO != null){
                    cardListDTO.map {
                        cardList.add(it.toCardDetail())
                    }
                    return cardList
                }else{
                    Log.e("info_card", "Card list is null!")
                }
            }else{
                Log.e("info_card", "Response message:${response.message()}")
            }

        }catch(getUsersError:Exception){
            getUsersError.printStackTrace( )
            Log.e("info_card", "GetUsersError")
        }
        return emptyList()
    }

}