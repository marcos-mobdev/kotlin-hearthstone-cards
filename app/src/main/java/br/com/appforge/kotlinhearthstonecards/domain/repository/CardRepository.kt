package br.com.appforge.kotlinhearthstonecards.domain.repository

import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail

interface CardRepository {

    suspend fun getAllCards(selectedCardSet:String):List<CardDetail>

    suspend fun searchCard(cardName:String):List<CardDetail>

}