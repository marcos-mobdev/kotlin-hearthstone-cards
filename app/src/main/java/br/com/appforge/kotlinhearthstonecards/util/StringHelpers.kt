package br.com.appforge.kotlinhearthstonecards.util

import br.com.appforge.kotlinhearthstonecards.data.dto.CardDetailsDTO
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail


fun String.capitalizeFirstLetter(): String {
    return this.lowercase().replaceFirstChar {it.titlecase()
    }
}
fun String.formatCardText(): String {
    return this.replace("<b>", "")
        .replace("</b>","")
        .replace("<i>", " ")
        .replace("</i>", " ")
        .replace("$", "")
        .replace("\\n", " ")
        .replace("_"," ")
        .replace("[x]","")
}