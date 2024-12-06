package br.com.appforge.kotlinhearthstonecards.domain.useCase

import br.com.appforge.kotlinhearthstonecards.domain.repository.CardSetRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCardSetsUseCaseTest {
    @Mock
    private lateinit var cardSetRepository: CardSetRepository
    private lateinit var getCardSetsUseCase: GetCardSetsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCardSetsUseCase = GetCardSetsUseCase(cardSetRepository)
    }



    @Test
    operator fun invoke()= runTest {
        //Arrange
        Mockito.`when`(cardSetRepository.getCardSets()).thenReturn(
            listOf("Classic", "Burning Crusade", "Wrath of Lich King", "Cataclysm")
        )
        //Act
        val actualCardSetList = getCardSetsUseCase()
        //Assert
         assertThat(actualCardSetList).isNotNull()
         assertThat(actualCardSetList).isNotEmpty()

    }

    @After
    fun tearDown() {
    }
}