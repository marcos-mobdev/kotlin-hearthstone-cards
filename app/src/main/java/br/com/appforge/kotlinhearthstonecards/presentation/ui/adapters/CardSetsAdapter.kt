import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.appforge.kotlinhearthstonecards.databinding.ItemSetBinding

class CardSetsAdapter(private var dataSet: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<CardSetsAdapter.CardSetItemViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */

    fun updateData(newDataSet: List<String>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class CardSetItemViewHolder(val binding: ItemSetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cardSet: String) {
            binding.btnCardSet.text = cardSet
            //Log.i("debug_brabo1", "DEBUG: $cardSet")

            binding.btnCardSet.setOnClickListener {
                Log.i("debug_brabo2", "DEBUG: $cardSet")
                onItemClick(cardSet)
            }

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardSetItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = ItemSetBinding.inflate(layoutInflater, viewGroup, false)

        return CardSetItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: CardSetItemViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}