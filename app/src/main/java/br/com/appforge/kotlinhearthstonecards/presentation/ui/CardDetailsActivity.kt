package br.com.appforge.kotlinhearthstonecards.presentation.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
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

                val formattedText = card.name
                    .replace("<b>", " ")


                cardNameText.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)

                //Text nullable
                cardShortText.visibility = if (card.text != null) View.VISIBLE else View.GONE
                cardShortText.text = card.text ?: ""

                //Cost nullable
                cardCostText.visibility = if (card.cost != null) View.VISIBLE else View.GONE
                cardCostText.text = "\uD83D\uDC8E ${card.cost.toString()}" ?: ""

                //Attack nullable
                cardAttackText.visibility = if (card.attack != null) View.VISIBLE else View.GONE
                cardAttackText.text = "\uD83D\uDDE1 ${card.attack.toString()}" ?: ""

                //Health nullable
                cardHealthText.visibility = if (card.health != null) View.VISIBLE else View.GONE
                cardHealthText.text = "\uD83E\uDE78 ${card.health.toString()}" ?: ""

                //Flavor nullable
                cardFlavorText.visibility = if (card.flavor != null) View.VISIBLE else View.GONE
                cardFlavorText.text = card.flavor ?: ""

                //Faction nullable
                cardFactionText.visibility = if (card.faction != null) View.VISIBLE else View.GONE
                cardFactionText.text = "Faction: ${card.faction}" ?: ""

                //Faction nullable
                cardRarityText.visibility = if (card.rarity != null) View.VISIBLE else View.GONE
                cardRarityText.text = "Rarity: ${card.rarity}" ?: ""

                cardSetText.text = "Set: ${card.cardSet}"
                cardTypeText.text = "Type: ${card.type}"
            }
        }
        /*
        cardDetailsViewModel.card.observe(this){ card->
        }

         */



    }
}