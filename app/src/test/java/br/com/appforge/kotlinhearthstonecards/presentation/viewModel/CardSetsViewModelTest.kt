package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetCardSetsUseCase
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
class CardSetsViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCardSetsUseCase: GetCardSetsUseCase

    private lateinit var cardSetsViewModel: CardSetsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cardSetsViewModel = CardSetsViewModel(getCardSetsUseCase)
    }

    @Test
    fun getCardSetList_should_update_cardSetList_and_should_not_be_empty() = runTest{
        //Arrange
        Mockito.`when`(getCardSetsUseCase()).thenReturn(
            listOf("Classic", "Burning Crusade", "Wrath of Lich King", "Cataclysm")
        )
        //Act
        cardSetsViewModel.getCardSetList()
        advanceUntilIdle()
        val cardSetList = cardSetsViewModel.cardSetList.getOrAwaitValue()
        //Assert
        assertThat(cardSetList).isNotNull()
        assertThat(cardSetList).isNotEmpty()


    }




    @After
    fun tearDown() {
    }
}