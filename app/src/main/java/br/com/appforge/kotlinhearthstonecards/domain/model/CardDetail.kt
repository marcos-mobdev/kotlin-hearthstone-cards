package br.com.appforge.kotlinhearthstonecards.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardDetail(
    val id: String,
    val imagePath: String?,
    val name: String,
    val flavor: String?,
    val text: String?,
    val cardSet: String,
    val type: String,
    val faction: String?,
    val rarity: String?,
    val attack: Int?,
    val cost: Int?,
    val health: Int?
):Parcelable