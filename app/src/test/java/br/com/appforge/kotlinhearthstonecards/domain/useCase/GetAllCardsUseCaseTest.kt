package br.com.appforge.kotlinhearthstonecards.domain.useCase

import br.com.appforge.kotlinhearthstonecards.data.dto.CardDetailsDTO
import br.com.appforge.kotlinhearthstonecards.data.repository.CardRepositoryImpl
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllCardsUseCaseTest {

    @Mock
    private lateinit var cardRepository: CardRepository

    private lateinit var getAllCardsUseCase: GetAllCardsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getAllCardsUseCase = GetAllCardsUseCase(cardRepository)
    }

    @Test
    fun `invoke should return a list of cards with image`() = runTest{
        //Arrange
        Mockito.`when`(cardRepository.getAllCards()).thenReturn(
            listOf(
                CardDetail("123", null, "Lich King", "Rise!", "Revive all ghouls", "Wrath of Lich King", "Undead", "Neutral", "Epic", 1,2,3),
                CardDetail("234", "www.test.com/img", "Jaina Proudmore", "Magic!", "Gains +2 magic damage", "Classic", "Human", "Alliance", "Rare", 2,3,4),
                CardDetail("345", "www.test.com/img2", "Thrall", "For the horde!", "Gives +2 attack for all cards", "Classic", "Orc", "Horde", "Rare", 5,6,7)
            )
        )
        //Act
        val actualListOfCards = getAllCardsUseCase()
        //Assert
        assertThat(actualListOfCards.none { it.imagePath == null }).isTrue()

    }




    @After
    fun tearDown() {
    }
}