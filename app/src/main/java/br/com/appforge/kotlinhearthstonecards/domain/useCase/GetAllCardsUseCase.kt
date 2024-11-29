package br.com.appforge.kotlinhearthstonecards.domain.useCase

import android.util.Log
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke():List<CardDetail>{
        try{
            val cards = cardRepository.getAllCards()
            val cardsWithImage = mutableListOf<CardDetail>()
            cards.forEach { item->
                if(item.imagePath != null){
                    cardsWithImage.add(item)
                }else{
                    Log.i("info_card", "card without image")
                }
            }

            return cardsWithImage
        }catch (getAllCardsException:Exception){
            getAllCardsException.printStackTrace()
            return emptyList()
        }
    }
}