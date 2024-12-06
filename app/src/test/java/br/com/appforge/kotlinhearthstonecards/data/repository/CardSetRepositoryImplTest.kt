package br.com.appforge.kotlinhearthstonecards.data.repository

import br.com.appforge.kotlinhearthstonecards.data.dto.InfoResponse
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class CardSetRepositoryImplTest {

    @Mock
    private lateinit var hearthstoneAPI: HearthstoneAPI

    private lateinit var cardSetRepositoryImpl:CardSetRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cardSetRepositoryImpl = CardSetRepositoryImpl(hearthstoneAPI)
    }

    @Test
    fun `getDataSets should return a list of strings with cardSets names`() = runTest {
        //Arrange
        Mockito.`when`(hearthstoneAPI.getDataSets()).thenReturn(
            Response.success(
                InfoResponse(
                    listOf("Classic", "Burning Crusade", "Wrath of Lich King", "Cataclysm")
                )
            )
        )
        //Act
        val actualCardSetList = cardSetRepositoryImpl.getCardSets()
        //Assert
        assertThat(actualCardSetList).isNotNull()
        assertThat(actualCardSetList).isNotEmpty()
        assertThat(actualCardSetList).contains("Cataclysm")
    }


    @After
    fun tearDown() {
    }
}