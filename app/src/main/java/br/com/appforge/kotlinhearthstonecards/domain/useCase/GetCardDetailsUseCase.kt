package br.com.appforge.kotlinhearthstonecards.domain.useCase

import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import javax.inject.Inject

class GetCardDetailsUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    /*
    suspend operator fun invoke():CardDetail{
      return CardDetail()
    }

     */

}