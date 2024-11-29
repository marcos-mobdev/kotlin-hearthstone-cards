package br.com.appforge.kotlinhearthstonecards.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.kotlinhearthstonecards.R
import br.com.appforge.kotlinhearthstonecards.databinding.ActivityCardDetailsBinding
import br.com.appforge.kotlinhearthstonecards.databinding.ActivityGalleryBinding
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import br.com.appforge.kotlinhearthstonecards.presentation.viewModel.CardDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailsActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCardDetailsBinding.inflate(layoutInflater)
    }

    //private val cardDetailsViewModel by viewModels<CardDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        val card = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle?.getParcelable("card", CardDetail::class.java)
        }else{
            bundle?.getParcelable("card")
        }

        if(card!= null){
            with(binding){
                Picasso.get()
                    .load(card.imagePath)
                    .error(R.drawable.ic_error_24)
                    .placeholder(R.drawable.card_back)
                    .into(cardImage)
                cardNameText.text = card.name
                cardFlavorText.text = card.flavor
                cardShortText.text = card.text
                cardSetText.text = card.cardSet
                cardTypeText.text = card.type
                cardFactionText.text = card.faction
                cardRarityText.text = card.rarity
                cardAttackText.text = card.attack.toString()
                cardCostText.text = card.cost.toString()
                cardHealthText.text = card.health.toString()
            }
        }
        /*
        cardDetailsViewModel.card.observe(this){ card->
        }

         */



    }
}