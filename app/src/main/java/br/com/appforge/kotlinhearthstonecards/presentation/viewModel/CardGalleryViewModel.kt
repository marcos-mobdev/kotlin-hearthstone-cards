package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetAllCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import javax.inject.Inject

@HiltViewModel
class CardGalleryViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase
):ViewModel() {
    //Cards
    private val _cardsList = MutableLiveData<List<CardDetail>>()
    val cards : LiveData <List<CardDetail>>
        get() = _cardsList

    //Sets
    private val _cardSet = MutableLiveData<String>()
    val cardset : LiveData <String>
        get() = _cardSet

    /*
    init {
        getAllCards("")
    }

     */


    fun getAllCards(selectedCardSet: String) {
        viewModelScope.launch (Dispatchers.IO){
            val listCards = getAllCardsUseCase(selectedCardSet)
            _cardsList.postValue(listCards)
        }
    }

}