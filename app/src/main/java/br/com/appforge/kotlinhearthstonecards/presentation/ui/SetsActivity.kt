package br.com.appforge.kotlinhearthstonecards.presentation.ui

import CardSetsAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.appforge.kotlinhearthstonecards.R
import br.com.appforge.kotlinhearthstonecards.databinding.ActivitySetsBinding
import br.com.appforge.kotlinhearthstonecards.presentation.viewModel.CardSetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetsActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivitySetsBinding.inflate(layoutInflater)
    }

    private val cardSetsViewModel by viewModels<CardSetsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            intent.putExtra("pathParam",binding.cardNameEditText.text.toString())
            intent.putExtra("cardSource", "SEARCH")
            binding.cardNameEditText.setText("")
            startActivity(intent)
        }

        val recyclerViewAdapter = initializeRecyclerView()

        cardSetsViewModel.cardSetList.observe(this){ cardSets->
            recyclerViewAdapter.updateData(cardSets)
        }
    }

    private fun initializeRecyclerView():CardSetsAdapter{
        val customAdapter = CardSetsAdapter(emptyList()){ cardSet ->
            val intent = Intent(this, GalleryActivity::class.java)
            intent.putExtra("pathParam",cardSet)
            intent.putExtra("cardSource", "CARDSET")
            startActivity(intent)
        }
        val recyclerView = binding.rvCardSets
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        return customAdapter
    }
}