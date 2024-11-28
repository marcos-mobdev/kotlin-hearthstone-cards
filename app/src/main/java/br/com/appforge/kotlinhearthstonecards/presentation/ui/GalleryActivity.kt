package br.com.appforge.kotlinhearthstonecards.presentation.ui

import CardGalleryAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import br.com.appforge.kotlinhearthstonecards.R
import br.com.appforge.kotlinhearthstonecards.databinding.ActivityGalleryBinding
import br.com.appforge.kotlinhearthstonecards.domain.model.CardItem
import br.com.appforge.kotlinhearthstonecards.presentation.viewModel.CardGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityGalleryBinding.inflate(layoutInflater)
    }

    private val cardGalleryViewModel by viewModels<CardGalleryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Adapter setup
        val customAdapter = CardGalleryAdapter(emptyList()){ card ->
            startActivity(Intent(this, CardDetailsActivity::class.java))
        }
        val recyclerView = binding.rvCards
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.adapter = customAdapter

        //ViewModel
        cardGalleryViewModel.cards.observe(this){ cardList ->
            customAdapter.updateData(cardList)
        }


    }
}