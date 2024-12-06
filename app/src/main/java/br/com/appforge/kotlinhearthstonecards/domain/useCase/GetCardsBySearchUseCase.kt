package br.com.appforge.kotlinhearthstonecards.domain.useCase

import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import javax.inject.Inject

class GetCardsBySearchUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(cardName: String):List<CardDetail>{
        try{
            val cards = cardRepository.searchCard(cardName)
            val cardsWithImage = mutableListOf<CardDetail>()
            cards.forEach { item->
                if(item.imagePath != null){
                    cardsWithImage.add(item)
                }else{
                    //Log.i("info_card", "card without image")
                }
            }
            return cardsWithImage
        }catch (getCardsBySearchError:Exception){
            getCardsBySearchError.printStackTrace()
            return emptyList()
        }
    }
}