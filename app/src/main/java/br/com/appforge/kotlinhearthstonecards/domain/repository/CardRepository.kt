package br.com.appforge.kotlinhearthstonecards.domain.repository

import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail

interface CardRepository {

    suspend fun getAllCards():List<CardDetail>

}