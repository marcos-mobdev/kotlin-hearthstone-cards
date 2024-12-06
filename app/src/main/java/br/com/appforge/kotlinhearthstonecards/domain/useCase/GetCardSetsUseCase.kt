package br.com.appforge.kotlinhearthstonecards.domain.useCase

import br.com.appforge.kotlinhearthstonecards.domain.repository.CardSetRepository
import javax.inject.Inject

class GetCardSetsUseCase @Inject constructor(
    private val cardSetRepository: CardSetRepository
) {
    suspend operator fun invoke():List<String>{
        try {
            return cardSetRepository.getCardSets()
        }catch(getCardSetUseCaseError:Exception){
            getCardSetUseCaseError.printStackTrace()
        }
        return emptyList()

    }
}