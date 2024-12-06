package br.com.appforge.kotlinhearthstonecards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetCardSetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetsViewModel @Inject constructor(
    private val getCardSetsUseCase: GetCardSetsUseCase
):ViewModel() {
    private val _cardSetList = MutableLiveData<List<String>>()

    val cardSetList : LiveData<List<String>>
        get() = _cardSetList

    init {
        getCardSetList()
    }

    fun getCardSetList() {
        viewModelScope.launch (Dispatchers.IO){
            val data = getCardSetsUseCase()
            _cardSetList.postValue(data)
        }
    }

}