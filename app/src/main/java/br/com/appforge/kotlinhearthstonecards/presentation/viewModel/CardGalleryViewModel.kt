package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlinhearthstonecards.domain.model.CardItem
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetAllCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardGalleryViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase
):ViewModel() {
    private val _cardsList = MutableLiveData<List<CardItem>>()

    val cards : LiveData <List<CardItem>>
        get() = _cardsList

    init {
        getAllCards()
    }

    private fun getAllCards() {
        viewModelScope.launch {
            val listCards = getAllCardsUseCase()
            _cardsList.postValue(listCards)
        }
    }
}