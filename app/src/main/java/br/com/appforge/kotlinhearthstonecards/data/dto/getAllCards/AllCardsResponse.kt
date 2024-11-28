package br.com.appforge.kotlinhearthstonecards.data.dto.getAllCards

data class AllCardsResponse (
    val map: Map<String, List<AllCardsResponseItem>>
)