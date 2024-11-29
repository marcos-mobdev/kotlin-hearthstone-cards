package br.com.appforge.kotlinhearthstonecards.data.dto

import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail

data class CardDetailsDTO(
    val cardId: String,
    val img: String?,
    val name: String,
    val flavor: String?,
    val text: String?,
    val cardSet: String,
    val type: String,
    val faction: String?,
    val rarity: String?,
    val attack: Int?,
    val cost: Int?,
    val health: Int?,
)

fun CardDetailsDTO.toCardDetail():CardDetail{
    return CardDetail(
        id = this.cardId,
        imagePath = this.img,
        name = this.name,
        flavor = this.flavor,
        text = this.text,
        cardSet = this.cardSet,
        type = this.type,
        faction = this.faction,
        rarity = this.rarity,
        attack = this.attack,
        cost = this.cost,
        health = this.health
    )
}

