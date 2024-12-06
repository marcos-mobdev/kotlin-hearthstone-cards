package br.com.appforge.kotlinhearthstonecards.presentation.ui

import CardSetsAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerViewAdapter = initializeRecyclerView()

        cardSetsViewModel.cardSetList.observe(this){ cardSets->
            recyclerViewAdapter.updateData(cardSets)
        }
    }

    fun initializeRecyclerView():CardSetsAdapter{
        val customAdapter = CardSetsAdapter(emptyList()){ cardSet ->
            val intent = Intent(this, GalleryActivity::class.java)
            intent.putExtra("cardset",cardSet)
            startActivity(intent)
        }
        val recyclerView = binding.rvCardSets
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        return customAdapter
    }
}