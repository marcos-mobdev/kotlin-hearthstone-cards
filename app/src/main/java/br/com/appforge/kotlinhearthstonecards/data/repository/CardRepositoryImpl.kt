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
    override suspend fun getAllCards(selectedCardSet: String): List<CardDetail> {
        try{
            val response = hearthstoneAPI.getDataSetCards(selectedCardSet)
            if(response.isSuccessful && response.body() != null){
                val cardListDTO = response.body()
                val cardList = mutableListOf<CardDetail>()
                if(cardListDTO != null){
                    cardListDTO.map {
                        //Log.i("card_details", "$it")
                        cardList.add(it.toCardDetail())
                    }
                    return cardList
                }else{
                    //Log.e("info_card", "Card list is null!")
                }
            }else{
                //Log.e("info_card", "Response message:${response.errorBody()}")
            }

        }catch(getCardsError:Exception){
            //Log.e("info_card", "GetCardsError $getCardsError")
            getCardsError.printStackTrace( )
        }
        return emptyList()
    }

}