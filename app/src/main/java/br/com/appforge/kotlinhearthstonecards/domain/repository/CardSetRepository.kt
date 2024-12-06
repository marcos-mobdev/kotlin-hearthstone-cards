package br.com.appforge.kotlinhearthstonecards.domain.repository

interface CardSetRepository {
    suspend fun getCardSets():List<String>
}