import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.appforge.kotlinhearthstonecards.R
import br.com.appforge.kotlinhearthstonecards.databinding.ItemCardBinding
import br.com.appforge.kotlinhearthstonecards.domain.model.CardDetail
import com.squareup.picasso.Picasso

class CardGalleryAdapter(private var dataSet: List<CardDetail>, private val onItemClick: (CardDetail) -> Unit) :
    RecyclerView.Adapter<CardGalleryAdapter.CardItemViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */

    fun updateData(newDataSet: List<CardDetail>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    inner class CardItemViewHolder(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: CardDetail) {
            //binding.textNameContact.text = contact.name

            Picasso.get()
                .load(card.imagePath)
                .resize(120,200)
                .error(R.drawable.ic_error_24)
                .placeholder(R.drawable.card_back)
                .into(binding.imageView)

            itemView.setOnClickListener {
                onItemClick(card)
            }

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = ItemCardBinding.inflate(layoutInflater, viewGroup, false)

        return CardItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: CardItemViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}