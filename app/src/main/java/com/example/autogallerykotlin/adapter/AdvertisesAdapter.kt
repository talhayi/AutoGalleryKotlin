package com.example.autogallerykotlin.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autogallerykotlin.data.model.Advertises
import com.example.autogallerykotlin.databinding.AdvertisesItemLayoutBinding
import com.example.autogallerykotlin.util.Util.BASE_URL

class AdvertisesAdapter : RecyclerView.Adapter<AdvertisesAdapter.AdvertsViewHolder>() {
    private lateinit var mListener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    class AdvertsViewHolder(
        val binding: AdvertisesItemLayoutBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Advertises>() {
        override fun areItemsTheSame(oldItem: Advertises, newItem: Advertises): Boolean {
            return oldItem.advertId == newItem.advertId
        }

        override fun areContentsTheSame(oldItem: Advertises, newItem: Advertises): Boolean {
            return newItem == oldItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var advertises: List<Advertises>
    get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertsViewHolder {

        return AdvertsViewHolder(AdvertisesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), mListener
        )
    }

    override fun onBindViewHolder(holder: AdvertsViewHolder, position: Int) {
        val currentAdvertises = advertises[position]
        holder.binding.apply {
            advertisesRowAdvertTitleTextView.text = currentAdvertises.advertTitle
            advertisesRowAddressTextView.text = currentAdvertises.address
            advertisesRowPriceTextView.text = currentAdvertises.price

            advertisesRowImageView.load(BASE_URL + currentAdvertises.image) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }
    override fun getItemCount(): Int {
        return advertises.size
    }
}