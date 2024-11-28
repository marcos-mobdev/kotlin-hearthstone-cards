package br.com.appforge.kotlinhearthstonecards.data.dto.getDataSetCards

import br.com.appforge.kotlinhearthstonecards.domain.model.CardItem

data class CardDTO(
    val attack: Int,
    val cardId: String,
    val cardSet: String,
    val cost: Int,
    val dbfId: Int,
    val faction: String,
    val flavor: String,
    val health: Int,
    val img: String?,
    val locale: String,
    val name: String,
    val race: String,
    val rarity: String,
    val text: String,
    val type: String
)

fun CardDTO.toCardItem() :CardItem{
    return CardItem(
        id = this.cardId,
        imagePath = this.img
    )
}