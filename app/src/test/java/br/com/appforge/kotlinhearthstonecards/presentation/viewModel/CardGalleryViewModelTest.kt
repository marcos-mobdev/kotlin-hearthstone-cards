package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetAllCardsUseCase
import br.com.appforge.kotlinhearthstonecards.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardGalleryViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAllCardsUseCase: GetAllCardsUseCase

    private lateinit var cardGalleryViewModel:CardGalleryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cardGalleryViewModel = CardGalleryViewModel(getAllCardsUseCase)
    }

    @Test
    fun `getAllCards should update _cardList and should not be empty`() = runTest{
        //Arrange
        Mockito.`when`(getAllCardsUseCase()).thenReturn(
            listOf(
                CardDetail("123", "www.test.com/img", "Lich King", "Rise!", "Revive all ghouls", "Wrath of Lich King", "Undead", "Neutral", "Epic", 1,2,3),
                CardDetail("234", "www.test.com/img1", "Jaina Proudmore", "Magic!", "Gains +2 magic damage", "Classic", "Human", "Alliance", "Rare", 2,3,4),
                CardDetail("345", "www.test.com/img2", "Thrall", "For the horde!", "Gives +2 attack for all cards", "Classic", "Orc", "Horde", "Rare", 5,6,7)
            )
        )
        //Act
        cardGalleryViewModel.getAllCards()

        advanceUntilIdle()

        val cardList = cardGalleryViewModel.cards.getOrAwaitValue()

        //Assert
        assertThat(cardList).isNotNull()
        assertThat(cardList).isNotEmpty()
        assertThat(cardList.size).isEqualTo(3)
        assertThat(cardList[0].name).isEqualTo("Lich King")
    }



    @After
    fun tearDown() {
    }
}