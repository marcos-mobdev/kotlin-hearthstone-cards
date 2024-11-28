package br.com.appforge.kotlinhearthstonecards.data.dto.getSingleCard

data class SingleCardResponseItem(
    val cardId: String,
    val img: String,
    val name: String,

    val flavor: String,
    val text: String,
    val cardSet: String,
    val type: String,
    //faccao
    val rarity: String,
    val attack: Int,
    val cost: Int,
    val health: Int
)