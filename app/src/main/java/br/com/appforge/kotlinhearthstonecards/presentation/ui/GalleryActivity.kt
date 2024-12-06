package br.com.appforge.kotlinhearthstonecards.presentation.ui

import CardGalleryAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.appforge.kotlinhearthstonecards.databinding.ActivityGalleryBinding
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.presentation.viewModel.CardGalleryViewModel
import br.com.appforge.kotlinhearthstonecards.presentation.viewModel.CardsSource
import dagger.hilt.android.AndroidEntryPoint
import javax.sql.DataSource

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityGalleryBinding.inflate(layoutInflater)
    }

    private val cardGalleryViewModel by viewModels<CardGalleryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Get cards
        val pathParam = getPathParamFromBundle()
        val cardSource = getCardSourceFromBundle()
        cardGalleryViewModel.getAllCards(pathParam, cardSource)

        //Adapter
        val recyclerViewAdapter = initializeRecyclerView()

        //ViewModel
        cardGalleryViewModel.cards.observe(this){ cardList ->
            recyclerViewAdapter.updateData(cardList)
            checkIfCardListIsEmptyAndSetTextviewErrorVisibility(cardList)
        }
    }

    private fun checkIfCardListIsEmptyAndSetTextviewErrorVisibility(cardList:List<CardDetail>){
        binding.noCardsText.visibility =
            if(cardList.isEmpty()) View.VISIBLE
            else View.INVISIBLE
    }

    private fun initializeRecyclerView():CardGalleryAdapter{
        val customAdapter = CardGalleryAdapter(emptyList()){ card ->
            val intent = Intent(this, CardDetailsActivity::class.java)
            intent.putExtra("card",card)
            startActivity(intent)
        }
        val recyclerView = binding.rvCards
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.adapter = customAdapter
        return customAdapter
    }

    private fun getPathParamFromBundle():String{
        val bundle = intent.extras?.getString("pathParam")
        var pathParam = ""
        if(bundle!= null){
            pathParam = bundle
        }
        Log.i("info_cardset", "pathParam: $pathParam")
        return pathParam

    }

    private fun getCardSourceFromBundle():CardsSource{
        val bundle = intent.extras?.getString("cardSource")
        var cardSource = CardsSource.valueOf(bundle ?: "SEARCH")
        if(bundle!= null){
            cardSource = CardsSource.valueOf(bundle)
        }
        Log.i("info_cardset", "cardSource: $cardSource")
        return cardSource
    }
}