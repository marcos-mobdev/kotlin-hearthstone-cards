package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetCardDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    private val getCardDetailsUseCase: GetCardDetailsUseCase
) :ViewModel(){
    private val _card = MutableLiveData<CardDetail>()

    val card : LiveData<CardDetail>
        get() = _card


    init {
        getCardDetails()
    }

    private fun getCardDetails() {
        viewModelScope.launch {
            /*
            val cardDetail = getCardDetailsUseCase()
            _card.postValue(cardDetail)

             */
        }
    }
}