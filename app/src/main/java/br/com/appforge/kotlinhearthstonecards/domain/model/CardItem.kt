package br.com.appforge.kotlinhearthstonecards.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardItem (
    val id: String,
    val imagePath:String?
):Parcelable