package br.com.appforge.kotlinhearthstonecards.data.repository


import br.com.appforge.kotlinhearthstonecards.data.dto.CardDetailsDTO
import br.com.appforge.kotlinhearthstonecards.data.dto.CardListResponse
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.hamcrest.core.AnyOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CardRepositoryImplTest {

    @Mock
    private lateinit var hearthstoneAPI: HearthstoneAPI

    private lateinit var cardRepositoryImpl: CardRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cardRepositoryImpl = CardRepositoryImpl(hearthstoneAPI)
    }

    @Test
    fun `getAllCards should return a list of cards when successful`() = runTest{
        //Arrange
        Mockito.`when`(hearthstoneAPI.getDataSetCards(anyString())).thenReturn(
            Response.success(
                CardListResponse().apply {
                    add(CardDetailsDTO("123", "www.test.com/img1", "Lich King", "Rise!", "Revive all ghouls", "Wrath of Lich King", "Undead", "Neutral", "Epic", 1,2,3))
                    add(CardDetailsDTO("234", "www.test.com/img", "Jaina Proudmore", "Magic!", "Gains +2 magic damage", "Classic", "Human", "Alliance", "Rare", 2,3,4))
                    add(CardDetailsDTO("345", "www.test.com/img2", "Thrall", "For the horde!", "Gives +2 attack for all cards", "Classic", "Orc", "Horde", "Rare", 5,6,7))
                }
            )
        )
        //Act
        val actualCardList = cardRepositoryImpl.getAllCards("Wrath of Lich King")
        //Assert
        assertThat(actualCardList).isNotNull()
        assertThat(actualCardList).isNotEmpty()
    }

    @After
    fun tearDown() {
    }

}