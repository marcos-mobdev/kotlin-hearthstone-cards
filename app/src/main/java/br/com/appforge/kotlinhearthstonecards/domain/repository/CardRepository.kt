package br.com.appforge.kotlinhearthstonecards.domain.repository

import br.com.appforge.kotlinhearthstonecards.domain.model.CardItem

interface CardRepository {

    suspend fun getAllCards():List<CardItem>

}