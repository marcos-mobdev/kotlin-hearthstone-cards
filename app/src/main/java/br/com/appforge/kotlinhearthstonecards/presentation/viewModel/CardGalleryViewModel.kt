package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetAllCardsUseCase
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetCardsBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import javax.inject.Inject

@HiltViewModel
class CardGalleryViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getCardsBySearchUseCase: GetCardsBySearchUseCase
):ViewModel() {
    //Cards
    private val _cardsList = MutableLiveData<List<CardDetail>>()
    val cards : LiveData <List<CardDetail>>
        get() = _cardsList

    fun getAllCards(pathParam: String, source: CardsSource) {
        viewModelScope.launch (Dispatchers.IO){
            val listCards = when(source){
                CardsSource.CARDSET -> getAllCardsUseCase(pathParam)
                CardsSource.SEARCH -> getCardsBySearchUseCase(pathParam)
            }
            _cardsList.postValue(listCards)
        }
    }

}

enum class CardsSource{
    SEARCH,
    CARDSET
}